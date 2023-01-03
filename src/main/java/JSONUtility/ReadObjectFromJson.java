package JSONUtility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.ArrayList;
import java.util.List;

public class ReadObjectFromJson {

    public static <T>  T read(String data,Class dataOut) throws JsonProcessingException {

            ObjectMapper mapper = new ObjectMapper();
            T me = (T) mapper.readValue(data, dataOut);
            return me;

    }

    public static <T> List<T> readListObject(String data,Class<T> Tclass) throws JsonProcessingException{

        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Tclass);
        List<T> ts = mapper.readValue(data, listType);
        return ts;

    }
}
