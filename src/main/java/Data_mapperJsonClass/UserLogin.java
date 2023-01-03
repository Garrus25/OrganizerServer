package Data_mapperJsonClass;

public class UserLogin {

    public UserLogin(){

    }
    public UserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    private String userLogin;

}
