package Services;

import Data.RegisterData;
import Data.Response;
import Data.ResponseLogin;
import Database.QueryManager;
import Database.SQL.SQLQuery;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RegisterService {

    public Optional<Response> registerTemporaryUser(RegisterData registerData) throws SQLException, JsonProcessingException {

        List<Object> list = Collections.singletonList(new ArrayList<Object>() {
            {
                add(new Integer(registerData.getIdUser()));
                add(        registerData.getLogin());
                        add(        registerData.getPassword());
                                add(registerData.getEmail());
                                        add(    registerData.getName());
                                                add(      registerData.getSurname());
                                                        add(      registerData.getColor());
                                                                add(      registerData.getAuthorizeToken());
                                                                add(     Boolean.FALSE);
            } });


        List<Class> args=new ArrayList<Class>(){{
           add( Integer.class);
           add(String.class);
            add(String.class);
            add(String.class);
            add(String.class);
            add(String.class);
            add(String.class);
            add(String.class);
            add(Boolean.class);

        }};

        QueryManager.executeQuery(SQLQuery.REGISTER_TEMPORARY_USER,list,args);
        return Optional.of(new Response("IfUserTempRegister","{Register:YES}"));
      /*  assert result != null;
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
*/

    }
}
