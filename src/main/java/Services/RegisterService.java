package Services;

import Data.*;
import Database.QueryManager;
import Database.SQL.SQLQuery;
import JSONUtility.CodeResponse;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RegisterService {

    public Response registerUser(UserID idUser){
        QueryManager.executeQuery(SQLQuery.REGISTER_USER, Collections.singletonList(idUser.getUserID()), Collections.singletonList(Integer.class));
        return CodeResponse.OK.getResponseForCode();
    }

    public Optional<Response> isRegistrationConfirmCodeValid(ConfirmCodeData code){

        final int INDEX_COL_FROM_DB_WITH_INFO_ABOUT_CODE_VALID=1;
        final int NO_DATA_IN_DB_WITH_CODE_QUANTITY=0;
        List<Object> dataConfirm=new ArrayList<Object>(){
            {
              add( code.getConfirmCode());
              add( code.getIdUser());
            }
        };

        List<Class> typeConfirmData=new ArrayList<Class>() {
            {
                add(String.class);
                add(Integer.class);
            }
        };


        Optional<Response> result= QueryManager.getFromSQL(SQLQuery.IS_REGISTRATION_CODE_VALID, dataConfirm,typeConfirmData,
                (resultRows)->{
                    try {

                        if(resultRows.next()){
                            int isCodeValid= resultRows.getInt(INDEX_COL_FROM_DB_WITH_INFO_ABOUT_CODE_VALID);
                            if(isCodeValid>NO_DATA_IN_DB_WITH_CODE_QUANTITY){

                                ConfirmCodeResponse response=new ConfirmCodeResponse(Boolean.TRUE);
                                String json= SaveDataAsJson.save(response);
                                return Optional.of( new Response(json,RequestType.IS_CODE_CONFIRM_ACCOUNT_VALID.getNameRequest()));
                            }else{

                                ConfirmCodeResponse response=new ConfirmCodeResponse(Boolean.FALSE);
                                String json= SaveDataAsJson.save(response);
                                return Optional.of(new Response(json,RequestType.IS_CODE_CONFIRM_ACCOUNT_VALID.getNameRequest()));
                            }
                        }

                        resultRows.close();
                    } catch (SQLException | JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                    return Optional.empty();
                });
        return result;

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
        return Optional.of(CodeResponse.OK.getResponseForCode());
    }
}
