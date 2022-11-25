import java.sql.*;

public final class DataBaseConnection {
    private static DataBaseConnection instance;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/greenhouse";
    private final String username = "root";
    private final String password = "";


    private DataBaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DataBaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}