package HandlerRequest;

import Data.*;
import JSONUtility.ReadObjectFromJson;
import Services.TaskService;

import java.util.Optional;

public class TaskRequestHandler extends RequestService{

    private TaskService taskService;

    public void add_getAllTaskForGroup() {
        addRequestHandler((Request request)-> analiseRequest("getAllTaskForGroup",request,(requestParam)->{
            GroupId loginData = ReadObjectFromJson.read(requestParam.getData(), GroupId.class);
            Optional<Response> response = taskService.getAllTaskForGroup(loginData);
            return response;
        }));
    }

    public void add_getAllTaskForUser() {
        addRequestHandler((Request request)-> analiseRequest("getAllTaskForUser",request,(requestParam)->{
            UserID loginData = ReadObjectFromJson.read(requestParam.getData(), UserID.class);
            Optional<Response> response = taskService.getAllTaskForUser(loginData);
            return response;
        }));
    }

    public void add_addtask() {
        addRequestHandler((Request request)-> analiseRequest("addTask",request,(requestParam)->{
            TaskData loginData = ReadObjectFromJson.read(requestParam.getData(), TaskData.class);
            Optional<Response> response = taskService.addTask(loginData);
            return response;
        }));
    }

    public void add_addtaskToUser() {
        addRequestHandler((Request request)-> analiseRequest("addTaskToUser",request,(requestParam)->{
            AddTaskToUser loginData = ReadObjectFromJson.read(requestParam.getData(), TaskData.class);
            Optional<Response> response = taskService.addTaskToUser(loginData);
            return response;
        }));
    }


    public void add_addtaskToGRoup() {
        addRequestHandler((Request request)-> analiseRequest("addTaskToGroup",request,(requestParam)->{
            AddTaskToGroupData loginData = ReadObjectFromJson.read(requestParam.getData(), AddTaskToGroupData.class);
            Optional<Response> response = taskService.addTaskToGroup(loginData);
            return response;
        }));
    }

    public void add_updatetask() {
        addRequestHandler((Request request)-> analiseRequest("updateTask",request,(requestParam)->{
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
        add_addtask();
        add_updatetask();
        add_addtaskToGRoup();
        add_addtaskToUser();
    }
}
