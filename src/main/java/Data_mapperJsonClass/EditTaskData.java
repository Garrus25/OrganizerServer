package Data_mapperJsonClass;

public class EditTaskData {

    private String name;
    private String description;
    private String createDate;
    private String dateOfNotification;

    public EditTaskData(){

    }

    public EditTaskData( String name, String descsription, String createDate, String dateOfNotifivation) {

        this.name = name;
        this.description = descsription;
        this.createDate = createDate;
        this.dateOfNotification = dateOfNotifivation;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDateOfNotification() {
        return dateOfNotification;
    }

    public void setDateOfNotification(String dateOfNotification) {
        this.dateOfNotification = dateOfNotification;
    }
}
