package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static Connection con = null;
    private static final String url = "jdbc:mysql://localhost:3306/organizer";
    private static final String user = "root";
    private static final String pass = "";

    private final String DRIVER_MYSQL_CLASS="com.mysql.cj.jdbc.Driver";
    static
    {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return con;
    }
}
