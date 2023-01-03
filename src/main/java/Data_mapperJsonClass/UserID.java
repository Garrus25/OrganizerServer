package Data_mapperJsonClass;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserID userID1 = (UserID) o;
        return Objects.equals(userID, userID1.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }
}
