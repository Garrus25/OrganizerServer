import Data.Request;
import Data.Response;
import HandlerRequest.RequestParser;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonParser;
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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ServerMain {


    public static void startServer() throws IOException, SQLException {
        ServerApp server=new ServerApp();
        server.start();
    }


    public static void main(String[] args) throws IOException, SQLException {
        startServer();

    }


}











