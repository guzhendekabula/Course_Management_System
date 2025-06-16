package essusys.coursemanagementsystem.demos.web.entity;

public class ClaCouTab {

    private  Integer claID;
    //    private String claCouCode;
//    private  Integer claTchId;
//    private  String couCode;
    private  String couName;
    private Integer scoStuId;



    public ClaCouTab() {
    }

    public ClaCouTab(Integer claID, String couName, Integer scoStuId) {
        this.claID = claID;
        this.couName = couName;
        this.scoStuId = scoStuId;
    }

    public Integer getScoStuId() {
        return scoStuId;
    }

    public void setScoStuId(Integer scoStuId) {
        this.scoStuId = scoStuId;
    }

    public Integer getClaID() {
        return claID;
    }

    public void setClaID(Integer claID) {
        this.claID = claID;
    }


    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }
}
