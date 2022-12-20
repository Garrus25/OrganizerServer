package Services;

import Data.*;
import Database.QueryManager;
import Database.SQL.SQLQuery;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import responses.ErrorResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RegisterService {




    public void registerUser(UserID idUser){
        QueryManager.executeQuery(SQLQuery.REGISTER_USER, Collections.singletonList(idUser.getUserID()), Collections.singletonList(String.class));
    }

    public Optional<Response> isRegistrationConfirmCodeValid(ConfirmCodeData code){

        List<Object> dataConfirm=new ArrayList<Object>(){
            {
              add(  code.getConfirmCode());
              add( code.getIdUser());
            }
        };

        List<Class> typeConfirm=new ArrayList<Class>() {
            {
                add(String.class);
                add(String.class);
            }
        };


        Optional<Response> resultx= QueryManager.getRSFromSQL(SQLQuery.IS_REGISTRATION_CODE_VALID, dataConfirm,typeConfirm,
                (result)->{
                    try {

                        if(result.next()){

                            Integer isCodeValid= result.getInt(1);

                            if(isCodeValid>0){
                                ConfirmCodeResponse response=new ConfirmCodeResponse(Boolean.TRUE);
                                String json= SaveDataAsJson.saveDataAsJson(response);
                                return Optional.of( new Response(json,"isCodeVerficationValid"));
                            }
                        }else{
                            ConfirmCodeResponse response=new ConfirmCodeResponse(Boolean.FALSE);
                            String json= SaveDataAsJson.saveDataAsJson(response);
                            return Optional.of(new Response(json,"isCodeVerficationValid"));

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

    public Optional<Response> registerTemporaryUser(RegisterData registerData) {
        List<Object> dataRegister=new ArrayList<Object>(){
            {
                add(registerData.getLogin());
                add(registerData.getPassword());
                add(registerData.getEmail());
                add(registerData.getName());
                add(registerData.getSurname());
                add(registerData.getColor());
                add(registerData.getAuthorizeToken());
                add(Boolean.FALSE);
            }
        };

        List<Class> dataRegisterColumnType=new ArrayList<Class>(){{

            add(String.class);
            add(String.class);
            add(String.class);
            add(String.class);
            add(String.class);
            add(String.class);
            add(Integer.class);
            add(Boolean.class);

        }};

        QueryManager.executeQuery(SQLQuery.REGISTER_TEMPORARY_USER,dataRegister,dataRegisterColumnType);
        return Optional.of(new Response("IfUserTempRegister","{Register:YES}"));
    }
}
