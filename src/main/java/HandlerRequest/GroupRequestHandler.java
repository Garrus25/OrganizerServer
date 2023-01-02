package HandlerRequest;

import Data.*;
import JSONUtility.ReadObjectFromJson;
import Services.GroupService;

import java.util.Optional;

public class GroupRequestHandler extends RequestService {

    private GroupService groupService;


    public GroupRequestHandler(){
        groupService =new GroupService();
        registerHandlersForClass();

    }


    public void add_getMembershipGroupAboutId() {
        addRequestHandler((Request request)-> analiseRequest(RequestType.GET_MEMBERSHIP_GROUP_ABOUT_UD.getNameRequest(), request,(requestParam)->{
            GroupId groupIdParamRequest = ReadObjectFromJson.read(requestParam.getData(), GroupId.class);
            Optional<Response>  response = groupService.getMembershipGroup(groupIdParamRequest);
            return response;
        }));
    }

      public void add_getAllGroup() {
              addRequestHandler((Request request)-> analiseRequest(RequestType.GET_ALL_GROUP_DATA.getNameRequest(), request,(requestParam)->{
              Optional<Response> response = groupService.getAllDataGroup();
              return response;
          }));
      }


      public void add_createGroup(){
          addRequestHandler((Request request)-> analiseRequest(RequestType.CREATE_GROUP.getNameRequest(),request,(requestParam)->{
              GroupCreationData groupCreationDataParamRequest = ReadObjectFromJson.read(requestParam.getData(), GroupCreationData.class);
              Optional<Response> response = groupService.createGroup(groupCreationDataParamRequest);
              return response;
          }));
      }


    public void add_addUserToGroup(){
        addRequestHandler((Request request)-> analiseRequest(RequestType.ADD_USER_TO_GROUP.getNameRequest(), request,(requestParam)->{
            UserGroupData addUserToGroupDataParamRequest = ReadObjectFromJson.read(requestParam.getData(), UserGroupData.class);
            Optional<Response> response = groupService.addUserToGroup(addUserToGroupDataParamRequest);
            return response;
        }));
    }


    public void add_removeUserFromGroup(){
        addRequestHandler((Request request)-> analiseRequest(RequestType.REMOVE_USER_FROM_GROUP.getNameRequest(), request,(requestParam)->{
            UserGroupData removeUserFromGroupDataParamRequest = ReadObjectFromJson.read(requestParam.getData(), UserGroupData.class);
            Optional<Response> response = groupService.removeUserFromGroup(removeUserFromGroupDataParamRequest);
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

