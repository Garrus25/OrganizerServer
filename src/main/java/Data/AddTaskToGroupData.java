package Data;

public class AddTaskToGroupData {
    private Integer idtask;
    private Integer idGroup;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    private Integer idUser;

    public AddTaskToGroupData(){}
    public AddTaskToGroupData(Integer idtask, Integer idGroup,Integer idUser) {
        this.idtask = idtask;
        this.idGroup = idGroup;
        this.idUser=idUser;
    }

    public Integer getIdtask() {
        return idtask;
    }

    public void setIdtask(Integer idtask) {
        this.idtask = idtask;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }
}
