import Data.RegisterData;
import Data.Request;
import Data.Response;
import JSONUtility.SaveDataAsJson;
import Tests.UtilityTest.Requests;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class sdf {


  /*  public static void main (String[]args) throws JsonProcessingException {
        RegisterData registerData = new RegisterData(1,"konrad99","asdas","asd@assf","asd","asd","#asd",19,false);
        Request request = new Request("registerUserTemporary", SaveDataAsJson.saveDataAsJson(registerData));
        Optional<Response> response= Requests.make(request);
        System.out.println(response.get());
    }*/


    public static void main(String[] args){
        try (AsynchronousSocketChannel client =
                     AsynchronousSocketChannel.open()) {
            Future<Void> result = client.connect(
                    new InetSocketAddress("127.0.0.1", 2137));
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
        }
        catch (ExecutionException | IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            System.out.println("Disconnected from the server.");
        }
    }
}
