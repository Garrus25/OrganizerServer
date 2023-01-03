import java.io.IOException;
import java.sql.SQLException;

public class ServerMain {


    public static void startServer() throws IOException, SQLException {
        ServerApp server=new ServerApp();
        server.start();
    }


    public static void main(String[] args) throws IOException, SQLException {
        startServer();

    }


}











