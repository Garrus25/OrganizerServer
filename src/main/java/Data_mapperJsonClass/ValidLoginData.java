package Data_mapperJsonClass;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidLoginData that = (ValidLoginData) o;
        return Objects.equals(isValidLoginAndPassword, that.isValidLoginAndPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isValidLoginAndPassword);
    }
}
