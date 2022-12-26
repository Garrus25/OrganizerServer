package Services;

import Data.Response;
import Data.UserData;
import Data.UserID;
import Database.QueryManager;
import Database.SQL.SQLQuery;
import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServices {

    public Optional<Response> getAllUserData(UserID idGroup){
        Optional<Response> resultx= QueryManager.getFromSQL(SQLQuery.GET_ALL_USER_DATA, Arrays.asList(new String[]{idGroup.getUserID()
                }),Arrays.asList(new Class[]{String.class}),
                (result)->{
                    List<UserData> groupData=new ArrayList<>();
                    try {

                        if(result.next()){


                            Integer idUser= result.getInt(1);
                            String login= result.getString(2);
                            String password= result.getString(3);
                            String email= result.getString(4);
                            String name= result.getString(5);
                            String surname= result.getString(6);
                            String color= result.getString(7);
                            Integer authorization=result.getInt(8);
                            Integer isActive= result.getInt(9);

                            groupData.add(new UserData(idUser,login,password,email,name,surname,color,authorization,isActive));

                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                    String json= null;
                    try {
                        json = SaveDataAsJson.saveDataAsJson(groupData);
                        return Optional.of(new Response(json,"getAlluserData"));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }



                });

        return  resultx;
    }
}
