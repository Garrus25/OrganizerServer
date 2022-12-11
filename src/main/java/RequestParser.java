import Data.Request;
import Data.Response;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class RequestParser {

    List<Function<Request, Response>> requestHandlers;


    private void registerRequestHandler(List<Function<Request,Response>> handlers){

    }

    public RequestParser() throws SQLException {
       requestHandlers=new ArrayList<>();
    }



    public Optional<String> requestParser(Request request){
        List<Function<Request,Optional<String>>> req=services.getRequestsHandler();
        for(Function<Request,Optional<String>> x:req){
         Optional<String> resuta=   x.apply(request);

         if(resuta.isPresent()){
             return resuta;
         }

        }
        return Optional.empty();
    }

    public Optional<String> parserRequest(Request requestText) throws JsonProcessingException {


        //ObjectMapper mapper = new ObjectMapper();



      //  Data.Request me = mapper.readValue(requestText, Data.Request.class);



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
