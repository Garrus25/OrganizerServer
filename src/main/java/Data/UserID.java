package Data;

public class UserID {

    public UserID(){

    }
    public UserID(String userID) {
        this.userID = userID;
    }

    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
