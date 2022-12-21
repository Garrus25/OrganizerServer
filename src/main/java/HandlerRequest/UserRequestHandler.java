package HandlerRequest;

import Data.GroupCreationData;
import Data.Request;
import Data.Response;
import Data.UserID;
import JSONUtility.ReadObjectFromJson;
import Services.UserServices;

import java.util.Optional;

public class UserRequestHandler extends RequestService {

    UserServices userServices;


    public void add_getAllDataUser(){
        addRequestHandler((Request request)-> analiseRequest("getUserData",request,(requestParam)->{
            UserID loginData = ReadObjectFromJson.read(requestParam.getData(), UserID.class);
            Optional<Response> response = userServices.getAllUserData(loginData);
            return response;
        }));
    }

    public UserRequestHandler(){
        userServices=new UserServices();
        registerHandlersForClass();
    }

    @Override
    public void registerHandlersForClass() {
        add_getAllDataUser();
    }



}
