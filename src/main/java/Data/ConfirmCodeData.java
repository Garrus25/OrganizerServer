package Data;

public class ConfirmCodeData {
    private String confirmCode;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    private Integer idUser;



    public ConfirmCodeData(String confirmCode,Integer idUser) {
        this.confirmCode = confirmCode;
        this.idUser = idUser;
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }
}
