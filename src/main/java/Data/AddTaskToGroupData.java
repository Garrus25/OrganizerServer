package Data;

public class AddTaskToGroupData {
    private Integer idTask;
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
        this.idTask = idtask;
        this.idGroup = idGroup;
        this.idUser=idUser;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }
}
