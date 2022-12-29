package Data;

import java.util.Objects;

public class UserData {
    private int idUser;
    private String login;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String color;
    private Integer authorizeToken;
    private Integer isActive;

    public UserData(){

    }

    public UserData(int idUser, String login, String password, String email, String name, String surname, String color, Integer authorizeToken, Integer isActive) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.color = color;
        this.authorizeToken = authorizeToken;
        this.isActive = isActive;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAuthorizeToken() {
        return authorizeToken;
    }

    public void setAuthorizeToken(Integer authorizeToken) {
        this.authorizeToken = authorizeToken;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return idUser == userData.idUser && Objects.equals(login, userData.login) && Objects.equals(password, userData.password) && Objects.equals(email, userData.email) && Objects.equals(name, userData.name) && Objects.equals(surname, userData.surname) && Objects.equals(color, userData.color) && Objects.equals(authorizeToken, userData.authorizeToken) && Objects.equals(isActive, userData.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, login, password, email, name, surname, color, authorizeToken, isActive);
    }
}
