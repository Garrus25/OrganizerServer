import Data.LoginData;
import Data.RegisterData;
import Data.Request;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class client {

    public static void main(String[] args) throws IOException {

        try (AsynchronousSocketChannel client =
                     AsynchronousSocketChannel.open()) {
            Future<Void> result = client.connect(
                    new InetSocketAddress("127.0.0.1", 2137));
            result.get();
            //String str= "{header:'IfUserLoginAvailable',data:'kk'}";

           // String carAsString = objectMapper.writeValueAsString(car);
            Request x=new Request("ifUserLoginAvailable", SaveDataAsJson.saveDataAsJson(new LoginData("Pawe")));

            RegisterData userDataREgister=new RegisterData(1,"konrad","testowe","email@mail","Konrad","Ktoś","#121212",1922,false);



            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword("xD");
            String encrypted= encryptor.encrypt(SaveDataAsJson.saveDataAsJson(userDataREgister));

            Request xx=new Request("registerUserTemporary", "\""+encrypted+"\"");




            ObjectMapper mapper = new ObjectMapper();
            String json=mapper.writeValueAsString(xx);
            System.out.println(json);


            ByteBuffer buffer = ByteBuffer.wrap(json.getBytes());
            Future<Integer> writeval = client.write(buffer);
            System.out.println("Write to serve: "+json);
            writeval.get();
            buffer.flip();
            Future<Integer> readval = client.read(buffer);
            System.out.println("Receive from server: "
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
