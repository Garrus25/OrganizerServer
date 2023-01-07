package Data_mapperJsonClass;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Event {
    private Integer taskId;
    private String eventName;
    private String group;
    private LocalDateTime date;
    private String description;
    private String login;
    private String type;
    private Integer idGroup;

    public Event(String eventName, String group, LocalDateTime date, String description, String type, Integer taskId) {
        this.eventName = eventName;
        this.group = group;
        this.date = date;
        this.description = description;
        this.type = type;
        this.login = "";
        this.taskId = taskId;
        this.idGroup = idGroup;
    }

    public Event(String eventName, String group, LocalDateTime date, String description, String type, String login,  Integer taskId,Integer idGroup) {
        this.eventName = eventName;
        this.group = group;
        this.date = date;
        this.description = description;
        this.type = type;
        this.login = login;
        this.taskId = taskId;
        this.idGroup=idGroup;
    }

    public Event() {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String personName) {
        this.login = personName;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return eventName;
    }

    public void replaceData(Event event) {
        this.eventName = event.eventName;
        this.group = event.group;
        this.date = event.date;
        this.type = event.type;
        this.description = event.description;
        this.login = event.login;
        this.taskId = event.taskId;
    }
}

