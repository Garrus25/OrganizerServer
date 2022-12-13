package Database.SQL;

public class SQLQuery {
    public static final String IS_USER_LOGIN_EXISTS="SELECT COUNT(*) FROM USER WHERE USER.LOGIN = ?";
    public static final String REGISTER_TEMPORARY_USER="INSERT INTO USER VALUES (NULL,?,?,?,?,?,?,?,?)";
}
