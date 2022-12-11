import Data.Request;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

                    ByteBuffer buffer = ByteBuffer.allocate(320);
                    Future<Integer> readResult = clientChannel.read(buffer);
                    // do some computation
                    readResult.get();

                    buffer.flip();
                    String message = new String(buffer.array()).trim();
                    System.out.println(message);

                    RequestParser parser=new RequestParser();
                    ObjectMapper mapper = new ObjectMapper();
                    Request me = mapper.readValue(message, Request.class);

                    Optional<String> response= parser.parserRequest(me);
                    System.out.println("Odpowiedź "+response.get());

                    if (message.equals("bye")) {
                        break; // while loop
                    }
                    buffer = ByteBuffer.wrap(new String(message).getBytes());
                    Future<Integer> writeResult = clientChannel.write(buffer);

                    // do some computation
                    writeResult.get();
                    buffer.clear();

                } // while()

                clientChannel.close();
                serverChannel.close();

            }
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) throws SQLException, IOException {

        testMethod();


        /*RequestParser parser=new RequestParser();

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
  }


}











