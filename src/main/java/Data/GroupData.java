package Data;

public class GroupData {
    private int idGroup;
    private String nameGroup;
    private String groupCode;
    public GroupData(){

    }

    public GroupData(int idGroup, String nameGroup, String groupCode) {
        this.idGroup = idGroup;
        this.nameGroup = nameGroup;
        this.groupCode = groupCode;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
