import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginService {
    private DataBaseConnection connectionDb;
    public LoginService() throws SQLException {
        connectionDb= DataBaseConnection.getInstance();

    }

    //If User Login Available
    public String ifUserLoginAvailable() throws JsonProcessingException {

        try (Connection connection = connectionDb.getConnection();
             Statement statement = connection.createStatement()) {

            String selectSql = LoginSql.IS_USER_EXIST.getSqlText();
            ResultSet resultSet = statement.executeQuery(selectSql);

            if (resultSet.first()) {
                System.out.println(resultSet.getString(0));
                String isUserAboutLoginExists=resultSet.getString(0);
                if(!isUserAboutLoginExists.equals("0")){
                    ResponseLogin responseLogin=new ResponseLogin("isLoginAvailable","NO");
                    ObjectMapper mapper = new ObjectMapper();
                    String json=mapper.writeValueAsString(responseLogin);
                    return json;
                }
            }


        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ResponseLogin responseLogin=new ResponseLogin("isLoginAvailable","YES");
        ObjectMapper mapper = new ObjectMapper();
        String json=mapper.writeValueAsString(responseLogin);
        return json;
    }


}
