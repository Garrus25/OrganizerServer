import responses.ErrorResponse;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ServerMain {

    private static final int TIME_TO_WAIT_ON_REQUEST_SECONDS = 10;
    private static final int BUFFER_CAPACITY_IN_BYTES = 1024;
    private static final String SERVER_ADDRESS = "127.0.0.1";

    private static final Integer PORT_NUMBER = 2137;


    public static void main(String[] args) throws SQLException {
        RequestParser parser=new RequestParser();

        try (AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open()) {

            server.bind(new InetSocketAddress(SERVER_ADDRESS, PORT_NUMBER));

            while (true) {

            Future<AsynchronousSocketChannel> acceptCon = server.accept();
            AsynchronousSocketChannel client = acceptCon.get(TIME_TO_WAIT_ON_REQUEST_SECONDS, TimeUnit.SECONDS);

            if ((client != null) && (client.isOpen())) {
                ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY_IN_BYTES);
                Future<Integer> readValueFromClient = client.read(buffer);

                System.out.println("Received from client: " + new String(buffer.array()).trim());

                Optional<String> response= parser.parserRequest(new String(buffer.array()).trim());
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

  }


}











