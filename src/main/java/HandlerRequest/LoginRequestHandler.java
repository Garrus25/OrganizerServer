package HandlerRequest;

import Data.*;
import JSONUtility.ReadObjectFromJson;
import Services.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.util.Optional;

public class LoginRequestHandler extends RequestService {

    private LoginService loginService;

    private String REQUEST_IF_USER_LOGIN_AVAILABLE="ifUserLoginAvailable";
    private String REQUEST_GET_USER_ID_FROM_LOGIN="getIdUserFromLogin";

    private String REQUEST_USER_LOGIN_DATA_VALID="isLoginDataValid";
    public LoginRequestHandler() throws SQLException {
        this.loginService=new LoginService();
        registerHandlersForClass();
    }

    public void add_isLoginAvailavle() {
        addRequestHandler((Request request)-> analiseRequest(RequestType.REQUEST_IF_USER_LOGIN_AVAILABLE.getNameRequest(), request,(requestParam)->{
                LoginData loginData = ReadObjectFromJson.read(requestParam.getData(), LoginData.class);
                Optional<Response>  response = loginService.ifUserLoginAvailable(loginData.getLogin());
                return response;
        }));
    }

    public void add_getIdUserFromLogin(){
        addRequestHandler((Request request)->analiseRequest(REQUEST_GET_USER_ID_FROM_LOGIN,request,(requestParam)->{
            UserLogin loginData=ReadObjectFromJson.read(requestParam.getData(),UserLogin.class);
            Optional<Response> response =loginService.getUserIdFromUserLogin(loginData.getUserLogin());
            return response;

        }));
    }


    public void add_isUserLoginDataValid(){
        addRequestHandler((Request request)-> analiseRequest(REQUEST_USER_LOGIN_DATA_VALID,request,(requestArg)->{
            LoginAndPassword loginData = ReadObjectFromJson.read(requestArg.getData(), LoginAndPassword.class);

           Optional< Response> x=  loginService.isUserLoginDataValid(loginData);
            return x;
        }));
    }
    //isUserLoginDataValid


    @Override
    public void registerHandlersForClass() {
        add_isLoginAvailavle();
        add_getIdUserFromLogin();
        add_isUserLoginDataValid();

    }
}
