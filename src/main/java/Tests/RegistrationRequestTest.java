package Tests;

import Data.*;
import JSONUtility.CodeResponse;
import JSONUtility.ReadObjectFromJson;
import JSONUtility.SaveDataAsJson;
import Tests.UtilityTest.Requests;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class RegistrationRequestTest {

    @Test
    void checkRegistrationTemporary() throws JsonProcessingException {

        RegisterData registerData = new RegisterData(1,"konrad99","asdas","asd@assf","asd","asd","#asd",19,false);
        Request request = new Request(RequestType.REGISTER_USER_TEMPORARY.getNameRequest(), SaveDataAsJson.saveDataAsJson(registerData));
        Optional<Response> response= Requests.make2(request);

        Assert.assertEquals(CodeResponse.OK.getResponseForCode(),response.get());

    }
    @Test void checkIsCodeVerficationValid_Valid() throws JsonProcessingException {
        ConfirmCodeData confirmCodeDataValid=new ConfirmCodeData("19",19);
        Request request=new Request(RequestType.IS_CODE_CONFIRM_ACCOUNT_VALID.getNameRequest(), SaveDataAsJson.saveDataAsJson(confirmCodeDataValid));
        Optional<Response> response=Requests.make2(request);
        ConfirmCodeResponse codeResp=ReadObjectFromJson.read( response.get().getData(),ConfirmCodeResponse.class);
        Assert.assertTrue(codeResp.getCodeValid());

    }

    @Test void checkIsCodeVerficationValid_NoValid() throws JsonProcessingException {
        ConfirmCodeData confirmCodeDataValid=new ConfirmCodeData("32434",19);
        Request request=new Request(RequestType.IS_CODE_CONFIRM_ACCOUNT_VALID.getNameRequest(), SaveDataAsJson.saveDataAsJson(confirmCodeDataValid));
        Optional<Response> response=Requests.make2(request);
        ConfirmCodeResponse codeResp=ReadObjectFromJson.read( response.get().getData(),ConfirmCodeResponse.class);
        Assert.assertFalse(codeResp.getCodeValid());

    }

    @Test void check_setUserAccountActive() throws JsonProcessingException {
        UserID idUser=new UserID("19");
        Request request=new Request(RequestType.REGISTER_USER.getNameRequest(),SaveDataAsJson.saveDataAsJson(idUser));
        Optional<Response> response=Requests.make2(request);
        System.out.println(":"+response.get().getData());
        System.out.println(response);

        Assert.assertEquals(CodeResponse.OK.getResponseForCode(),response.get());
    }

}
