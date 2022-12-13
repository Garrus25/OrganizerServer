import Data.LoginData;
import Data.RegisterData;
import Data.Request;
import Data.Response;
import JSONUtility.ReadObjectFromJson;
import Services.LoginService;
import Services.RegisterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Optional;

public class RegisterRequestHandler extends RequestService {
    private RegisterService registerService;
  //  private LoginService loginService;
   // private static final Logger logger = LogManager.getLogger(LoginRequestHandler.class);
    public RegisterRequestHandler() throws SQLException {
     //   this.loginService=new LoginService();
        this.registerService=new RegisterService();
        registerHandlersForClass();
    }

    public void add_registerUserTemporary() {
        addRequestHandler((Request x)-> analiseRequest("registerUserTemporary",x,(xx)->{
            RegisterData loginData = ReadObjectFromJson.read(xx.getData(), RegisterData.class);
            Optional<Response> response = registerService.registerTemporaryUser(loginData);
            return response;
        }));
    }



    @Override
    public void registerHandlersForClass() {
        add_registerUserTemporary();
    }


}
