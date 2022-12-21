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

public class TaskService {




    public Optional<Response> getAllTaskForGroup(GroupId idGroup){
        Optional<Response> resultx= QueryManager.getRSFromSQL(SQLQuery.GET_ALL_TASK_FOR_GROUP, Arrays.asList(new Integer[]{idGroup.getGroupId()
                }),Arrays.asList(new Class[]{Integer.class}),
                (result)->{
                    List<TaskData> groupData=new ArrayList<>();
                    try {

                        if(result.next()){


                            // TASK.ID_TASK,TASK.NAME,TASK.DESCRIPTION,TASK.CREATE_DATE,TASK.DATE_OF_NOTIFICATION
                            Integer idTask= result.getInt(1);
                            String name= result.getString(2);
                            String description= result.getString(3);
                            String createDate= result.getString(4);
                            String dateNotification= result.getString(5);

                            groupData.add(new TaskData(idTask,name,description,createDate,dateNotification));

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




    public Optional<Response> addTaskToGroup(AddTaskToGroupData registerData) {
        List<Object> dataRegister=new ArrayList<Object>(){
            {
                add(registerData.getIdtask());
                add(registerData.getIdUser());
                add(registerData.getIdGroup());

            }
        };

        List<Class> dataRegisterColumnType=new ArrayList<Class>(){{


            add(Integer.class);
            add(Integer.class);
            add(Integer.class);


        }};

        QueryManager.executeQuery(SQLQuery.ADD_TASK_TO_GROUP,dataRegister,dataRegisterColumnType);
        return Optional.of(new Response("addtaskToGroup","{Register:YES}"));
    }


    public Optional<Response> addTaskToUser(AddTaskToUser registerData) {
        List<Object> dataRegister=new ArrayList<Object>(){
            {
                add(registerData.getIdUSer());
                add(registerData.getIdTask());
                add(registerData.getIsDisplay());

            }
        };

        List<Class> dataRegisterColumnType=new ArrayList<Class>(){{


            add(Integer.class);
            add(Integer.class);
            add(Integer.class);


        }};

        QueryManager.executeQuery(SQLQuery.ADD_TASK_TO_USER,dataRegister,dataRegisterColumnType);
        return Optional.of(new Response("addtaskToGroup","{Register:YES}"));
    }


    public Optional<Response> updateTask(TaskData registerData) {
        List<Object> dataRegister=new ArrayList<Object>(){
            {
                add(registerData.getName());
                add(registerData.getDescsription());
                add(registerData.getCreateDate());
                add(registerData.getDateOfNotifivation());
                add(registerData.getIdTask());
            }
        };

        List<Class> dataRegisterColumnType=new ArrayList<Class>(){{


            add(String.class);
            add(String.class);
            add(String.class);
            add(String.class);
            add(Integer.class);


        }};

        QueryManager.executeQuery(SQLQuery.EDIT_TASK,dataRegister,dataRegisterColumnType);
        return Optional.of(new Response("updateTeask","{Register:YES}"));
    }

    public Optional<Response> addTask(TaskData registerData) {
        List<Object> dataRegister=new ArrayList<Object>(){
            {

                add(registerData.getName());
                add(registerData.getDescsription());
                add(registerData.getCreateDate());
                add(registerData.getDateOfNotifivation());
            }
        };

        List<Class> dataRegisterColumnType=new ArrayList<Class>(){{


            add(String.class);
            add(String.class);
            add(String.class);
            add(String.class);


        }};

        QueryManager.executeQuery(SQLQuery.ADD_TASK,dataRegister,dataRegisterColumnType);
        return Optional.of(new Response("addTask","{Register:YES}"));
    }
    public Optional<Response> getAllTaskForUser(UserID idGroup){
        Optional<Response> resultx= QueryManager.getRSFromSQL(SQLQuery.GET_ALL_TASK_FOR_USER, Arrays.asList(new String[]{idGroup.getUserID()
                }),Arrays.asList(new Class[]{String.class}),
                (result)->{
                    List<TaskData> groupData=new ArrayList<>();
                    try {

                        if(result.next()){


                            // TASK.ID_TASK,TASK.NAME,TASK.DESCRIPTION,TASK.CREATE_DATE,TASK.DATE_OF_NOTIFICATION
                            Integer idTask= result.getInt(1);
                            String name= result.getString(2);
                            String description= result.getString(3);
                            String createDate= result.getString(4);
                            String dateNotification= result.getString(5);

                            groupData.add(new TaskData(idTask,name,description,createDate,dateNotification));

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                    String json= null;
                    try {
                        json = SaveDataAsJson.saveDataAsJson(groupData);
                        return Optional.of(new Response(json,"getAllTaskForUser"));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }



                });

        return  resultx;
    }
}
