package HandlerRequest;

import Data.*;
import JSONUtility.ReadObjectFromJson;
import Services.RegisterService;
import java.sql.SQLException;
import java.util.Optional;

public class RegisterRequestHandler extends RequestService {
    private RegisterService registerService;
    private String REQUEST_REGISTER_USER_TEMPORARY="registerUserTemporary";
    private String REQUEST_IS_CODE_CONFIM_ACCOUNT_VALID="isCodeVerficationValid";

    private String REQUEST_REGISTER_USER="registerUser";


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
        addRequestHandler((Request request)-> analiseRequest(REQUEST_IS_CODE_CONFIM_ACCOUNT_VALID,request,(requestArg)->{
            ConfirmCodeData loginData = ReadObjectFromJson.read(requestArg.getData(), RegisterData.class);
            Optional<Response> response = registerService.isRegistrationConfirmCodeValid(loginData);
            return response;
        }));
    }


    public void add_registerUser(){
        addRequestHandler((Request request)-> analiseRequest(REQUEST_REGISTER_USER,request,(requestArg)->{
            UserID loginData = ReadObjectFromJson.read(requestArg.getData(), RegisterData.class);
            registerService.registerUser(loginData);
           Response x=new Response("register:YES","RegisterUser");
           return  Optional.of(x);
        }));
    }





    @Override
    public void registerHandlersForClass() {
        add_registerUserTemporary();
        add_isValidRegisterCode();
        add_registerUser();
    }


}
