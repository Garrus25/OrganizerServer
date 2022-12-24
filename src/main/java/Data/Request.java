package Data;

import JSONUtility.SaveDataAsJson;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.IOException;
import java.io.StringWriter;

public class
Request {
    private String header;



    private String data;

    public Request(String header, String data) {
        this.header = header;
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("xD");
        String encrypted= encryptor.encrypt(data);
       // encrypted=  "\""+encrypted+"\"";
        this.data = encrypted;
    }


    public Request(){}
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getData() {

        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword("xD");
      //  this.data=
        return  decryptor.decrypt(data);
    }

    public void setData(String data) {

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("xD");
        String encrypted= encryptor.encrypt(data);
       // encrypted=  "\""+encrypted+"\"";
        this.data = encrypted;
    }


    @Override
    public String toString() {
        return "Request{" +
                "header='" + header + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
