package HandlerRequest;

import Data.CheckedFunction;
import Data.LoginData;
import Data.Request;
import Data.Response;
import JSONUtility.ReadObjectFromJson;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class RequestService {
   private  List<Function<Request, Optional<Response>>> listRequestsHandler=new ArrayList<>();

   public void addRequestHandler(Function<Request,Optional<Response>> requestHandler)  {
       listRequestsHandler.add(requestHandler);
   }

   abstract public void registerHandlersForClass();

   public List<Function<Request,Optional<Response>>> getRequestsHandler(){
       return listRequestsHandler;
   }


   public Optional<Response> analiseRequest(String nameGoalRequest, Request request, CheckedFunction<Request,Optional<Response>> func){
       try {
           if (request.getHeader().equals(nameGoalRequest)) {
               return func.apply(request);
           } else {
               return Optional.empty();
           }
       }catch (Exception exp){
            throw   new RuntimeException(exp);
       }

   }



}
