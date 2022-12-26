package Tests.UtilityTest;

import Data.Request;
import Data.Response;
import JSONUtility.ReadObjectFromJson;
import JSONUtility.SaveDataAsJson;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Requests {




    public static Optional<Response> make2(Request request){
        try (AsynchronousSocketChannel client =
                     AsynchronousSocketChannel.open()) {
            Future<Void> result = client.connect(
                    new InetSocketAddress("127.0.0.1", 2137));
            result.get();
          //  String str= "Hello! How are you?";
            String str=SaveDataAsJson.saveDataAsJson(request);
            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
            Future<Integer> writeval = client.write(buffer);
            System.out.println("Writing to server: "+str);
            writeval.get();
            buffer.flip();
            Future<Integer> readval = client.read(buffer);

            readval.get();
            System.out.println("Received from server: "
                    +new String(buffer.array()).trim());

            String rawDataRead=new String(buffer.array()).trim();

            System.out.println("Raw:"+rawDataRead);
            Response response= ReadObjectFromJson.read(rawDataRead,Response.class);
         //   readval.get();
           buffer.clear();

            return Optional.ofNullable(response);
        }
        catch (ExecutionException | IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            System.out.println("Disconnected from the server.");
        }
        return Optional.empty();
    }
    public static Optional<Response> make(Request request){

       try (AsynchronousSocketChannel client = AsynchronousSocketChannel.open()) {
            Future<Void> result = client.connect(new InetSocketAddress("127.0.0.1", 2137));


           result.get();



           String str= "Hello! How are you?";
           ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
           Future<Integer> writeval = client.write(buffer);
           System.out.println("Writing to server: "+str);
           writeval.get();
           buffer.flip();
           Future<Integer> readval = client.read(buffer);
           System.out.println("Received from server: "
                   +new String(buffer.array()).trim());
           readval.get();
           buffer.clear();
/*
           String messageForServer=SaveDataAsJson.saveDataAsJson(request);
           ByteBuffer buffer = ByteBuffer.wrap(messageForServer.getBytes());
           Future<Integer> writeval = client.write(buffer);
           System.out.println("Writing to server: "+messageForServer);
           writeval.get();
           buffer.flip();








           Future<Integer> readval = client.read(buffer);
           System.out.println("Received from server: "
                   +new String(buffer.array()).trim());
           String rawResp=new String(buffer.array()).trim();
           readval.get();
           buffer.clear();
           return Optional.of(ReadObjectFromJson.read(rawResp,Response.class));
*/
/*
            ByteBuffer buffer = ByteBuffer.wrap(SaveDataAsJson.saveDataAsJson(request).getBytes());
            Future<Integer> writeval = client.write(buffer);

            writeval.get();
            buffer.flip();
            Future<Integer> readval = client.read(buffer);
            System.out.println("Receive from server: " +new String(buffer.array()).trim());
            String rawDataRead=new String(buffer.array()).trim();

            Response response= ReadObjectFromJson.read(rawDataRead,Response.class);
            System.out.println(response.getData());

            readval.get();
            buffer.clear();
            return Optional.of(response);*/



        }
        catch (ExecutionException | IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            System.out.println("Disconnected from the server");
        }

        return Optional.empty();
    }
}
