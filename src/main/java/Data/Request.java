package Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class
Request {
    private String header;



    @JsonProperty(value = "data")
    public void setJsonRaw(JsonNode jsonNode) throws IOException {
        // this leads to non-standard json, see discussion:
        // setJson(jsonNode.toString());

       data=jsonNode.asText();
    }


    @JsonRawValue
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
