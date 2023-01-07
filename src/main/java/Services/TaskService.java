package Services;

import Data_mapperJsonClass.*;
import Database.QueryManager;
import Database.SQL.SQLQuery;
import JSONUtility.CodeResponse;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TaskService {




    public Optional<Response> getAllTaskForGroup(GroupId idGroup){
        Optional<Response> result= QueryManager.getFromSQL(SQLQuery.GET_ALL_TASK_FOR_GROUP, Arrays.asList(new Integer[]{idGroup.getGroupId()
                }), Arrays.asList(new Class[]{Integer.class}),
                (resultArg)->{
                    List<Event> groupData=new ArrayList<>();
                    System.out.println("RESULT " + resultArg);
                    try {

                        if(resultArg.next()){
                            Integer idTask= resultArg.getInt(1);
                            String name = resultArg.getString(2);
                            String description= resultArg.getString(3);
                            Timestamp date = resultArg.getTimestamp(4);
                            LocalDateTime date2 = resultArg.getTimestamp(5).toLocalDateTime();
                            String login = resultArg.getString(6);
                            String groupName = resultArg.getString(7);

                            groupData.add(new Event(name, groupName, date2, description, "", login, idTask,idGroup.getGroupId()));

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    String json= null;
                    try {
                        json = SaveDataAsJson.save(groupData);
                        return Optional.of(new Response(json,RequestType.GET_ALL_TASK_FOR_GROUP.getNameRequest()));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                });

        return  result;
    }




    public Optional<Response> addTaskToGroup(AddTaskToGroupData registerData) {
        List<Object> dataAddTaskToGroup=new ArrayList<Object>(){
            {
                add(registerData.getIdUser());
                add(registerData.getIdGroup());
            }
        };

        List<Class> typeDataAddTaskToGroup=new ArrayList<Class>(){{
            add(Integer.class);
            add(Integer.class);

        }};

        QueryManager.executeQuery(SQLQuery.ADD_TASK_TO_GROUP,dataAddTaskToGroup,typeDataAddTaskToGroup);
        return Optional.of(CodeResponse.OK.getResponseForCode());
    }


    public Optional<Response> addTaskToUser(AddTaskToUser registerData) {
        List<Object> addTaskToUserData=new ArrayList<Object>(){
            {
                add(registerData.getIdUser());
                add(registerData.getIdTask());
                add(registerData.getIsDisplay());

            }
        };

        List<Class> typeAddTaskToUserData=new ArrayList<Class>(){{
            add(Integer.class);
            add(Integer.class);
            add(Integer.class);

        }};

        QueryManager.executeQuery(SQLQuery.ADD_TASK_TO_USER,addTaskToUserData,typeAddTaskToUserData);
        return Optional.of(CodeResponse.OK.getResponseForCode());
    }


    public Optional<Response> updateTask(TaskData registerData) {
        List<Object> dataUpdateTask=new ArrayList<Object>(){
            {
                add(registerData.getName());
                add(registerData.getDescription());
                add(registerData.getCreateDate());
                add(registerData.getDateOfNotification());
                add(registerData.getIdTask());
            }
        };

        List<Class> typeDataUpdateTask=new ArrayList<Class>(){{
            add(String.class);
            add(String.class);
            add(Timestamp.class);
            add(Timestamp.class);
            add(Integer.class);
        }};

        QueryManager.executeQuery(SQLQuery.EDIT_TASK,dataUpdateTask,typeDataUpdateTask);
        return Optional.of(CodeResponse.OK.getResponseForCode());
    }

    public Optional<Response> addTask(TaskData registerData) {
        List<Object> addTaskData=new ArrayList<Object>(){
            {
                add(registerData.getName());
                add(registerData.getDescription());
                add(registerData.getCreateDate());
                add(registerData.getDateOfNotification());
            }
        };

        List<Class> updateTaskData=new ArrayList<Class>(){{
            add(String.class);
            add(String.class);
            add(Timestamp.class);
            add(Timestamp.class);

        }};

        QueryManager.executeQuery(SQLQuery.ADD_TASK,addTaskData,updateTaskData);


        Optional<Response> result= QueryManager.getFromSQL(SQLQuery.GET_MAX_ID_TASK, new ArrayList<>(),new ArrayList<>(),
                (resultArg)->{
                    try {
                        if(resultArg.next()){
                            int idTaskMaxV= resultArg.getInt(1);
                            IdTask idTaskMax=new IdTask(idTaskMaxV);
                            String json=SaveDataAsJson.save(idTaskMax);
                            return Optional.of( new Response(json,RequestType.ADD_NEW_TASK.getNameRequest()));

                        }else{


                        }
                    } catch (SQLException | JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return Optional.of(CodeResponse.OK.getResponseForCode());
                });

        return result;

    }
    public Optional<Response> getAllTaskForUser(UserID idGroup){
        Optional<Response> result= QueryManager.getFromSQL(SQLQuery.GET_ALL_TASK_FOR_USER, Arrays.asList(new String[]{idGroup.getUserID()
                }),Arrays.asList(new Class[]{String.class}),
                (resultArg)->{
                    List<TaskData> groupData=new ArrayList<>();
                    try {

                        if(resultArg.next()){
                            Integer idTask= resultArg.getInt(1);
                            String name= resultArg.getString(2);
                            String description= resultArg.getString(3);
                            Timestamp createDate= resultArg.getTimestamp(4);
                            Timestamp dateNotification= resultArg.getTimestamp(5);
                            groupData.add(new TaskData(idTask,name,description,createDate,dateNotification));

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    String json= null;
                    try {
                        json = SaveDataAsJson.save(groupData);
                        return Optional.of(new Response(json,RequestType.GET_ALL_TASK_FOR_USER.getNameRequest()));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }


                });

        return  result;
    }
}
