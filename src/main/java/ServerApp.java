import Data.Request;
import Data.Response;
import HandlerRequest.RequestParser;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.SQLException;
import java.util.*;

class ServerApp {

    // Z każdym klientem powiążemy kolejkę oczekujących na wysłanie buforów.
    private final Map<SocketChannel, Queue<ByteBuffer>> pendingData = new HashMap<>();

    private ServerSocketChannel ssc;
    private Selector selector;

    public void start() throws IOException, SQLException {
        selector = Selector.open();

        ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(2137));
        ssc.configureBlocking(false);

        // Chcemy być informowani o gotowości do akceptacji nowego połączenia.
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();

            for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext(); ) {

                SelectionKey sk = it.next();
                // Należy pamiętać o usuwaniu przetworzonych kluczy!
                it.remove();

                if (sk.isValid() && sk.isAcceptable()) {
                    // Nowy klient czeka na akceptację.
                    handleAccept();
                } else if (sk.isValid() && sk.isReadable()) {
                    // Możemy wykonać nieblokującą operację READ na kliencie.
                    handleRead(sk);
                } else if (sk.isValid() && sk.isWritable()) {
                    // Możemy wykonać nieblokującą operację WRITE na kliencie.
                    handleWrite(sk);
                }
            }
        }
    }

    private void handleAccept() throws IOException {
        SocketChannel sc = ssc.accept();

        if (sc != null) {
            sc.configureBlocking(false);
            // Chcemy być informowani o możliwości wykonania nieblokującej
            // operacji READ na kliencie.
            sc.register(selector, SelectionKey.OP_READ);

            pendingData.put(sc, new LinkedList<ByteBuffer>());
        }
    }

    private void handleRead(SelectionKey sk) throws IOException, SQLException {
        try{
        // Pobieramy kanał powiązany z zadanym kluczem.
        SocketChannel sc = (SocketChannel) sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(4200);

        // Czytamy z kanału sc do bufora bb. Zmienna read zawiera
        // liczbę przeczytanych znaków.
        int read = 0;
        try{
             read = sc.read(bb);
        }catch (Exception x){
            pendingData.remove(sc);
            sc.close();
            return;
        }


        if (read == -1) {
            // -1 -> EOF. Usuwamy klienta z mapy i zamykamy połączenie.
            pendingData.remove(sc);
            sc.close();
        } else if (read > 0) {
            // Aktualizujemy pozycję i limit w buforze, a także podmieniamy jego
            // zawartość na wersję UPPERCASE.
            bb.flip();











            RequestParser parser = new RequestParser();
            ObjectMapper mapper = new ObjectMapper();

            //  String textPurpose=mapper.readValue( "{\"idUser\":1,\"login\":\"konrad\",\"password\":\"testowe\",\"name\":\"Konrad\",\"surname\":\"Ktoś\",\"color\":\"#121212\",\"authorizeToken\":1922,\"email\":\"email@mail\",\"active\":false}",String.class);
            //   System.out.println(":D "+textPurpose);
            mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            Request me = mapper.readValue((new String(bb.array()).trim()), Request.class);

            //   String decrypted = encryptor.decrypt(me.getData());
            //    System.out.println(decrypted);
            //     me.setData(decrypted);
            System.out.println(me.getHeader()+"/"+me.getData());

            if(parser==null){
                System.out.println("null parser");
            }

            Optional<Response> response = parser.requestParser(me);
            String message ="";
            if(response.isPresent()) {
                System.out.println("Odpowiedź " + response.get().getData());

                 message = SaveDataAsJson.saveDataAsJson(response.get());

            }
           while (message.length()<540){
               message=message+" ";
           }
           System.out.println("Mess: "+message);




            bb.clear();

            bb.put(message.getBytes());
            bb.flip();
         /*   for (int i = 0; i < bb.limit(); i++) {
                bb.put(i, (byte) Character.toUpperCase((char) bb.get(i)));

            }*/

            System.out.println("Received from client: "
                    + new String(bb.array()).trim());

            // Przetworzony bufor zostaje dodany do kolejki klienta.
            pendingData.get(sc).add(bb);
            // Po odczytaniu danych chcemy wysłać przetworzone wejście z powrotem
            // do klienta, więc od teraz interesuje nas, kiedy można wykonać na nim
            // nieblokującą operację WRITE.
            sk.interestOps(SelectionKey.OP_WRITE);


        }
    }catch (Exception x){
            x.printStackTrace();
        }}

    private void handleWrite(SelectionKey sk) throws IOException {
        SocketChannel sc = (SocketChannel) sk.channel();
        Queue<ByteBuffer> queue = pendingData.get(sc);

        while (!queue.isEmpty()) {
            ByteBuffer bb = queue.peek();

            // Piszemy do kanału sc z bufora bb. Zmienna write zawiera 
            // liczbę wysłanych znaków.

            int write = sc.write(bb);//    ByteBuffer.wrap(str.getBytes())
            if (write == -1) {
                pendingData.remove(sc);
                sc.close();
                return;
            } else if (bb.hasRemaining()) {
                // Nie udało się wysłać całej zawartości bufora. Oznacza to, że w trakcie
                // wykonywania operacji write przestało być możliwe nieblokujące
                // wysyłanie danych. Opuszczamy metodę - reszta bufora zostanie wysłana
                // przy następnej okazji.
                return;
            }

            // Wysłaliśmy cały bufor. Usuwamy go z kolejki.
            queue.remove();
        }

        // Wysłaliśmy odpowiedź. Wracamy do nasłuchiwania zapytań od klienta.
        sk.interestOps(SelectionKey.OP_READ);
    }
}