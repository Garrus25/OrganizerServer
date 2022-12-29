package Data;

import java.util.Objects;

public class ResponseLogin {
    private String name;
    private String response;


    public ResponseLogin(){

    }
    public ResponseLogin(String name, String response) {
        this.name = name;
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseLogin that = (ResponseLogin) o;
        return Objects.equals(name, that.name) && Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, response);
    }

    @Override
    public String toString() {
        return "ResponseLogin{" +
                "name='" + name + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
