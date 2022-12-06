public class Request {
    private String header;
    private String data;

    public Request(String header, String data) {
        this.header = header;
        this.data = data;
    }


    public Request(){}
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
