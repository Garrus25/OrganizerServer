package Data;

public enum RequestType {

    REGISTER_USER_TEMPORARY("registerUserTemporary"),
    IS_CODE_CONFIRM_ACCOUNT_VALID("isCodeVerficationValid"),
    REGISTER_USER("registerUser");

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
