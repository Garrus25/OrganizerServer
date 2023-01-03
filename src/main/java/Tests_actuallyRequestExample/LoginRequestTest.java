package Tests_actuallyRequestExample;

import Data_mapperJsonClass.*;
import JSONUtility.ReadObjectFromJson;
import JSONUtility.SaveDataAsJson;
import Utility.Requests;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class LoginRequestTest {

    @Test
    void isLoginAvailable_No() throws JsonProcessingException {
        LoginData loginData=new LoginData("konrad99");
        Request request=new Request(RequestType.IF_USER_LOGIN_AVAILABLE.getNameRequest(), SaveDataAsJson.save(loginData));
        Optional<Response> response= Requests.make2(request);
        ResponseLogin responseData= ReadObjectFromJson.read( response.get().getData(),ResponseLogin.class);
        ResponseLogin correctResponseMock=new ResponseLogin("isLoginAvailable","NO");
        Assert.assertEquals(correctResponseMock,responseData);
    }

    @Test
    void isLoginAvailable_Yes() throws JsonProcessingException {
        LoginData loginData=new LoginData("konrad99xxxx");
        Request request=new Request(RequestType.IF_USER_LOGIN_AVAILABLE.getNameRequest(), SaveDataAsJson.save(loginData));
        Optional<Response> response= Requests.make2(request);
        ResponseLogin responseData= ReadObjectFromJson.read( response.get().getData(),ResponseLogin.class);
        ResponseLogin correctResponseMock=new ResponseLogin("isLoginAvailable","YES");
        Assert.assertEquals(correctResponseMock,responseData);

    }


    @Test
    public void getIdUserFromLogin() throws JsonProcessingException {
        UserLogin userLogin=new UserLogin("konrad99");
        Request request=new Request(RequestType.GET_USER_ID_FROM_LOGIN.getNameRequest(), SaveDataAsJson.save(userLogin));
        Optional<Response> response= Requests.make2(request);
        UserID responseData= ReadObjectFromJson.read( response.get().getData(),UserID.class);
        UserID mockUserIdCorrect=new UserID("5");
        Assert.assertEquals(mockUserIdCorrect,responseData);

    }

    @Test
    public void add_isUserLoginDataValid_GoodData() throws JsonProcessingException {
        LoginAndPassword loginAndPassword=new LoginAndPassword("konrad99","asdas");
        Request request=new Request(RequestType.USER_LOGIN_DATA_VALID.getNameRequest(), SaveDataAsJson.save(loginAndPassword));
        Optional<Response> response= Requests.make2(request);
        ValidLoginData responseData= ReadObjectFromJson.read( response.get().getData(),ValidLoginData.class);
        ValidLoginData mockValidLoginDataCorrect=new ValidLoginData("YES");
        Assert.assertEquals(mockValidLoginDataCorrect,responseData);
    }

    @Test
    public void add_isUserLoginDataValid_BadData() throws JsonProcessingException {
        LoginAndPassword loginAndPassword=new LoginAndPassword("konrdad99","asdasdas");
        Request request=new Request(RequestType.USER_LOGIN_DATA_VALID.getNameRequest(), SaveDataAsJson.save(loginAndPassword));
        Optional<Response> response= Requests.make2(request);
        ValidLoginData responseData= ReadObjectFromJson.read( response.get().getData(),ValidLoginData.class);
        ValidLoginData mockValidLoginDataCorrect=new ValidLoginData("NO");
        Assert.assertEquals(mockValidLoginDataCorrect,responseData);
    }
}
