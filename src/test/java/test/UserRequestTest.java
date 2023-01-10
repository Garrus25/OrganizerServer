package test;

import Data_mapperJsonClass.*;
import JSONUtility.ReadObjectFromJson;
import JSONUtility.SaveDataAsJson;
import Utility.Requests;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class UserRequestTest {

    @Test
    void checkGetDataUser() throws JsonProcessingException {
        UserID idUser=new UserID("19");
        Request request=new Request(RequestType.GET_USER_DATA.getNameRequest(), SaveDataAsJson.save(idUser));
        Optional<Response> response= Requests.make2(request);
        System.out.println(":"+response.get().getData());
        Integer idUserData= 1;
        String login= "konrad99";
        String password= "asdas";
        String email= "asd@assf";
        String name= "asd";
        String surname= "asd";
        String color= "#asd";
        Integer authorization= 19;
        Integer isActive=1;
        UserData userMock=new UserData(idUserData,login,password,email,name,surname,color,authorization,isActive);

        UserData userData= ReadObjectFromJson.read(response.get().getData(),UserData.class);
        Assert.assertEquals(userData,userMock);
    }
}
