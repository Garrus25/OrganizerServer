package HandlerRequest;

import Data.RegisterData;
import Data.Request;
import Data.Response;
import JSONUtility.ReadObjectFromJson;
import Services.RegisterService;
import java.sql.SQLException;
import java.util.Optional;

public class RegisterRequestHandler extends RequestService {
    private RegisterService registerService;
    private String REQUEST_REGISTER_USER_TEMPORARY="registerUserTemporary";
    public RegisterRequestHandler() throws SQLException {
        this.registerService=new RegisterService();
        registerHandlersForClass();
    }

    public void add_registerUserTemporary() {
        addRequestHandler((Request request)-> analiseRequest(REQUEST_REGISTER_USER_TEMPORARY,request,(requestArg)->{
            RegisterData loginData = ReadObjectFromJson.read(requestArg.getData(), RegisterData.class);
            Optional<Response> response = registerService.registerTemporaryUser(loginData);
            return response;
        }));
    }

    public void add_isValidRegisterCode(){

    }




    @Override
    public void registerHandlersForClass() {
        add_registerUserTemporary();
    }


}
