package Data;

public class Response {
    private String data;
    private String header;

    public Response(String data, String header) {
        this.data = data;
        this.header = header;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
