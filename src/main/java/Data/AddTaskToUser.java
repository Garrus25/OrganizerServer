package Data;

public class AddTaskToUser {
    private Integer idUSer;
    private Integer idTask;
    private Integer isDisplay;

    public AddTaskToUser(){};

    public AddTaskToUser(Integer idUSer, Integer idTask, Integer isDisplay) {
        this.idUSer = idUSer;
        this.idTask = idTask;
        this.isDisplay = isDisplay;
    }

    public Integer getIdUSer() {
        return idUSer;
    }

    public void setIdUSer(Integer idUSer) {
        this.idUSer = idUSer;
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
