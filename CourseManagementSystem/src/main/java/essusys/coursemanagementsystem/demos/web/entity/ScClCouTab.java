package essusys.coursemanagementsystem.demos.web.entity;


public  class ScClCouTab {

    //    private Integer claId; // 教学班id
//    private String claCouCode; // 课程识别码
    private Integer claTchId; // 任课教师
    //       private String claTimeset; // 上课日期
//    private String claWeekset; // 上课周数
//    private String claClassroom; //
//    private String couCode;
    private String couName;
    private Integer couTerm;
    private String couRank;
    private Integer couScore;
    //    private Integer scoStuId;
//    private Integer scoClaId;
    private double scoScore1;
    private double scoScore2;
    private String tchName;
    private Boolean isPass;



    public ScClCouTab() {

    }

    public ScClCouTab(Integer claTchId, String couName, Integer couTerm, String couRank, Integer couScore, double scoScore1, double scoScore2, String tchName, Boolean isPass) {
        this.claTchId = claTchId;
        this.couName = couName;
        this.couTerm = couTerm;
        this.couRank = couRank;
        this.couScore = couScore;
        this.scoScore1 = scoScore1;
        this.scoScore2 = scoScore2;
        this.tchName = tchName;
        this.isPass = isPass;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public Integer getClaTchId() {
        return claTchId;
    }

    public void setClaTchId(Integer claTchId) {
        this.claTchId = claTchId;
    }

    public Integer getCouTerm() {
        return couTerm;
    }

    public void setCouTerm(Integer couTerm) {
        this.couTerm = couTerm;
    }

    public String getCouRank() {
        return couRank;
    }

    public void setCouRank(String couRank) {
        this.couRank = couRank;
    }

    public Integer getCouScore() {
        return couScore;
    }

    public void setCouScore(Integer couScore) {
        this.couScore = couScore;
    }

    public double getScoScore1() {
        return scoScore1;
    }

    public void setScoScore1(double scoScore1) {
        this.scoScore1 = scoScore1;
    }

    public double getScoScore2() {
        return scoScore2;
    }

    public void setScoScore2(double scoScore2) {
        this.scoScore2 = scoScore2;
    }

    public String getTchName() {
        return tchName;
    }

    public void setTchName(String tchName) {
        this.tchName = tchName;
    }

    public Boolean getPass() {
        return getScoScore1()>60;
    }

    public void setPass(Boolean pass) {
        isPass = pass;
    }


}