package Database.SQL;

public class SQLQuery {
    public static final String IS_USER_LOGIN_EXISTS="SELECT COUNT(*) FROM USER WHERE USER.LOGIN = ?";
    public static final String REGISTER_TEMPORARY_USER="INSERT INTO USER VALUES (NULL,?,?,?,?,?,?,?,?)";
    public static final String GET_USER_ID_BASED_ON_USER_LOGIN="SELECT USER.ID_USER FROM USER where USER.LOGIN = '?' ";

    public static final String IS_REGISTRATION_CODE_VALID="SELECT COUNT(*) FROM USER WHERE USER.AUTHENTICATION_TOKEN=? AND USER.ID_USER=?";

    public static final String REGISTER_USER="UPDATE USER SET USER.IS_ACTIVE=1 WHERE USER.ID_USER=?";

    public static final String IS_USER_LOGIN_DATA_VALID="SELECT COUNT(*) FROM USER WHERE USER.LOGIN=? and USER.PASSWORD=?";

    public static final String GET_ALL_GROUP_DATA="SELECT GROUPS.ID_GROUP,GROUPS.NAME,GROUPS.GROUP_CODE FROM GROUPS";

    public static final String GET_ALL_TASK_FOR_GROUP="SELECT TASK.ID_TASK,TASK.NAME,TASK.DESCRIPTION,TASK.CREATE_DATE,TASK.DATE_OF_NOTIFICATION FROM TASK JOIN taskusergroup on taskusergroup.idTask=task.ID_TASK where taskusergroup.idGroup=?";
    public static final String GET_ALL_MEMBERSHIP_GROUP_ABOUT_ID="SELECT user.ID_USER,user.LOGIN,user.PASSWORD,user.EMAIL,user.NAME,user.SURNAME,user.COLOR,user.AUTHENTICATION_TOKEN,user.IS_ACTIVE FROM GROUPS join groups_users on groups_users.ID_GROUP=groups.ID_GROUP join user on user.ID_USER=groups_users.ID_USER where groups.ID_GROUP=?";



    public static final String ADD_TASK_TO_GROUP="INSERT INTO TASKUSERGROUP VALUES(null,?,?,?)";
    public static final String CREATE_GROUP="INSERT INTO GROUPS VALUES(null,?,?)";
    public static final String REMOVE_USER_FROM_GROUP="DELETE GROUPS WHERE GROUPS.ID_USER=? AND GROUPS.ID_GROUP=?";


    public static final String ADD_TASK="INSERT INTO TASK VALUES(null,?,?,?,?)";
    public static final String ADD_TASK_TO_USER="INSERT INTO TASKS_USERS VALUES(?,?,?)";
    public static final String EDIT_TASK="UPDATE TASK SET Task.NAME=?,TASK.DESCRIPTION=?,TASK.CREATE_DATE,TASK.DATE_OF_NOTIFICATION=? where TASK.ID_TASK=?";
    public static final String ADD_USER_TO_GROUP="INSERT INTO GROUPS_USERS VALUES(?,?)";
    public static final String GET_ALL_USER_DATA="SELECT * FROM USER WHERE USER.ID_USER=?";
    public static final String GET_ALL_TASK_FOR_USER="SELECT TASK.ID_TASK,TASK.NAME,TASK.DESCRIPTION,TASK.CREATE_DATE,TASK.DATE_OF_NOTIFICATION from TASK join tasks_users on tasks_users.ID_TASK=task.ID_TASK where tasks_users.ID_USER=?";
}
