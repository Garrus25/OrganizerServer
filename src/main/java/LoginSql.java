public enum  LoginSql {
    IS_USER_EXIST("","Is user exists");

    LoginSql(String sqlText, String sqlDescription){
        this.sqlText=sqlText;
        this.sqlDescription=sqlDescription;

    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public String getSqlDescription() {
        return sqlDescription;
    }

    public void setSqlDescription(String sqlDescription) {
        this.sqlDescription = sqlDescription;
    }

    private String sqlText;
    private String sqlDescription;
}
