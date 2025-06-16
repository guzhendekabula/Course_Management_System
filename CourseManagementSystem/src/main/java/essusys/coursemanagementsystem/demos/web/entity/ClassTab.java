package essusys.coursemanagementsystem.demos.web.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassTab {

  @JsonProperty("cla_id")
  private Integer claId;  // 教学班id，对应数据库的 cla_id 字段

  @JsonProperty("cla_cou_code")
  private String claCouCode;  // 课程识别码，对应数据库的 cla_cou_code 字段

  @JsonProperty("cla_tch_id")
  private Integer claTchId;  // 任课教师，对应数据库的 cla_tch_id 字段

  @JsonProperty("cla_toschool_1")
  private String claToschool1;  // 可选课学院1，对应数据库的 cla_toschool_1 字段

  @JsonProperty("cla_toschool_2")
  private String claToschool2;  // 可选课学院2，对应数据库的 cla_toschool_2 字段

  @JsonProperty("cla_toschool_3")
  private String claToschool3;  // 可选课学院3，对应数据库的 cla_toschool_3 字段

  @JsonProperty("cla_toschool_4")
  private String claToschool4;  // 可选课学院4，对应数据库的 cla_toschool_4 字段

  @JsonProperty("cla_timeset")
  private String claTimeset;  // 上课日期，对应数据库的 cla_timeset 字段

  @JsonProperty("cla_weekset")
  private String claWeekset;  // 上课周数，对应数据库的 cla_weekset 字段

  @JsonProperty("cla_classroom")
  private String claClassroom;  // 课室，对应数据库的 cla_classroom 字段

  // 默认构造函数
  public ClassTab() {}

  // 全参构造函数
  public ClassTab(Integer claId, String claCouCode, Integer claTchId, String claToschool1,
                  String claToschool2, String claToschool3, String claToschool4,
                  String claTimeset, String claWeekset, String claClassroom) {
    this.claId = claId;
    this.claCouCode = claCouCode;
    this.claTchId = claTchId;
    this.claToschool1 = claToschool1;
    this.claToschool2 = claToschool2;
    this.claToschool3 = claToschool3;
    this.claToschool4 = claToschool4;
    this.claTimeset = claTimeset;
    this.claWeekset = claWeekset;
    this.claClassroom = claClassroom;
  }

  // Getters and Setters
  public Integer getClaId() {
    return claId;
  }

  public void setClaId(Integer claId) {
    this.claId = claId;
  }

  public String getClaCouCode() {
    return claCouCode;
  }

  public void setClaCouCode(String claCouCode) {
    this.claCouCode = claCouCode;
  }

  public Integer getClaTchId() {
    return claTchId;
  }

  public void setClaTchId(Integer claTchId) {
    this.claTchId = claTchId;
  }

  public String getClaToschool1() {
    return claToschool1;
  }

  public void setClaToschool1(String claToschool1) {
    this.claToschool1 = claToschool1;
  }

  public String getClaToschool2() {
    return claToschool2;
  }

  public void setClaToschool2(String claToschool2) {
    this.claToschool2 = claToschool2;
  }

  public String getClaToschool3() {
    return claToschool3;
  }

  public void setClaToschool3(String claToschool3) {
    this.claToschool3 = claToschool3;
  }

  public String getClaToschool4() {
    return claToschool4;
  }

  public void setClaToschool4(String claToschool4) {
    this.claToschool4 = claToschool4;
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

  @Override
  public String toString() {
    return "ClassTab{" +
            "claId=" + claId +
            ", claCouCode='" + claCouCode + '\'' +
            ", claTchId=" + claTchId +
            ", claToschool1='" + claToschool1 + '\'' +
            ", claToschool2='" + claToschool2 + '\'' +
            ", claToschool3='" + claToschool3 + '\'' +
            ", claToschool4='" + claToschool4 + '\'' +
            ", claTimeset='" + claTimeset + '\'' +
            ", claWeekset='" + claWeekset + '\'' +
            ", claClassroom='" + claClassroom + '\'' +
            '}';
  }
}
