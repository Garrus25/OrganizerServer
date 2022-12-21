package Services;

import Data.*;
import Database.QueryManager;
import Database.SQL.SQLQuery;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GroupService {



    public Optional<Response> addUserToGroup(AddUserToGroupData groupData){
        List<Object> dataRegister=new ArrayList<Object>(){
            {
                add(groupData.getIdUser());
                add(groupData.getIdGroup());

            }
        };

        List<Class> dataRegisterColumnType=new ArrayList<Class>(){{

            add(Integer.class);
            add(Integer.class);


        }};

        QueryManager.executeQuery(SQLQuery.ADD_USER_TO_GROUP,dataRegister,dataRegisterColumnType);
        return Optional.of(new Response("adduserToGroup","{Register:YES}"));

    }

    public Optional<Response> createGroup(GroupCreationData groupData){
        List<Object> dataRegister=new ArrayList<Object>(){
            {
                add(groupData.getNameGroup());
                add(groupData.getCodeGroup());

            }
        };

        List<Class> dataRegisterColumnType=new ArrayList<Class>(){{

            add(String.class);
            add(String.class);


        }};

        QueryManager.executeQuery(SQLQuery.CREATE_GROUP,dataRegister,dataRegisterColumnType);
        return Optional.of(new Response("createGroup","{Register:YES}"));

    }


    public Optional<Response> removeUserFromGroup(AddUserToGroupData data){
        List<Object> dataRegister=new ArrayList<Object>(){
            {
                add(data.getIdUser());
                add(data.getIdGroup());

            }
        };

        List<Class> dataRegisterColumnType=new ArrayList<Class>(){{

            add(Integer.class);
            add(Integer.class);


        }};
        QueryManager.executeQuery(SQLQuery.REMOVE_USER_FROM_GROUP,dataRegister,dataRegisterColumnType);
        return Optional.of(new Response("createGroup","{Register:YES}"));
    }

    public Optional<Response> getMembershipGroup(GroupId idGroup){
        Optional<Response> resultx= QueryManager.getRSFromSQL(SQLQuery.GET_ALL_MEMBERSHIP_GROUP_ABOUT_ID, Arrays.asList(new Integer[]{idGroup.getGroupId()
                        }),Arrays.asList(new Class[]{Integer.class}),
                (result)->{
                    List<UserData> groupData=new ArrayList<>();
                    try {

                        if(result.next()){
//SELECT user.ID_USER,user.LOGIN,user.PASSWORD,user.EMAIL,user.NAME,user.SURNAME,user.COLOR,user.AUTHENTICATION_TOKEN,user.IS_ACTIVE FROM GROUPS join groups_users on groups_users.ID_GROUP=groups.ID_GROUP join user on user.ID_USER=groups_users.ID_USER where groups.ID_GROUP=?
                            Integer idUser= result.getInt(1);
                            String login= result.getString(2);
                            String password= result.getString(3);
                            String email= result.getString(4);
                            String name= result.getString(5);
                            String surname= result.getString(6);
                            String color= result.getString(7);
                            Integer authorization=result.getInt(8);
                            Integer isActive= result.getInt(9);
                            groupData.add(new UserData(idUser,login,password,email,name,surname,color,authorization,isActive));

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                    String json= null;
                    try {
                        json = SaveDataAsJson.saveDataAsJson(groupData);
                        return Optional.of(new Response(json,"getMembershipGroupAboutId"));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }



                });

        return  resultx;
    }

    public Optional<Response> getAllDataGroup(){




        Optional<Response> resultx= QueryManager.getRSFromSQL(SQLQuery.GET_ALL_GROUP_DATA, new ArrayList<>(),new ArrayList<>(),
                (result)->{
                    List<GroupData> groupData=new ArrayList<>();
                    try {



                        if(result.next()){

                            Integer idGroup= result.getInt(1);
                            String nameGroup= result.getString(2);
                            String codeGroup= result.getString(3);
                            groupData.add(new GroupData(idGroup,nameGroup,codeGroup));

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                    String json= null;
                    try {
                        json = SaveDataAsJson.saveDataAsJson(groupData);
                        return Optional.of(new Response(json,"getGroupData"));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }



                });

        return  resultx;
    }

}
