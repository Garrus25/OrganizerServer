package HandlerRequest;

import Data.*;
import JSONUtility.ReadObjectFromJson;
import Services.RegisterService;
import java.util.Optional;
public class RegisterRequestHandler extends RequestService {
    private final RegisterService registerService;

    public RegisterRequestHandler() {
        this.registerService = new RegisterService();
        registerHandlersForClass();
    }

    public void add_registerUserTemporary() {
        addRequestHandler((Request request)-> analiseRequest(RequestType.REGISTER_USER_TEMPORARY.getNameRequest(),request,(requestArg)->{
            RegisterData registerTempData = ReadObjectFromJson.read(requestArg.getData(), RegisterData.class);
            Optional<Response> response = registerService.registerTemporaryUser(registerTempData);
            return response;
        }));
    }

    public void add_isValidRegisterCode(){
        addRequestHandler((Request request)-> analiseRequest(RequestType.IS_CODE_CONFIRM_ACCOUNT_VALID.getNameRequest(), request,(requestArg)->{
            System.out.println("Działam isValidConfirmCode");
            ConfirmCodeData isValidRegisterCodeData = ReadObjectFromJson.read(requestArg.getData(), ConfirmCodeData.class);
            Optional<Response> response = registerService.isRegistrationConfirmCodeValid(isValidRegisterCodeData);
            return response;
        }));
    }


    public void add_registerUser(){
        addRequestHandler((Request request)-> analiseRequest(RequestType.REGISTER_USER.getNameRequest(), request,(requestArg)->{
            UserID registerIdUserData = ReadObjectFromJson.read(requestArg.getData(), UserID.class);
            Response response = registerService.registerUser(registerIdUserData);
            return  Optional.of(response);
        }));
    }



    @Override
    public void registerHandlersForClass() {
        add_registerUserTemporary();
        add_isValidRegisterCode();
        add_registerUser();
    }


}
