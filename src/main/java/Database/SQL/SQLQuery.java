package Database.SQL;

public class SQLQuery {
    public static final String IS_USER_LOGIN_EXISTS="SELECT COUNT(*) FROM USER WHERE USER.LOGIN = ?";
    public static final String REGISTER_TEMPORARY_USER="INSERT INTO USER VALUES (NULL,?,?,?,?,?,?,?,?)";
    public static final String GET_USER_ID_BASED_ON_USER_LOGIN="SELECT USER.ID_USER FROM USER where USER.LOGIN = '?' ";

    public static final String IS_REGISTRATION_CODE_VALID="SELECT COUNT(*) FROM USER WHERE USER.AUTHENTICATION_TOKEN=? AND USER.ID_USER=?";

    public static final String REGISTER_USER="UPDATE USER SET USER.IS_ACTIVE=1 WHERE USER.ID_USER=?";

    public static final String IS_USER_LOGIN_DATA_VALID="SELECT COUNT(*) FROM USER WHERE USER.LOGIN=? and USER.PASSWORD=?";
}
