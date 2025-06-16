package essusys.coursemanagementsystem.demos.web.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherTab {

  @JsonProperty("tch_id")
  private Integer tchId; // 教师ID，对应数据库的 tch_id 字段

  @JsonProperty("tch_name")
  private String tchName; // 教师姓名，对应数据库的 tch_name 字段

  @JsonProperty("tch_sex")
  private String tchSex; // 教师性别，对应数据库的 tch_sex 字段

  @JsonProperty("tch_sch")
  private String tchSch; // 教师所属学院，对应数据库的 tch_sch 字段

  @JsonProperty("tch_tel")
  private String tchTel; // 教师电话，对应数据库的 tch_tel 字段

  @JsonProperty("tch_picture")
  private String tchPicture; // 教师照片存储路径，对应数据库的 tch_picture 字段

  @JsonProperty("tch_account")
  private String tchAccount; // 教师账号，对应数据库的 tch_account 字段

  @JsonProperty("tch_password")
  private String tchPassword; // 教师密码，对应数据库的 tch_password 字段

  @JsonProperty("tch_status")
  private Integer tchStatus; // 教师状态，对应数据库的 tch_status 字段

  // 默认构造函数
  public TeacherTab() {}

  // 全参构造函数
  public TeacherTab(Integer tchId, String tchName, String tchSex, String tchSch, String tchTel, String tchPicture, String tchAccount, String tchPassword, Integer tchStatus) {
    this.tchId = tchId;
    this.tchName = tchName;
    this.tchSex = tchSex;
    this.tchSch = tchSch;
    this.tchTel = tchTel;
    this.tchPicture = tchPicture;
    this.tchAccount = tchAccount;
    this.tchPassword = tchPassword;
    this.tchStatus = tchStatus;
  }

  // Getters and Setters
  public Integer getTchId() {
    return tchId;
  }

  public void setTchId(Integer tchId) {
    this.tchId = tchId;
  }

  public String getTchName() {
    return tchName;
  }

  public void setTchName(String tchName) {
    this.tchName = tchName;
  }

  public String getTchSex() {
    return tchSex;
  }

  public void setTchSex(String tchSex) {
    this.tchSex = tchSex;
  }

  public String getTchSch() {
    return tchSch;
  }

  public void setTchSch(String tchSch) {
    this.tchSch = tchSch;
  }

  public String getTchTel() {
    return tchTel;
  }

  public void setTchTel(String tchTel) {
    this.tchTel = tchTel;
  }

  public String getTchPicture() {
    return tchPicture;
  }

  public void setTchPicture(String tchPicture) {
    this.tchPicture = tchPicture;
  }

  public String getTchAccount() {
    return tchAccount;
  }

  public void setTchAccount(String tchAccount) {
    this.tchAccount = tchAccount;
  }

  public String getTchPassword() {
    return tchPassword;
  }

  public void setTchPassword(String tchPassword) {
    this.tchPassword = tchPassword;
  }

  public Integer getTchStatus() {
    return tchStatus;
  }

  public void setTchStatus(Integer tchStatus) {
    this.tchStatus = tchStatus;
  }

  @Override
  public String toString() {
    return "TeacherTab{" +
            "tchId=" + tchId +
            ", tchName='" + tchName + '\'' +
            ", tchSex='" + tchSex + '\'' +
            ", tchSch='" + tchSch + '\'' +
            ", tchTel='" + tchTel + '\'' +
            ", tchPicture='" + tchPicture + '\'' +
            ", tchAccount='" + tchAccount + '\'' +
            ", tchPassword='" + tchPassword + '\'' +
            ", tchStatus=" + tchStatus +
            '}';
  }
}