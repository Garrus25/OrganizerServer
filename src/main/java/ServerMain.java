import Data.Request;
import Data.Response;
import HandlerRequest.RequestParser;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ServerMain {

    private static final int TIME_TO_WAIT_ON_REQUEST_SECONDS = 10;
    private static final int BUFFER_CAPACITY_IN_BYTES = 1024;
    private static final String SERVER_ADDRESS = "127.0.0.1";

    private static final Integer PORT_NUMBER = 2137;
    private static AsynchronousServerSocketChannel serverChannel;
    private static Future<AsynchronousSocketChannel> acceptResult;
    private static AsynchronousSocketChannel clientChannel;

    public static void testMethod() throws IOException {
        serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 2137);
        serverChannel.bind(hostAddress);
        acceptResult = serverChannel.accept();

        try {
            clientChannel = acceptResult.get();
            if ((clientChannel != null) && (clientChannel.isOpen())) {
                while (true) {

                    try {

                        ByteBuffer buffer = ByteBuffer.allocate(320);
                        Future<Integer> readResult = clientChannel.read(buffer);
                        // do some computation
                        readResult.get();

                        buffer.flip();
                        String message = new String(buffer.array()).trim();
                        System.out.println(":" + message);



                    //    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
                    //    encryptor.setPassword("xD");


                        RequestParser parser = new RequestParser();
                        ObjectMapper mapper = new ObjectMapper();

                        //  String textPurpose=mapper.readValue( "{\"idUser\":1,\"login\":\"konrad\",\"password\":\"testowe\",\"name\":\"Konrad\",\"surname\":\"Ktoś\",\"color\":\"#121212\",\"authorizeToken\":1922,\"email\":\"email@mail\",\"active\":false}",String.class);
                        //   System.out.println(":D "+textPurpose);
                        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                        Request me = mapper.readValue(message, Request.class);

                     //   String decrypted = encryptor.decrypt(me.getData());
                    //    System.out.println(decrypted);
                   //     me.setData(decrypted);


                        Optional<Response> response = parser.requestParser(me);
                        System.out.println("Odpowiedź " + response.get().getData());

                        message = SaveDataAsJson.saveDataAsJson(response.get());

                        if (message.equals("bye")) {
                            break; // while loop
                        }
                        System.out.println("ODP:"+message);
                        buffer = ByteBuffer.wrap(new String(message).getBytes());
                        Future<Integer> writeResult = clientChannel.write(buffer);

                        // do some computation
                        writeResult.get();
                        buffer.clear();
                    }catch (Exception exp){

                    }

                } // while()

                clientChannel.close();
                serverChannel.close();

            }
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();

        }




    }


    public static void testMethod2() throws IOException, ExecutionException, InterruptedException, TimeoutException {
        serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 2137);
        serverChannel.bind(hostAddress);
        while(true) {

                Future<AsynchronousSocketChannel> acceptCon =
                        serverChannel.accept();
                AsynchronousSocketChannel client = acceptCon.get(10,
                        TimeUnit.SECONDS);
                if ((client!= null) && (client.isOpen())) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    Future<Integer> readval = client.read(buffer);
                    System.out.println("Received from client: "
                            + new String(buffer.array()).trim());
                    readval.get();
                    buffer.flip();
                    String str= "I'm fine. Thank you!";
                    Future<Integer> writeVal = client.write(
                            ByteBuffer.wrap(str.getBytes()));
                    System.out.println("Writing back to client: "
                            +str);
                    writeVal.get();
                    buffer.clear();
                }
                client.close();



        }

    }


    public static void main(String[] args){
        try (AsynchronousServerSocketChannel server =
                     AsynchronousServerSocketChannel.open()) {
            server.bind(new InetSocketAddress("127.0.0.1",
                    2137));
            Future<AsynchronousSocketChannel> acceptCon =
                    server.accept();
            AsynchronousSocketChannel client = acceptCon.get(10,
                    TimeUnit.SECONDS);
            if ((client!= null) && (client.isOpen())) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                Future<Integer> readval = client.read(buffer);
                System.out.println("Received from client: "
                        + new String(buffer.array()).trim());
                readval.get();
                buffer.flip();
                String str= "I'm fine. Thank you!";
                Future<Integer> writeVal = client.write(
                        ByteBuffer.wrap(str.getBytes()));
                System.out.println("Writing back to client: "
                        +str);
                writeVal.get();
                buffer.clear();
            }
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    public static void main(String[] args) throws SQLException, IOException, ExecutionException, InterruptedException, TimeoutException {

        testMethod2();


        /*HandlerRequest.RequestParser parser=new HandlerRequest.RequestParser();

        try (AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open()) {

            server.bind(new InetSocketAddress(SERVER_ADDRESS, PORT_NUMBER));

            while (true) {



            System.out.println("Czekam");
            Future<AsynchronousSocketChannel> acceptCon = server.accept();
            AsynchronousSocketChannel client = acceptCon.get(TIME_TO_WAIT_ON_REQUEST_SECONDS, TimeUnit.SECONDS);

            if ((client != null) && (client.isOpen())) {
                System.out.println("Odebrano odpowiedź");
                ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY_IN_BYTES);
                Future<Integer> readValueFromClient = client.read(buffer);

                System.out.println("Received from client: " + new String(buffer.array()).trim());

                Optional<String> response= parser.parserRequest(new String(buffer.array()).trim());
                System.out.println("Odpowiedź "+response.get());
                readValueFromClient.get();
                buffer.flip();

                if(response.isPresent()){

                    String str = response.get();
                    Future<Integer> writeVal = client.write(ByteBuffer.wrap(str.getBytes()));
                    writeVal.get();

                }else {

                    ErrorResponse error=new ErrorResponse("Sth is not ok :D");
                    Future<Integer> writeVal = client.write(ByteBuffer.wrap(error.generateJSONError().getBytes()));
                    writeVal.get();

                }


                buffer.clear();
            }
                assert client != null;
                client.close();

        }
      } catch(Exception e){
         e.printStackTrace();
    }
*/
  //}


}











