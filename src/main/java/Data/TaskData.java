package Data;

import java.sql.Timestamp;
import java.util.Objects;

public class TaskData {
    private Integer idTask;
    private String name;
    private String description;
    private Timestamp createDate;
    private Timestamp dateOfNotification;


    public TaskData(){

    }

    public TaskData(Integer idTask, String name, String descsription, Timestamp createDate, Timestamp dateOfNotifivation) {
        this.idTask = idTask;
        this.name = name;
        this.description = descsription;
        this.createDate = createDate;
        this.dateOfNotification = dateOfNotifivation;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getDateOfNotification() {
        return dateOfNotification;
    }

    public void setDateOfNotification(Timestamp dateOfNotification) {
        this.dateOfNotification = dateOfNotification;
    }

    @Override
    public String toString() {
        return "TaskData{" +
                "idTask=" + idTask +
                ", name='" + name + '\'' +
                ", descsription='" + description + '\'' +
                ", createDate=" + createDate +
                ", dateOfNotifivation=" + dateOfNotification +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskData taskData = (TaskData) o;
        return Objects.equals(idTask, taskData.idTask) && Objects.equals(name, taskData.name) && Objects.equals(description, taskData.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask, name, description);
    }
}
