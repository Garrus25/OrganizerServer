package Data;

public class EditTaskData {

    private String name;
    private String descsription;
    private String createDate;
    private String dateOfNotifivation;


    public EditTaskData(){

    }

    public EditTaskData( String name, String descsription, String createDate, String dateOfNotifivation) {

        this.name = name;
        this.descsription = descsription;
        this.createDate = createDate;
        this.dateOfNotifivation = dateOfNotifivation;
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
