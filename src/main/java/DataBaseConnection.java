import java.sql.*;

public class DataBaseConnection {
    private static Connection con = null;

    static {
        //String url = "jdbc:mysql://localhost:3306/bank";
        String url = "jdbc:mariadb://localhost:3306/bank";
        String user = "root";
        String pass = "";
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public static void main(String[] args) throws SQLException {
        DataBaseConnection base = new DataBaseConnection();
        System.out.println("Polaczenie z baza: " + base.getConnection());
        String query = "SELECT imie FROM klient WHERE idKlienta = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, 11);
        ResultSet rs = ps.executeQuery();
        rs.next();
        System.out.println("Wynik z testowego Query: " + rs.getString(1));
    }
}