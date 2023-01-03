package HandlerRequest;

import Data.Request;
import Data.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class RequestParser {

    List<Function<Request, Optional<Response>>> requestHandlers;
    LoginRequestHandler loginRequestHandler;
    RegisterRequestHandler registerRequestHandler;

    GroupRequestHandler  groupRegisterHandler;

    TaskRequestHandler taskRequestHandler;
    UserRequestHandler userRequestHandler;
    private void registerRequestHandlers(List<Function<Request,Optional<Response>>> handlers){
        requestHandlers.addAll(handlers);
    }


    private void registerClassRequestParser(){
        registerRequestHandlers(loginRequestHandler.getRequestsHandler());
        registerRequestHandlers(registerRequestHandler.getRequestsHandler());
        registerRequestHandlers(groupRegisterHandler.getRequestsHandler());
        registerRequestHandlers(taskRequestHandler.getRequestsHandler());
        registerRequestHandlers(userRequestHandler.getRequestsHandler());
    }

    public RequestParser() throws SQLException {
       requestHandlers=new ArrayList<>();
       loginRequestHandler=new LoginRequestHandler();
       registerRequestHandler=new RegisterRequestHandler();
       groupRegisterHandler=new GroupRequestHandler();
       taskRequestHandler=new TaskRequestHandler();
       userRequestHandler=new UserRequestHandler();
       registerClassRequestParser();

    }



    public Optional<Response> requestParser(Request request){

        for(Function<Request,Optional<Response>> requestHandler:requestHandlers){
            System.out.println("xD"+requestHandlers.size());
         Optional<Response> resultRequest=   requestHandler.apply(request);

         if(resultRequest.isPresent()){
             return resultRequest;
         }

        }
        return Optional.empty();
    }

}
