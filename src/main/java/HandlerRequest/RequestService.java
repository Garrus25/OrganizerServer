package HandlerRequest;

import Interfaces.CheckedFunction;
import Data_mapperJsonClass.Request;
import Data_mapperJsonClass.Response;

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
