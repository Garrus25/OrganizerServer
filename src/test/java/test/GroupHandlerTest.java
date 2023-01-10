package test;

import Data_mapperJsonClass.*;
import JSONUtility.CodeResponse;
import JSONUtility.ReadObjectFromJson;
import JSONUtility.SaveDataAsJson;
import Utility.Requests;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupHandlerTest {


    @Test void getUserGroups() throws JsonProcessingException {
        UserID idUser=new UserID("1");

        Request request=new Request(RequestType.GET_ALL_GROUP_DATA.getNameRequest(), SaveDataAsJson.save(idUser));
        Optional<Response> response= Requests.make2(request);
        List<GroupData> result= ReadObjectFromJson.<GroupData>readListObject(response.get().getData(),GroupData.class);
        GroupData x=new GroupData(1,"testowaGrupa","TST1");
        List<GroupData> list=new ArrayList<>();
        list.add(x);

        Assert.assertEquals(list,result);
    }

    @Test void createGroup() throws JsonProcessingException {

        GroupCreationData groupData=new GroupCreationData("testowaGrupa","TST1");
        Request request=new Request(RequestType.CREATE_GROUP.getNameRequest(), SaveDataAsJson.save(groupData));
        Optional<Response> response= Requests.make2(request);
        Assert.assertEquals(CodeResponse.OK.getResponseForCode(),response.get());
    }

    @Test void addUserToGroup() throws JsonProcessingException {
        UserGroupData groupData=new UserGroupData(1,1);
        Request request=new Request(RequestType.ADD_USER_TO_GROUP.getNameRequest(), SaveDataAsJson.save(groupData));
        Optional<Response> response= Requests.make2(request);
        Assert.assertEquals(CodeResponse.OK.getResponseForCode(),response.get());
    }

    @Test void getMembershipGroupAboutId() throws JsonProcessingException {
        GroupId groupData=new GroupId(1);
        Request request=new Request(RequestType.GET_MEMBERSHIP_GROUP_ABOUT_UD.getNameRequest(), SaveDataAsJson.save(groupData));
        Optional<Response> response= Requests.make2(request);
        List<UserData> result= ReadObjectFromJson.<UserData>readListObject(response.get().getData(),UserData.class);
        UserData x=new UserData(1,"konrad99","asdas","asd@assf","asd","asd","#asd",19,0);
        List<UserData> list=new ArrayList<>();
        list.add(x);

        Assert.assertEquals(list,result);
    }

    @Test void getAllGroup() throws JsonProcessingException{

        Request request=new Request(RequestType.GET_ALL_GROUP_DATA.getNameRequest(), SaveDataAsJson.save(""));
        Optional<Response> response= Requests.make2(request);
        List<GroupData> result= ReadObjectFromJson.<GroupData>readListObject(response.get().getData(),GroupData.class);
        GroupData x=new GroupData(1,"testowaGrupa","TST1");
        List<GroupData> list=new ArrayList<>();
        list.add(x);

        Assert.assertEquals(list,result);
    }

    @Test void removeUserFromGroup() throws JsonProcessingException {
        UserGroupData groupData=new UserGroupData(1,1);
        Request request=new Request(RequestType.REMOVE_USER_FROM_GROUP.getNameRequest(), SaveDataAsJson.save(groupData));
        Optional<Response> response= Requests.make2(request);
        Assert.assertEquals(CodeResponse.OK.getResponseForCode(),response.get());
    }



}
