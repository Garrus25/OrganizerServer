

import java.io.IOException;
import java.sql.SQLException;

public class ServerMain {


    public static void startServer() throws IOException, SQLException {
        ServerApp server=new ServerApp();
        server.start();

//        server.start2();
    }


    public static void main(String[] args) throws IOException, SQLException {
        startServer();
       // MailerServices.sendMail("konradnrog@gmail.com");

    }


}











