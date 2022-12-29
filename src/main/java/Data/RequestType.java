package Data;

public enum RequestType {

    REGISTER_USER_TEMPORARY("registerUserTemporary"),
    IS_CODE_CONFIRM_ACCOUNT_VALID("isCodeVerficationValid"),
    REGISTER_USER("registerUser"),

    GET_USER_DATA("getUserData"),

    REQUEST_IF_USER_LOGIN_AVAILABLE("ifUserLoginAvailable"),
    REQUEST_GET_USER_ID_FROM_LOGIN("getIdUserFromLogin"),

    REQUEST_USER_LOGIN_DATA_VALID("isLoginDataValid");

    public String getNameRequest() {
        return nameRequest;
    }

    public void setNameRequest(String nameRequest) {
        this.nameRequest = nameRequest;
    }

    private String nameRequest;
    RequestType(String nameRequest){
        this.nameRequest=nameRequest;
    }
}
