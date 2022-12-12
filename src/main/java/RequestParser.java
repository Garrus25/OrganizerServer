import Data.Request;
import Data.Response;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class RequestParser {

    List<Function<Request, Optional<Response>>> requestHandlers;
    LoginRequestHandler loginRequestHandler;

    private void registerRequestHandler(List<Function<Request,Optional<Response>>> handlers){
        requestHandlers.addAll(handlers);
    }


    private void registerClassRequestParser(){
        registerRequestHandler(loginRequestHandler.getRequestsHandler());
    }

    public RequestParser() throws SQLException {
       requestHandlers=new ArrayList<>();
       loginRequestHandler=new LoginRequestHandler();
       registerClassRequestParser();
       System.out.println(requestHandlers.size());
    }



    public Optional<Response> requestParser(Request request){

        for(Function<Request,Optional<Response>> x:requestHandlers){
         Optional<Response> resuta=   x.apply(request);

         if(resuta.isPresent()){
             return resuta;
         }

        }
        return Optional.empty();
    }

  /*  public Optional<String> parserRequest(Request requestText) throws JsonProcessingException {


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

    }*/

}
