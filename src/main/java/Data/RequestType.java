package Data;

public enum RequestType {

    REGISTER_USER_TEMPORARY("registerUserTemporary"),
    IS_CODE_CONFIRM_ACCOUNT_VALID("isCodeVerficationValid"),
    REGISTER_USER("registerUser"),

    GET_USER_DATA("getUserData"),

    IF_USER_LOGIN_AVAILABLE("ifUserLoginAvailable"),
    GET_USER_ID_FROM_LOGIN("getIdUserFromLogin"),

    USER_LOGIN_DATA_VALID("isLoginDataValid"),

    GET_ALL_TASK_FOR_GROUP("getAllTaskForGroup"),

    GET_ALL_TASK_FOR_USER("getAllTaskForUSer"),

    ADD_NEW_TASK("addNewTask"),

    ADD_TASK_TO_USER("addTaskToUSer"),

    ADD_TASK_TO_GROUP("addTaskToGroup"),

    UPDATE_TASK("updateTask"),

    CREATE_GROUP("createGroup"),

    GET_MEMBERSHIP_GROUP_ABOUT_UD("getMembershipGroupAboutId"),

    GET_ALL_GROUP_DATA("getAllDataGroup"),

    ADD_USER_TO_GROUP("adduserToGroup"),

    REMOVE_USER_FROM_GROUP("removeUserFromGroup");





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
