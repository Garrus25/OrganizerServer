package Data_mapperJsonClass;

public class GroupCreationData {
    private String nameGroup;
    private String codeGroup;


    public GroupCreationData(){}

    public GroupCreationData(String nameGroup, String codeGroup) {
        this.nameGroup = nameGroup;
        this.codeGroup = codeGroup;
    }


    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getCodeGroup() {
        return codeGroup;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }
}
