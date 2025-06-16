package essusys.coursemanagementsystem.demos.web.entity;

public class SDClassTab {
    private String claTimeset; // 上课日期
    private String claWeekset; // 上课周数
    private String claClassroom; // 课室
    private String couName;
    private String couIfexam;
    private Integer couTerm;
    private Integer couScore;
    private String couRank;
    private Integer claId;
    private Integer scoStuId;
    private String tchName;


    public SDClassTab() {
    }

    public SDClassTab(String claTimeset, String claWeekset, String claClassroom, String couName, String couIfexam, Integer couTerm, Integer couScore, String couRank, String tchName, Integer claId, Integer scoStuId) {
        this.claTimeset = claTimeset;
        this.claWeekset = claWeekset;
        this.claClassroom = claClassroom;
        this.couName = couName;
        this.couIfexam = couIfexam;
        this.couTerm = couTerm;
        this.couScore = couScore;
        this.couRank = couRank;
        this.tchName = tchName;
        this.claId = claId;
        this.scoStuId = scoStuId;
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

    public String getCouIfexam() {
        return couIfexam;
    }

    public void setCouIfexam(String couIfexam) {
        this.couIfexam = couIfexam;
    }

    public Integer getCouTerm() {
        return couTerm;
    }

    public void setCouTerm(Integer couTerm) {
        this.couTerm = couTerm;
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

    public Integer getScoClaId() {
        return claId;
    }

    public void setScoClaId(Integer claId) {
        this.claId = claId;
    }

    public Integer getScoStudId() {
        return scoStuId;
    }

    public void setScoStudId(Integer scoStuId) {
        this.scoStuId = scoStuId;
    }
}
