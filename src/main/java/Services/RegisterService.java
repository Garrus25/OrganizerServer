package Services;

import Data.ConfirmCodeData;
import Data.RegisterData;
import Data.Response;
import Database.QueryManager;
import Database.SQL.SQLQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegisterService {

    public Optional<Response> isRegistrationConfirmCodeValid(ConfirmCodeData code){


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
