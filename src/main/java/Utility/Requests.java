package Utility;

import Data_mapperJsonClass.Request;
import Data_mapperJsonClass.Response;
import JSONUtility.ReadObjectFromJson;
import JSONUtility.SaveDataAsJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
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
            String str=SaveDataAsJson.save(request);


            for(int i=0;i<300;++i){
                str+=" ";
            }
            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
            Future<Integer> writeval = client.write(buffer);
            System.out.println("Writing to server: "+str);
            writeval.get();
            buffer.flip();
            Future<Integer> readval = client.read(buffer);

            readval.get();
            System.out.println("Received from server: "
                    +new String(buffer.array()).trim());



            buffer.flip();
            String rawDataRead=new String(buffer.array()).trim();

            System.out.println("Raw:"+rawDataRead);
            Response response= ReadObjectFromJson.read(rawDataRead,Response.class);
         //   readval.get();
           buffer.clear();
           readval.get();

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
    public static Optional<Response> makeAsync(Request request)  {

        Socket clientSocket;
        PrintWriter out;
       BufferedReader in;
       try {
           clientSocket = new Socket("127.0.0.1", 2137);
           out = new PrintWriter(clientSocket.getOutputStream(), true);
           in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println(SaveDataAsJson.save(request));
           String resp = in.readLine();
           System.out.println(resp);
           Response response= ReadObjectFromJson.read(resp,Response.class);
           return Optional.of(response);
       }catch (Exception x){
            x.printStackTrace();
       }

        return Optional.empty();
    }
}
