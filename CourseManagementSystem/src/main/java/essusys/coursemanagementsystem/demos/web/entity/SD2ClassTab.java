package essusys.coursemanagementsystem.demos.web.entity;

public class SD2ClassTab {
    private String claTimeset; // 上课日期
    private String claWeekset; // 上课周数
    private String claClassroom; // 课室
    private String couName;
//    private String couIfexam;
//    private Integer couTerm;
    private Integer couScore;
    private String couRank;
    private Integer scoClaId;
    private Integer scoStuId;
    private String tchName;
    private Integer claTchId;

    public SD2ClassTab() {
    }

    public SD2ClassTab(String claTimeset, String claWeekset, String claClassroom, String couName, Integer couScore, String couRank, Integer scoClaId, Integer scoStuId, String tchName, Integer claTchId) {
        this.claTimeset = claTimeset;
        this.claWeekset = claWeekset;
        this.claClassroom = claClassroom;
        this.couName = couName;
        this.couScore = couScore;
        this.couRank = couRank;
        this.scoClaId = scoClaId;
        this.scoStuId = scoStuId;
        this.tchName = tchName;
        this.claTchId = claTchId;
    }

    public Integer getScoClaId() {
        return scoClaId;
    }

    public void setScoClaId(Integer scoClaId) {
        this.scoClaId = scoClaId;
    }

    public Integer getClaTchId() {
        return claTchId;
    }

    public void setClaTchId(Integer claTchId) {
        this.claTchId = claTchId;
    }

    public String getClaTimeset() {
        return claTimeset;
    }

    public void setClaTimeset(String claTimeset) {
        this.claTimeset = claTimeset;
    }

    public String getClaWeekset() {
        return claWeekset;
    }

    public void setClaWeekset(String claWeekset) {
        this.claWeekset = claWeekset;
    }

    public String getClaClassroom() {
        return claClassroom;
    }

    public void setClaClassroom(String claClassroom) {
        this.claClassroom = claClassroom;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public Integer getCouScore() {
        return couScore;
    }

    public void setCouScore(Integer couScore) {
        this.couScore = couScore;
    }

    public String getCouRank() {
        return couRank;
    }

    public void setCouRank(String couRank) {
        this.couRank = couRank;
    }

    public String getTchName() {
        return tchName;
    }

    public void setTchName(String tchName) {
        this.tchName = tchName;
    }

    public Integer getClaId() {
        return scoClaId;
    }

    public void setClaId(Integer scoClaId) {
        this.scoClaId = scoClaId;
    }

    public Integer getScoStuId() {
        return scoStuId;
    }

    public void setScoStuId(Integer scoStuId) {
        this.scoStuId = scoStuId;
    }
}
