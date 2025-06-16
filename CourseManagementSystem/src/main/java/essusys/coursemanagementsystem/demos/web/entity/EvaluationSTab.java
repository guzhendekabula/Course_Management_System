package essusys.coursemanagementsystem.demos.web.entity;

public class EvaluationSTab {
    private String couRank;
    private String couType;
    private String couName;
    private String couSchool;
    private String tchName;
    private Integer evaClaId;
    private Integer evaTchId;
    private Integer evaStuId;
    private Integer evaScore;
    private Integer evaItem1;
    private Integer evaItem2;
    private Integer evaItem3;
    private Integer evaItem4;
    private String evaText;

    public EvaluationSTab() {
    }

    public EvaluationSTab(String couRank, String couType, String couName, String couSchool, String tchName, Integer evaClaId, Integer evaTchId, Integer evaStuId, Integer evaScore, Integer evaItem1, Integer evaItem2, Integer evaItem3, Integer evaItem4, String evaText) {
        this.couRank = couRank;
        this.couType = couType;
        this.couName = couName;
        this.couSchool = couSchool;
        this.tchName = tchName;
        this.evaClaId = evaClaId;
        this.evaTchId = evaTchId;
        this.evaStuId = evaStuId;
        this.evaScore = evaScore;
        this.evaItem1 = evaItem1;
        this.evaItem2 = evaItem2;
        this.evaItem3 = evaItem3;
        this.evaItem4 = evaItem4;
        this.evaText = evaText;
    }

    public String getCouRank() {
        return couRank;
    }

    public void setCouRank(String couRank) {
        this.couRank = couRank;
    }

    public String getCouType() {
        return couType;
    }

    public void setCouType(String couType) {
        this.couType = couType;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getCouSchool() {
        return couSchool;
    }

    public void setCouSchool(String couSchool) {
        this.couSchool = couSchool;
    }

    public String getTchName() {
        return tchName;
    }

    public void setTchName(String tchName) {
        this.tchName = tchName;
    }

    public Integer getEvaClaId() {
        return evaClaId;
    }

    public void setEvaClaId(Integer evaClaId) {
        this.evaClaId = evaClaId;
    }

    public Integer getEvaTchId() {
        return evaTchId;
    }

    public void setEvaTchId(Integer evaTchId) {
        this.evaTchId = evaTchId;
    }

    public Integer getEvaStuId() {
        return evaStuId;
    }

    public void setEvaStuId(Integer evaStuId) {
        this.evaStuId = evaStuId;
    }

    public Integer getEvaScore() {
        return evaScore;
    }

    public void setEvaScore(Integer evaScore) {
        this.evaScore = evaScore;
    }

    public Integer getEvaItem1() {
        return evaItem1;
    }

    public void setEvaItem1(Integer evaItem1) {
        this.evaItem1 = evaItem1;
    }

    public Integer getEvaItem2() {
        return evaItem2;
    }

    public void setEvaItem2(Integer evaItem2) {
        this.evaItem2 = evaItem2;
    }

    public Integer getEvaItem3() {
        return evaItem3;
    }

    public void setEvaItem3(Integer evaItem3) {
        this.evaItem3 = evaItem3;
    }

    public Integer getEvaItem4() {
        return evaItem4;
    }

    public void setEvaItem4(Integer evaItem4) {
        this.evaItem4 = evaItem4;
    }

    public String getEvaText() {
        return evaText;
    }

    public void setEvaText(String evaText) {
        this.evaText = evaText;
    }
}
