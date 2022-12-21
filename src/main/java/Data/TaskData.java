package Data;

public class TaskData {
    private Integer idTask;
    private String name;
    private String descsription;
    private String createDate;
    private String dateOfNotifivation;


    public TaskData(){

    }

    public TaskData(Integer idTask, String name, String descsription, String createDate, String dateOfNotifivation) {
        this.idTask = idTask;
        this.name = name;
        this.descsription = descsription;
        this.createDate = createDate;
        this.dateOfNotifivation = dateOfNotifivation;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescsription() {
        return descsription;
    }

    public void setDescsription(String descsription) {
        this.descsription = descsription;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDateOfNotifivation() {
        return dateOfNotifivation;
    }

    public void setDateOfNotifivation(String dateOfNotifivation) {
        this.dateOfNotifivation = dateOfNotifivation;
    }
}
