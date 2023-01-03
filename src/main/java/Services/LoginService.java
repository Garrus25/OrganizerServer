package Services;

import Data.*;
import Database.QueryManager;
import Database.SQL.SQLQuery;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import responses.ErrorResponse;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class LoginService {




    public Optional<Response> isUserLoginDataValid(LoginAndPassword loginAndPassword) throws SQLException {

        String VALID_LOGIN_DATA="YES";
        String ERROR_LOGIN_DATA="NO";

        Optional<Response> result= QueryManager.getFromSQL(SQLQuery.IS_USER_LOGIN_DATA_VALID, Arrays.asList(new String[]{loginAndPassword.getLogin(),
                        loginAndPassword.getPassword()}),Arrays.asList(new Class[]{String.class,String.class}),
                   (resultArg)->{
                    try {
                        if(resultArg.next()){
                            int isValid= resultArg.getInt(1);
                            if(isValid>0){
                                ValidLoginData x=new ValidLoginData(VALID_LOGIN_DATA);
                                String json=SaveDataAsJson.save(x);
                                return Optional.of( new Response(json,RequestType.USER_LOGIN_DATA_VALID.getNameRequest()));
                            }else{
                                ValidLoginData x=new ValidLoginData(ERROR_LOGIN_DATA);
                                String json=SaveDataAsJson.save(x);
                                return Optional.of( new Response(json,RequestType.USER_LOGIN_DATA_VALID.getNameRequest()));
                            }
                        }else{
                            ValidLoginData x=new ValidLoginData(ERROR_LOGIN_DATA);
                            String json=SaveDataAsJson.save(x);
                            return Optional.of(new Response(json,RequestType.USER_LOGIN_DATA_VALID.getNameRequest()));

                        }
                    } catch (SQLException | JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                   });
        return result;
    }


    public Optional<Response> getUserIdFromUserLogin(String loginUser) throws SQLException {
        Optional<Response> result= QueryManager.getFromSQL(SQLQuery.GET_USER_ID_BASED_ON_USER_LOGIN, Collections.singletonList(loginUser),Collections.singletonList(String.class),
                (resultArg)->{
                    try {

                        if(resultArg.next()){
                            String userID= String.valueOf(resultArg.getInt(1));
                            UserID userIdentification=new UserID(userID);
                            if(userID.length()>0){
                                String json=SaveDataAsJson.save(userIdentification);
                                return Optional.of( new Response(json,RequestType.GET_USER_ID_FROM_LOGIN.getNameRequest()));
                            }
                        }else{

                            ErrorResponse error=new ErrorResponse("User with this login no exists");
                            return Optional.of(new Response(error.getErrorCode(),RequestType.GET_USER_ID_FROM_LOGIN.getNameRequest()));

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                });
        return result;
    }


    public Optional<Response> ifUserLoginAvailable(String data) throws SQLException, JsonProcessingException {

        String AVAILABLE_LOGIN="YES";
        String NO_AVAILABLE_LOGIN="NO";

        String NO_USER_ABOUT_LOGIN="0";

        Optional<Response> result= QueryManager.getFromSQL(SQLQuery.IS_USER_LOGIN_EXISTS, Collections.singletonList(data),Collections.singletonList(String.class),
                (resultParam)->{
                    try {

                        if(resultParam.next()){
                            String isUserAboutLoginExists= String.valueOf(resultParam.getInt(1));

                            if(!isUserAboutLoginExists.trim().equals(NO_USER_ABOUT_LOGIN)){
                                ResponseLogin responseLogin=new ResponseLogin(RequestType.IF_USER_LOGIN_AVAILABLE.getNameRequest(),NO_AVAILABLE_LOGIN);
                                String json=SaveDataAsJson.save(responseLogin);
                                return Optional.of( new Response(json,RequestType.IF_USER_LOGIN_AVAILABLE.getNameRequest()));
                            }else{
                                ResponseLogin responseLogin=new ResponseLogin(RequestType.IF_USER_LOGIN_AVAILABLE.getNameRequest(),AVAILABLE_LOGIN);
                                String json=SaveDataAsJson.save(responseLogin);
                                return Optional.of(new Response(json,RequestType.IF_USER_LOGIN_AVAILABLE.getNameRequest()));
                            }
                        }
                    } catch (SQLException | JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return Optional.empty();
                });
        return result;

    }


}
