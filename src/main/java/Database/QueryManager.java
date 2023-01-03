package Database;

import Data_mapperJsonClass.Response;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class QueryManager {
    private static DataBaseConnection connectionDb;
    private static final int OFFSET_ITERATE_RESULT_SET = 1;
    static {
        try {
            connectionDb = DataBaseConnection.getInstance();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void executeQuery(String sql, List<Object> args, List<Class> type)   {

        Connection connection = connectionDb.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.size(); ++i) {

                if (type.get(i).equals(String.class)) {
                    statement.setString(i + OFFSET_ITERATE_RESULT_SET, ((String) args.get(i)));
                } else if (type.get(i).equals(Integer.class)) {
                    statement.setInt(i + OFFSET_ITERATE_RESULT_SET, (Integer) args.get(i));
                }else if(type.get(i).equals(Boolean.class)){
                    statement.setBoolean(i + OFFSET_ITERATE_RESULT_SET,(Boolean) args.get(i));
                }else{
                    statement.setTimestamp(i + OFFSET_ITERATE_RESULT_SET,(Timestamp) args.get(i));
                }
            }

            statement.execute();


        } catch (Exception x) {
            x.printStackTrace();
        }

    }
    public static Optional<Response> getFromSQL(String sql, List<Object> args, List<Class> type, Function<ResultSet,Optional<Response>> func)  {

        try {

            Connection connection = connectionDb.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 0; i < args.size(); ++i) {
                if (type.get(i).equals(String.class)) {
                    statement.setString(i + OFFSET_ITERATE_RESULT_SET, ((String) args.get(i)));
                } else if (type.get(i).equals(Integer.class)) {
                    statement.setInt(i + OFFSET_ITERATE_RESULT_SET, (Integer) args.get(i));
                }else{
                    statement.setTimestamp(i + OFFSET_ITERATE_RESULT_SET,(Timestamp) args.get(i));
                }
            }

            ResultSet resultSet = statement.executeQuery();

            return func.apply(resultSet);

        } catch (Exception x) {
                x.printStackTrace();
        }
        return Optional.empty();
    }


}
