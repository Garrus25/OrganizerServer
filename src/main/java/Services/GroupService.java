package Services;

import Data_mapperJsonClass.*;
import Database.QueryManager;
import Database.SQL.SQLQuery;
import JSONUtility.CodeResponse;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonProcessingException;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GroupService {



    public Optional<Response> addUserToGroup(UserGroupData groupData){
        List<Object> dataAddUserToGroup=new ArrayList<Object>(){
            {
                add(groupData.getIdUser());
                add(groupData.getIdGroup());
            }
        };

        List<Class> addUserToGroupTypeParam=new ArrayList<Class>(){{
            add(Integer.class);
            add(Integer.class);
        }};

        QueryManager.executeQuery(SQLQuery.ADD_USER_TO_GROUP,dataAddUserToGroup,addUserToGroupTypeParam);
        return Optional.of(CodeResponse.OK.getResponseForCode());
    }

    public Optional<Response> createGroup(GroupCreationData groupData){
        List<Object> createGroupData=new ArrayList<Object>(){
            {
                add(groupData.getNameGroup());
                add(groupData.getCodeGroup());
            }
        };

        List<Class> createGroupType=new ArrayList<Class>(){{
            add(String.class);
            add(String.class);
        }};

        QueryManager.executeQuery(SQLQuery.CREATE_GROUP,createGroupData,createGroupType);
        return Optional.of(CodeResponse.OK.getResponseForCode());
    }


    public Optional<Response> removeUserFromGroup(UserGroupData data){
        List<Object> dataRemoveUserFromGroup=new ArrayList<Object>(){
            {
                add(data.getIdUser());
                add(data.getIdGroup());
            }
        };

        List<Class> typeRemoveUserFromGroup=new ArrayList<Class>(){{
            add(Integer.class);
            add(Integer.class);
        }};
        QueryManager.executeQuery(SQLQuery.REMOVE_USER_FROM_GROUP,dataRemoveUserFromGroup,typeRemoveUserFromGroup);
        return Optional.of(CodeResponse.OK.getResponseForCode());
    }



    public Optional<Response> getGroupsUser(UserID userID){

        List<Object> getGroupUSerData=new ArrayList<Object>(){
            {
                add(userID.getUserID());
            }
        };
        List<Class> getGroupUserType=new ArrayList<Class>(){{
            add(String.class);
        }};
        Optional<Response> result= QueryManager.getFromSQL(SQLQuery.GET_GROUPS_USER, getGroupUSerData,getGroupUserType
                ,(resultReq)->{
                    List<GroupData> groupData=new ArrayList<>();
                    try {

                        while(resultReq.next()){
                            int idGroup= resultReq.getInt(1);
                            String nameGroup= resultReq.getString(2);
                            String codeGroup= resultReq.getString(3);
                            groupData.add(new GroupData(idGroup,nameGroup,codeGroup));

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                    String json= null;
                    try {
                        json = SaveDataAsJson.save(groupData);
                        return Optional.of(new Response(json,RequestType.GET_ALL_GROUP_DATA.getNameRequest()));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }


                });

        return  result;
    }

    public Optional<Response> getMembershipGroup(GroupId idGroup){
        Optional<Response> result= QueryManager.getFromSQL(SQLQuery.GET_ALL_MEMBERSHIP_GROUP_ABOUT_ID, Arrays.asList(new Integer[]{idGroup.getGroupId()}),Arrays.asList(new Class[]{Integer.class})
                , (resultArg)->{

                    List<UserData> groupData=new ArrayList<>();

                    try {

                        while(resultArg.next()){
                            int idUser= resultArg.getInt(1);
                            String login= resultArg.getString(2);
                            String password= resultArg.getString(3);
                            String email= resultArg.getString(4);
                            String name= resultArg.getString(5);
                            String surname= resultArg.getString(6);
                            String color= resultArg.getString(7);
                            Integer authorization=resultArg.getInt(8);
                            Integer isActive= resultArg.getInt(9);
                            groupData.add(new UserData(idUser,login,password,email,name,surname,color,authorization,isActive));

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                    String json= null;
                    try {
                        json = SaveDataAsJson.save(groupData);
                        return Optional.of(new Response(json,RequestType.GET_MEMBERSHIP_GROUP_ABOUT_UD.getNameRequest()));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }


                });

        return  result;
    }

    public Optional<Response> getAllDataGroup(){


        Optional<Response> result= QueryManager.getFromSQL(SQLQuery.GET_ALL_GROUP_DATA, new ArrayList<>(),new ArrayList<>(),
                (resultReq)->{
                    List<GroupData> groupData=new ArrayList<>();
                    try {

                        while (resultReq.next()){
                            int idGroup= resultReq.getInt(1);
                            String nameGroup= resultReq.getString(2);
                            String codeGroup= resultReq.getString(3);
                            groupData.add(new GroupData(idGroup,nameGroup,codeGroup));

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                    String json= null;
                    try {
                        json = SaveDataAsJson.save(groupData);
                        return Optional.of(new Response(json,RequestType.GET_ALL_GROUP_DATA.getNameRequest()));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }


                });

        return  result;
    }

}
