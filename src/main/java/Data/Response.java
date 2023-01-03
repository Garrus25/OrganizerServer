package Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.util.Objects;

public class Response {
    private String data;
    private String header;

    private static final String ENCRYPTION_PASSWORD="xD";


    public Response(){

    }
    public Response(String data, String header) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(ENCRYPTION_PASSWORD);
        this.data= decryptor.encrypt(data);
        this.header = header;
    }

    public String getData() {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(ENCRYPTION_PASSWORD);
       return decryptor.decrypt(data);
    }

    public void setData(String data) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(ENCRYPTION_PASSWORD);
        this.data= decryptor.encrypt(data);

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return  Objects.equals(header, response.header);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, header);
    }
}
