package JSONUtility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadObjectFromJson {

    public static <T>  T read(String data,Class dataOut) throws JsonProcessingException {

            ObjectMapper mapper = new ObjectMapper();
            T me = (T) mapper.readValue(data, dataOut);
            return me;

    }
}
