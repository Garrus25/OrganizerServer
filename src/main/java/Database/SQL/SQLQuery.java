package Database.SQL;

public class SQLQuery {
    public static final String IS_USER_LOGIN_EXISTS="SELECT COUNT(*) FROM USER WHERE USER.LOGIN = ?";
    public static final String REGISTER_TEMPORARY_USER="INSERT INTO USER VALUES (NULL,?,?,?,?,?,?,?,?)";

    public static final String GET_USER_ID_BASED_ON_USER_LOGIN="SELECT USER.ID_USER FROM USER where USER.LOGIN = '?' ";
}
