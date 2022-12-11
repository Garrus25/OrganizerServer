package Data;

public class ResponseLogin {
    private String name;
    private String response;

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
}
