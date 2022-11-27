import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.Optional;

public class RequestParser {

    private LoginService loginService;

    public RequestParser() throws SQLException {
        this.loginService=new LoginService();
    }


    public Optional<String> parserRequest(String requestText) throws JsonProcessingException {

        Optional<String> response=Optional.empty();

        switch (requestText){
            case "IfUserLoginAvailable":
                response= Optional.of(loginService.ifUserLoginAvailable());
            break;
        }



        return response;

    }

}
