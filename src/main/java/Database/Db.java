package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static Connection con = null;
    private static final String URL = "jdbc:mysql://localhost:3306/organizer";
    private static final String USER = "root";
    private static final String PASS = "";

    private static final String DRIVER_MYSQL_CLASS="com.mysql.cj.jdbc.Driver";
    static
    {

        try {
            Class.forName(DRIVER_MYSQL_CLASS);
            con = DriverManager.getConnection(URL, USER, PASS);
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
