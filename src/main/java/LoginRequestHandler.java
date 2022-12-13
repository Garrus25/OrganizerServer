import Data.LoginData;
import JSONUtility.ReadObjectFromJson;
import Data.Request;
import Data.Response;
import Services.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Optional;

public class LoginRequestHandler extends RequestService {
    private LoginService loginService;
    private static final Logger logger = LogManager.getLogger(LoginRequestHandler.class);
    public LoginRequestHandler() throws SQLException {
        this.loginService=new LoginService();
        registerHandlersForClass();
    }

    public void add_isLoginAvailavle() {
        addRequestHandler((Request x)-> analiseRequest("ifUserLoginAvailable",x,(xx)->{
                LoginData loginData = ReadObjectFromJson.read(xx.getData(), LoginData.class);
                Optional<Response>  response = loginService.ifUserLoginAvailable(loginData.getLogin());
                return response;
        }));
    }



    @Override
    public void registerHandlersForClass() {
        add_isLoginAvailavle();
    }
}
