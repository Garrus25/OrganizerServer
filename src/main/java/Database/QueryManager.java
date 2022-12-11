package Database;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class QueryManager {
    private static DataBaseConnection connectionDb;

    static {
        try {
            connectionDb= DataBaseConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static ResultSet getRSFromSQL(String sql, List<Object> args, List<Class> type) {
        try (Connection connection = connectionDb.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.size(); ++i) {
                if (type.get(i).getClass().equals(String.class)) {
                    statement.setString(i, ((String) args.get(i)));
                } else if (type.get(i).getClass().equals(Integer.class)) {
                    statement.setInt(i, (Integer) args.get(i));
                }
            }


            ResultSet resultSet = statement.executeQuery();


            return resultSet;

        } catch (Exception x) {

        }
        return null;
    }


}
