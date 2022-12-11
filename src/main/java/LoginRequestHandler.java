import Data.LoginData;
import JSONUtility.ReadObjectFromJson;
import Data.Request;
import Data.Response;
import Services.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.Optional;

public class LoginRequestHandler extends RequestService {
    private LoginService loginService;

    public LoginRequestHandler() throws SQLException {
        this.loginService=new LoginService();
    }


    public void add_isLoginAvailavle() {
        addRequestHandler((Request x)->{
            try {
                LoginData loginData= ReadObjectFromJson.read(x.getData(),LoginData.class);
                Optional<Response> response=loginService.ifUserLoginAvailable(loginData.getLogin());
                return response;
            } catch (JsonProcessingException | SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


    

}
