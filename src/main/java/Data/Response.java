package Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Response {
    private String data;
    private String header;


    public Response(){

    }
    public Response(String data, String header) {
        this.data = data;
        this.header = header;
    }

    public String getData() {

       return data;
    }

    public void setData(String data) {

        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword("xD");
        this.data= decryptor.decrypt(data);
     //  this.data = encrypted;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }


    @Override
    public String toString() {
        return "Response{" +
                "data='" + data + '\'' +
                ", header='" + header + '\'' +
                '}';
    }
}
