package Services;

import Data.*;
import Database.DataBaseConnection;
import Database.QueryManager;
import Database.SQL.SQLQuery;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import responses.ErrorResponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LoginService {




    public Optional<Response> isUserLoginDataValid(LoginAndPassword loginAndPassword){
        Optional<Response> resultx= QueryManager.getRSFromSQL(SQLQuery.IS_USER_LOGIN_DATA_VALID, Arrays.asList(new String[]{loginAndPassword.getLogin(),
                        loginAndPassword.getPassword()}),Arrays.asList(new Class[]{String.class,String.class}),
                (result)->{
                    try {

                        if(result.next()){

                            Integer isValid= result.getInt(1);

                            if(isValid>0){
                                ValidLoginData x=new ValidLoginData("YES");
                                String json=SaveDataAsJson.saveDataAsJson(x);
                                return Optional.of( new Response(json,"getIdUserFromLogin"));
                            }
                        }else{
                            ValidLoginData x=new ValidLoginData("NO");
                            String json=SaveDataAsJson.saveDataAsJson(x);
                            return Optional.of(new Response(json,"getIdUserFromLogin"));

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                });
        return resultx;
    }


    public Optional<Response> getUserIdFromUserLogin(String loginUser){
        Optional<Response> resultx= QueryManager.getRSFromSQL(SQLQuery.GET_USER_ID_BASED_ON_USER_LOGIN, Collections.singletonList(loginUser),Collections.singletonList(String.class),
                (result)->{
                    try {

                        if(result.next()){

                            String userID= String.valueOf(result.getInt(1));
                            UserID userIdentification=new UserID(userID);
                            if(userID.length()>0){
                                String json=SaveDataAsJson.saveDataAsJson(userIdentification);
                                return Optional.of( new Response(json,"getIdUserFromLogin"));
                            }
                        }else{

                            ErrorResponse error=new ErrorResponse("User with this login no exists");
                            return Optional.of(new Response(error.getErrorCode(),"getIdUserFromLogin"));

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                });
        return resultx;
    }
    //If User Login Available

    public Optional<Response> ifUserLoginAvailable(String data) throws SQLException, JsonProcessingException {


        Optional<Response> resultx= QueryManager.getRSFromSQL(SQLQuery.IS_USER_LOGIN_EXISTS, Collections.singletonList(data),Collections.singletonList(String.class),
                (result)->{
                    try {

                        if(result.next()){

                            String isUserAboutLoginExists= String.valueOf(result.getInt(1));


                            if(!isUserAboutLoginExists.trim().equals("0")){

                                ResponseLogin responseLogin=new ResponseLogin("isLoginAvailable","NO");
                                String json=SaveDataAsJson.saveDataAsJson(responseLogin);
                                return Optional.of( new Response(json,"isLoginAvailable"));
                            }else{

                                ResponseLogin responseLogin=new ResponseLogin("isLoginAvailable","YES");
                                String json=SaveDataAsJson.saveDataAsJson(responseLogin);

                                return Optional.of(new Response(json,"isLoginAvailable"));
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                });
        return resultx;


    }


}
