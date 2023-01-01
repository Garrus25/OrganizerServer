package HandlerRequest;

import Data.*;
import JSONUtility.ReadObjectFromJson;
import Services.TaskService;

import java.util.Optional;

public class TaskRequestHandler extends RequestService{

    private TaskService taskService;

    public void add_getAllTaskForGroup() {
        addRequestHandler((Request request)-> analiseRequest(RequestType.GET_ALL_TASK_FOR_GROUP.getNameRequest(),request,(requestParam)->{
            GroupId loginData = ReadObjectFromJson.read(requestParam.getData(), GroupId.class);
            Optional<Response> response = taskService.getAllTaskForGroup(loginData);
            return response;
        }));
    }

    public void add_getAllTaskForUser() {
        addRequestHandler((Request request)-> analiseRequest(RequestType.GET_ALL_TASK_FOR_USER.getNameRequest(), request,(requestParam)->{
            UserID loginData = ReadObjectFromJson.read(requestParam.getData(), UserID.class);
            Optional<Response> response = taskService.getAllTaskForUser(loginData);
            return response;
        }));
    }

    public void add_addTask() {
        addRequestHandler((Request request)-> analiseRequest(RequestType.ADD_NEW_TASK.getNameRequest(), request,(requestParam)->{
            TaskData loginData = ReadObjectFromJson.read(requestParam.getData(), TaskData.class);
            Optional<Response> response = taskService.addTask(loginData);
            return response;
        }));
    }

    public void add_addTaskToUser() {
        addRequestHandler((Request request)-> analiseRequest(RequestType.ADD_TASK_TO_USER.getNameRequest(),request,(requestParam)->{
            AddTaskToUser loginData = ReadObjectFromJson.read(requestParam.getData(), AddTaskToUser.class);
            Optional<Response> response = taskService.addTaskToUser(loginData);
            return response;
        }));
    }


    public void add_addTaskToGroup() {
        addRequestHandler((Request request)-> analiseRequest(RequestType.ADD_TASK_TO_GROUP.getNameRequest(), request,(requestParam)->{
            AddTaskToGroupData loginData = ReadObjectFromJson.read(requestParam.getData(), AddTaskToGroupData.class);
            Optional<Response> response = taskService.addTaskToGroup(loginData);
            return response;
        }));
    }

    public void add_updateTask() {
        addRequestHandler((Request request)-> analiseRequest(RequestType.UPDATE_TASK.getNameRequest(), request,(requestParam)->{
            TaskData loginData = ReadObjectFromJson.read(requestParam.getData(), TaskData.class);
            Optional<Response> response = taskService.updateTask(loginData);
            return response;
        }));
    }


    public TaskRequestHandler(){
        taskService=new TaskService();
        registerHandlersForClass();

    }

    @Override
    public void registerHandlersForClass() {
        add_getAllTaskForGroup();
        add_getAllTaskForUser();
        add_addTask();
        add_updateTask();
        add_addTaskToGroup();
        add_addTaskToUser();
    }
}
