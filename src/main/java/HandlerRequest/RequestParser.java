package HandlerRequest;

import Data.Request;
import Data.Response;
import HandlerRequest.LoginRequestHandler;
import HandlerRequest.RegisterRequestHandler;

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
    private void registerRequestHandler(List<Function<Request,Optional<Response>>> handlers){
        requestHandlers.addAll(handlers);
    }


    private void registerClassRequestParser(){
        registerRequestHandler(loginRequestHandler.getRequestsHandler());
        registerRequestHandler(registerRequestHandler.getRequestsHandler());
        registerRequestHandler(groupRegisterHandler.getRequestsHandler());
        registerRequestHandler(taskRequestHandler.getRequestsHandler());
        registerRequestHandler(userRequestHandler.getRequestsHandler());
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
         Optional<Response> resultRequest=   requestHandler.apply(request);

         if(resultRequest.isPresent()){
             return resultRequest;
         }

        }
        return Optional.empty();
    }

}
