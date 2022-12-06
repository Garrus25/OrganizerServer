import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class RequestParser {

    private LoginService loginService;

    public RequestParser() throws SQLException {
        this.loginService=new LoginService();
    }


    public Optional<String> parserRequest(Request requestText) throws JsonProcessingException {


        //ObjectMapper mapper = new ObjectMapper();



      //  Request me = mapper.readValue(requestText, Request.class);



        System.out.println(requestText.getHeader()+"xd");
        Optional<String> response=Optional.empty();

        switch (requestText.getHeader()){
            case "ifUserLoginAvailable":
                System.out.println("Czy login?");
                response= Optional.of(loginService.ifUserLoginAvailable(requestText.getData()));
            break;
        }



        return response;

    }

}
