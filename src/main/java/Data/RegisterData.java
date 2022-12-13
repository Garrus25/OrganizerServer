package Data;

public class RegisterData {
    private int idUser;
    private String login;
    private String password;

    public RegisterData(){

        this.email = email;
    }
    public RegisterData(int idUser, String login, String password,String email, String name, String surname, String color, int authorizeToken, boolean isActive) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.color = color;
        this.authorizeToken = authorizeToken;
        this.isActive = isActive;
        this.email=email;
    }

    private String name;
    private String surname;
    private String color;
    private int authorizeToken;
    private boolean isActive;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

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

    public int getAuthorizeToken() {
        return authorizeToken;
    }

    public void setAuthorizeToken(int authorizeToken) {
        this.authorizeToken = authorizeToken;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
