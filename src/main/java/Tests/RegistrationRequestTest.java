package Tests;

import Data.RegisterData;
import Data.Request;
import Data.Response;
import Data.UserData;
import JSONUtility.ReadObjectFromJson;
import JSONUtility.SaveDataAsJson;
import Tests.UtilityTest.Requests;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;

import java.util.Optional;

public class RegistrationRequestTest {

    @Test
    void checkRegistrationTemporary() throws JsonProcessingException {

        RegisterData registerData = new RegisterData(1,"konrad99","asdas","asd@assf","asd","asd","#asd",19,false);
        Request request = new Request("registerUserTemporary", SaveDataAsJson.saveDataAsJson(registerData));
        Optional<Response> response= Requests.make(request);
        System.out.println(response.get().getData());

    }

}
