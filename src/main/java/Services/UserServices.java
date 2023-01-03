package Services;

import Data_mapperJsonClass.RequestType;
import Data_mapperJsonClass.Response;
import Data_mapperJsonClass.UserData;
import Data_mapperJsonClass.UserID;
import Database.QueryManager;
import Database.SQL.SQLQuery;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

public class UserServices {

    public Optional<Response> getAllUserDataFromId(UserID idUser){
        Optional<Response> result= QueryManager.getFromSQL(SQLQuery.GET_ALL_USER_DATA, Arrays.asList(new String[]{idUser.getUserID()
                }),Arrays.asList(new Class[]{String.class}),
                (resultParam)->{

                    try {

                        if(resultParam.next()){
                            Integer idUserData= resultParam.getInt(1);
                            String login= resultParam.getString(2);
                            String password= resultParam.getString(3);
                            String email= resultParam.getString(4);
                            String name= resultParam.getString(5);
                            String surname= resultParam.getString(6);
                            String color= resultParam.getString(7);
                            Integer authorization=resultParam.getInt(8);
                            Integer isActive= resultParam.getInt(9);
                            UserData user=new UserData(idUserData,login,password,email,name,surname,color,authorization,isActive);
                            String json = SaveDataAsJson.save(user);
                            return Optional.of(new Response(json, RequestType.GET_USER_DATA.getNameRequest()));
                        }

                    } catch (SQLException | JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                    return Optional.empty();
                });

        return  result;
    }
}
