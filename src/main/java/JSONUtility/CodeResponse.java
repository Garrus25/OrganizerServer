package JSONUtility;

import Data_mapperJsonClass.Response;

public enum CodeResponse {

    OK(200,"Ok");
    int code;
    String description;

    private CodeResponse(int code,String description) {
        this.code=code;
        this.description=description;
    }

     public Response getResponseForCode(){
        return new Response(""+code,description);
     }

    @Override
    public String toString() {
        return "CodeResponse{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
