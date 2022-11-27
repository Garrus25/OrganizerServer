package responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorResponse{
    private String errorCode;

    public ErrorResponse(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String generateJSONError() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json=mapper.writeValueAsString(errorCode);
        return json;
    }



}
