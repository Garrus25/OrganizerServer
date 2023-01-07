import Data_mapperJsonClass.Request;
import Data_mapperJsonClass.Response;
import HandlerRequest.RequestParser;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServerApp {

    // Z każdym klientem wiążemy kolejkę oczekujących na wysłanie buforów
    private final Map<SocketChannel, Queue<ByteBuffer>> pendingData = new HashMap<>();

    private ServerSocketChannel ssc;
    private Selector selector;
    private static final int BUFFER_CAPACITY_IN_BYTES = 4200;
    private static final Integer PORT_NUMBER = 2137;


    private final ExecutorService exec = Executors.newFixedThreadPool(16);
    public void start2() throws IOException {
        ServerSocket server = new ServerSocket(PORT_NUMBER);
        while (true) {
            Socket socket = server.accept();
            exec.execute(() -> handleRequest(socket));
        }
    }



    private void handleRequest(Socket socket)  {
        System.out.println("Start analise request");

        PrintWriter out = null;
        BufferedReader in = null;
        try{
        out = new PrintWriter(
                socket.getOutputStream(), true);
        System.out.println("A");

        // get the inputstream of client
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
            System.out.println("B");
        String line;
        String allText="";
        line=in.readLine();
       /* while ((line = in.readLine()) != null) {
            System.out.println("C : "+line);
            // writing the received message from
            // client
            allText+=line;

        }*/
        System.out.println("Request message raw "+line);

            System.out.printf(
                    " Sent from the client: %s\n",
                    line);



            RequestParser parser = new RequestParser();
            ObjectMapper mapper = new ObjectMapper();

            mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

            Request me = mapper.readValue((line.trim()), Request.class);

            System.out.println("Request data "+me.getData());




            Optional<Response> response = parser.requestParser(me);
            String message ="";
            if(response.isPresent()) {


                message = SaveDataAsJson.save(response.get());

                out.println(message);
            }
    }catch (Exception x){
            x.printStackTrace();
        }

    }
















    public void start() throws IOException, SQLException {
        selector = Selector.open();

        ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(PORT_NUMBER));
        ssc.configureBlocking(false);

        // Rejstujemy powiadomienie o nowym połączeniu
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();

            for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext(); ) {

                SelectionKey sk = it.next();
                // usuwam przetworzone klucze
                it.remove();

                if (sk.isValid() && sk.isAcceptable()) {
                    // akceptujemy nowego klienta
                    handleAccept();
                } else if (sk.isValid() && sk.isReadable()) {
                    // nieblokujące czytanie klienta
                    handleRead(sk);
                } else if (sk.isValid() && sk.isWritable()) {
                    // nieblokujący write do klienta
                    handleWrite(sk);
                }
            }
        }
    }

    private void handleAccept() throws IOException {
        SocketChannel sc = ssc.accept();

        if (sc != null) {
            sc.configureBlocking(false);
            //rejestruje dostęp do informacji o nieblokującym odczycie
            sc.register(selector, SelectionKey.OP_READ);
            pendingData.put(sc, new LinkedList<ByteBuffer>());
        }
    }

    private void handleRead(SelectionKey sk) throws IOException, SQLException {
        try{
        // pobieram kanał klienta
        SocketChannel sc = (SocketChannel) sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(BUFFER_CAPACITY_IN_BYTES);

        // czytam z kanału do bufora
        int read = 0;
        try{
             read = sc.read(bb);
        }catch (Exception x){
            pendingData.remove(sc);
            sc.close();
            return;
        }


        if (read == -1) {
            // -1 -> EOF koniec komunikacji usuwam klienta z mapy
            pendingData.remove(sc);
            sc.close();
        } else if (read > 0) {

            bb.flip();

            RequestParser parser = new RequestParser();
            ObjectMapper mapper = new ObjectMapper();

            mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            Request me = mapper.readValue((new String(bb.array()).trim()), Request.class);




            Optional<Response> response = parser.requestParser(me);
            String message ="";
            if(response.isPresent()) {


                 message = SaveDataAsJson.save(response.get());

            }
           while (message.length()<540){
               message=message+" ";
           }





            bb.clear();

            bb.put(message.getBytes());
            bb.flip();


            System.out.println("Received from client: "
                    + new String(bb.array()).trim());

            // Dodaje odpowiedz do kolejki klienta
            pendingData.get(sc).add(bb);
            // Wysyłam dane do klienta kiedy będe mógł nieblokująco wykonać write
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

            // piszę do kanału bufora

            int write = sc.write(bb);
            if (write == -1) {
                pendingData.remove(sc);
                sc.close();
                return;
            } else if (bb.hasRemaining()) {
                //Nie można wysłać całego bufora nieblokująco czekam na możłiwość wysyłki reszty
                return;
            }

            // wysłano cały bufor usuwanie z kolejki
            queue.remove();
        }

        // Wysłaliśmy odpowiedź. Wracamy do nasłuchiwania zapytań od klienta.
        sk.interestOps(SelectionKey.OP_READ);
    }
}