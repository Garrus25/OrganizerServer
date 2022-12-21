package HandlerRequest;

import Data.*;
import JSONUtility.ReadObjectFromJson;
import Services.GroupService;

import java.util.Optional;

public class GroupRequestHandler extends RequestService {

    private GroupService ser;


    public GroupRequestHandler(){
        ser=new GroupService();
        registerHandlersForClass();

    }


    //getMembershipGroupAboutId

    public void add_getMembershipGroupAboutId() {
        addRequestHandler((Request request)-> analiseRequest("getMembershipGroupAboutId",request,(requestParam)->{
            GroupId loginData = ReadObjectFromJson.read(requestParam.getData(), GroupId.class);
            Optional<Response>  response = ser.getMembershipGroup(loginData);
            return response;
        }));
    }

      public void add_getAllGroup() {
              addRequestHandler((Request request)-> analiseRequest("getAllDataGroup",request,(requestParam)->{
              Optional<Response> response = ser.getAllDataGroup();
              return response;
          }));
      }


      public void add_createGroup(){
          addRequestHandler((Request request)-> analiseRequest("createGroup",request,(requestParam)->{
              GroupCreationData loginData = ReadObjectFromJson.read(requestParam.getData(), GroupCreationData.class);
              Optional<Response> response = ser.createGroup(loginData);
              return response;
          }));
      }


    public void add_addUserToGroup(){
        addRequestHandler((Request request)-> analiseRequest("adduserToGroup",request,(requestParam)->{
            AddUserToGroupData loginData = ReadObjectFromJson.read(requestParam.getData(), GroupCreationData.class);
            Optional<Response> response = ser.addUserToGroup(loginData);
            return response;
        }));
    }


    public void add_removeUserFromGroup(){
        addRequestHandler((Request request)-> analiseRequest("removeUserFromGroup",request,(requestParam)->{
            AddUserToGroupData loginData = ReadObjectFromJson.read(requestParam.getData(), GroupCreationData.class);
            Optional<Response> response = ser.removeUserFromGroup(loginData);
            return response;
        }));
    }

    @Override
    public void registerHandlersForClass() {
        add_getAllGroup();
        add_getMembershipGroupAboutId();
        add_createGroup();
        add_addUserToGroup();
        add_removeUserFromGroup();
    }
}

//getAllDataGroup