import Data.Request;
import Data.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class RequestService {
   private  List<Function<Request, Optional<Response>>> listRequestsHandler=new ArrayList<>();

   public void addRequestHandler(Function<Request,Optional<Response>> requestHandler)  {
       listRequestsHandler.add(requestHandler);
   }

   public List<Function<Request,Optional<Response>>> getRequestsHandler(){
       return listRequestsHandler;
   }
}
