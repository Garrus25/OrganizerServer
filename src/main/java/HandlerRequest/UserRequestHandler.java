package HandlerRequest;

import Data_mapperJsonClass.*;
import JSONUtility.ReadObjectFromJson;
import Services.UserServices;

import java.util.Optional;

public class UserRequestHandler extends RequestService {
    UserServices userServices;
    public void add_getAllDataUser(){
        addRequestHandler((Request request)-> analiseRequest(RequestType.GET_USER_DATA.getNameRequest(), request,(requestParam)->{
            UserID idUserRequestParam = ReadObjectFromJson.read(requestParam.getData(), UserID.class);
            Optional<Response> response = userServices.getAllUserDataFromId(idUserRequestParam);
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
