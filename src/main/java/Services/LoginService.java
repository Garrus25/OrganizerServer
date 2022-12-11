package Services;

import Data.ResponseLogin;
import Database.DataBaseConnection;
import Data.Response;
import Database.QueryManager;
import Database.SQL.SQLQuery;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LoginService {


    //If User Login Available
    public Optional<Response> ifUserLoginAvailable(String data) throws SQLException, JsonProcessingException {

        ResultSet result= QueryManager.getRSFromSQL(SQLQuery.IS_USER_LOGIN_EXISTS, Collections.singletonList(data),Collections.singletonList(String.class));

        assert result != null;
        if(result.first()){
            String isUserAboutLoginExists= String.valueOf(result.getInt(1));
            if(!isUserAboutLoginExists.equals("0")){
                ResponseLogin responseLogin=new ResponseLogin("isLoginAvailable","NO");
                String json=SaveDataAsJson.saveDataAsJson(responseLogin);
                return Optional.of( new Response(json,"isLoginAvailable"));
            }
        }else{
            ResponseLogin responseLogin=new ResponseLogin("isLoginAvailable","YES");
            String json=SaveDataAsJson.saveDataAsJson(responseLogin);
            return Optional.of(new Response(json,"isLoginAvailable"));
        }

        return null;
    }


}
