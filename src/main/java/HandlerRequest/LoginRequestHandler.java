package HandlerRequest;

import Data_mapperJsonClass.*;
import JSONUtility.ReadObjectFromJson;
import Services.LoginService;

import java.sql.SQLException;
import java.util.Optional;

public class LoginRequestHandler extends RequestService {

    private LoginService loginService;

    public LoginRequestHandler() throws SQLException {
        this.loginService=new LoginService();
        registerHandlersForClass();
    }

    public void add_isLoginAvailable() {
        addRequestHandler((Request request)-> analiseRequest(RequestType.IF_USER_LOGIN_AVAILABLE.getNameRequest(), request,(requestParam)->{
                LoginData loginDataRequestParam = ReadObjectFromJson.read(requestParam.getData(), LoginData.class);
                Optional<Response>  response = loginService.ifUserLoginAvailable(loginDataRequestParam.getLogin());
                return response;
        }));
    }

    public void add_getIdUserFromLogin(){
        addRequestHandler((Request request)->analiseRequest(RequestType.GET_USER_ID_FROM_LOGIN.getNameRequest(), request,(requestParam)->{
            UserLogin loginDataRequestParam = ReadObjectFromJson.read(requestParam.getData(),UserLogin.class);
            Optional<Response> response = loginService.getUserIdFromUserLogin(loginDataRequestParam.getUserLogin());
            return response;

        }));
    }


    public void add_isUserLoginDataValid(){
        addRequestHandler((Request request)-> analiseRequest(RequestType.USER_LOGIN_DATA_VALID.getNameRequest(), request,(requestArg)->{
            LoginAndPassword loginAndPasswordRequestParam = ReadObjectFromJson.read(requestArg.getData(), LoginAndPassword.class);
            Optional< Response> response =  loginService.isUserLoginDataValid(loginAndPasswordRequestParam);
            return response;
        }));
    }


    @Override
    public void registerHandlersForClass() {
        add_isLoginAvailable();
        add_getIdUserFromLogin();
        add_isUserLoginDataValid();

    }
}
