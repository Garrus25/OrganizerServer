package Data;

public class AddTaskToUser {
    private Integer idUser;
    private Integer idTask;
    private Integer isDisplay;

    public AddTaskToUser(){};

    public AddTaskToUser(Integer idUSer, Integer idTask, Integer isDisplay) {
        this.idUser = idUSer;
        this.idTask = idTask;
        this.isDisplay = isDisplay;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }
}
