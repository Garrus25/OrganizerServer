package Data;

public class ValidLoginData {
    private String isValidLoginAndPassword;
    public ValidLoginData(){

    }

    public ValidLoginData(String isValidLoginAndPassword) {
        this.isValidLoginAndPassword = isValidLoginAndPassword;
    }

    public String getIsValidLoginAndPassword() {
        return isValidLoginAndPassword;
    }

    public void setIsValidLoginAndPassword(String isValidLoginAndPassword) {
        this.isValidLoginAndPassword = isValidLoginAndPassword;
    }
}
