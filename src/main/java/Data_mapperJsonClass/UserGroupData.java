package Data_mapperJsonClass;

public class UserGroupData {
    private int idUser;
    private int idGroup;

    public UserGroupData(){

    }

    public UserGroupData(int idUser, int idGroup) {
        this.idUser = idUser;
        this.idGroup = idGroup;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
}
