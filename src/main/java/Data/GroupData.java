package Data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return idGroup == groupData.idGroup && Objects.equals(nameGroup, groupData.nameGroup) && Objects.equals(groupCode, groupData.groupCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGroup, nameGroup, groupCode);
    }
}
