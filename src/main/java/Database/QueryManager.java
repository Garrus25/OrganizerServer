package Database;

import Data.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class QueryManager {
    private static DataBaseConnection connectionDb;

    static {
        try {
            connectionDb= DataBaseConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Optional<Response> getRSFromSQL(String sql, List<Object> args, List<Class> type, Function<ResultSet,Optional<Response>> func) {
        List returnData=new ArrayList();
        try (Connection connection = connectionDb.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.size(); ++i) {
                if (type.get(i).equals(String.class)) {
                    System.out.println("typ string");
                    statement.setString(i+1, ((String) args.get(i)));
                } else if (type.get(i).equals(Integer.class)) {
                    statement.setInt(i+1, (Integer) args.get(i));
                }
            }


            System.out.println(statement);

            ResultSet resultSet = statement.executeQuery();
            return func.apply(resultSet);

        } catch (Exception x) {

        }
        return null;
    }


}
