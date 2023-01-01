package Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class TaskData {
    private Integer idTask;
    private String name;
    private String descsription;
    private Timestamp createDate;
    private Timestamp dateOfNotifivation;


    public TaskData(){

    }

    public TaskData(Integer idTask, String name, String descsription, Timestamp createDate, Timestamp dateOfNotifivation) {
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getDateOfNotifivation() {
        return dateOfNotifivation;
    }

    public void setDateOfNotifivation(Timestamp dateOfNotifivation) {
        this.dateOfNotifivation = dateOfNotifivation;
    }

    @Override
    public String toString() {
        return "TaskData{" +
                "idTask=" + idTask +
                ", name='" + name + '\'' +
                ", descsription='" + descsription + '\'' +
                ", createDate=" + createDate +
                ", dateOfNotifivation=" + dateOfNotifivation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskData taskData = (TaskData) o;
        return Objects.equals(idTask, taskData.idTask) && Objects.equals(name, taskData.name) && Objects.equals(descsription, taskData.descsription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTask, name, descsription);
    }
}
