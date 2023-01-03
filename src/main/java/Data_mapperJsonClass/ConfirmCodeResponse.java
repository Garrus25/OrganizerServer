package Data_mapperJsonClass;

public class ConfirmCodeResponse {
    private Boolean isCodeValid;

    public ConfirmCodeResponse(Boolean isCodeValid) {
        this.isCodeValid = isCodeValid;
    }

    public ConfirmCodeResponse(){

    }

    public Boolean getCodeValid() {
        return isCodeValid;
    }

    public void setCodeValid(Boolean codeValid) {
        isCodeValid = codeValid;
    }
}
