package Database;

import Data.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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



    public static void executeQuery(String sql, List<Object> args, List<Class> type)   {
        System.out.println("execute "+sql);
        Connection connection = connectionDb.getConnection();

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.size(); ++i) {
                System.out.println("i"+i);

                if (type.get(i).equals(String.class)) {
                    System.out.println("typ string");
                    System.out.println((String) args.get(i));
                    statement.setString(i+1, ((String) args.get(i)));
                } else if (type.get(i).equals(Integer.class)) {
                    System.out.println("typ int");
                    System.out.println((Integer) args.get(i));
                    statement.setInt(i+1, (Integer) args.get(i));
                }else if(type.get(i).equals(Boolean.class)){
                    System.out.println("typ bool");
                    System.out.println((Boolean) args.get(i));
                    statement.setBoolean(i+1,(Boolean) args.get(i));
                }else{
                    statement.setTimestamp(i+1,(Timestamp) args.get(i));
                }
            }


            System.out.println(statement);
          //  statement.executeQuery();
            statement.execute();




        } catch (Exception x) {
            x.printStackTrace();
        }

    }
    public static Optional<Response> getFromSQL(String sql, List<Object> args, List<Class> type, Function<ResultSet,Optional<Response>> func)  {
        List returnData=new ArrayList();

        try {

            Connection connection = connectionDb.getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.size(); ++i) {
                if (type.get(i).equals(String.class)) {
                    System.out.println("typ string");
                    statement.setString(i+1, ((String) args.get(i)));
                } else if (type.get(i).equals(Integer.class)) {
                    statement.setInt(i+1, (Integer) args.get(i));
                }else{
                    statement.setTimestamp(i+1,(Timestamp) args.get(i));
                }
            }


            System.out.println(statement);

            ResultSet resultSet = statement.executeQuery();

            return func.apply(resultSet);

        } catch (Exception x) {
                x.printStackTrace();
        }
        return null;
    }


}
