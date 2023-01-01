package Tests;

import Data.*;
import JSONUtility.CodeResponse;
import JSONUtility.ReadObjectFromJson;
import JSONUtility.SaveDataAsJson;
import Tests.UtilityTest.Requests;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.util.TimeUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class TaskRequestTest {

    @Test void addNewTask() throws JsonProcessingException {


        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        TaskData taskData=new TaskData(2,"Zrobić","Coś zrobić",date,date);
        Request request=new Request(RequestType.ADD_NEW_TASK.getNameRequest(), SaveDataAsJson.saveDataAsJson(taskData));
        Optional<Response> response= Requests.make2(request);
        Assert.assertEquals(CodeResponse.OK.getResponseForCode(),response.get());
    }

    @Test void addTaskToUser() throws JsonProcessingException {
        AddTaskToUser addTaskToUser=new AddTaskToUser(1,1,1);
        Request request=new Request(RequestType.ADD_TASK_TO_USER.getNameRequest(), SaveDataAsJson.saveDataAsJson(addTaskToUser));
        Optional<Response> response= Requests.make2(request);
        Assert.assertEquals(CodeResponse.OK.getResponseForCode(),response.get());
    }


    @Test void getAllTaskUser() throws JsonProcessingException {
        UserID idUser=new UserID("1");
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        Request request=new Request(RequestType.GET_ALL_TASK_FOR_USER.getNameRequest(), SaveDataAsJson.saveDataAsJson(idUser));
        Optional<Response> response= Requests.make2(request);
        List<TaskData> listTask=new ArrayList<>();
        TaskData taskDataMock=new TaskData(1,"Zrobić","Coś zrobić",date,date);

        listTask.add(taskDataMock);


        List<TaskData> result= ReadObjectFromJson.<TaskData>readListObject(response.get().getData(),TaskData.class);

        for(TaskData x:result){
            System.out.println(x);
        }

        Assert.assertEquals(result,listTask);



    }
}
