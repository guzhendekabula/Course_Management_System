package essusys.coursemanagementsystem.demos.web.entity;

import com.fasterxml.jackson.annotation.JsonProperty;


public class StudentTab {

  @JsonProperty("stu_id")
  private Integer stuId; // 学生ID，对应数据库的 stu_id 字段

  @JsonProperty("stu_name")
  private String stuName; // 学生姓名，对应数据库的 stu_name 字段

  @JsonProperty("stu_sex")
  private String stuSex; // 学生性别，对应数据库的 stu_sex 字段

  @JsonProperty("stu_tel")
  private String stuTel; // 学生电话，对应数据库的 stu_tel 字段

  @JsonProperty("stu_grade")
  private Integer stuGrade; // 学生年级，对应数据库的 stu_grade 字段

  @JsonProperty("stu_sch")
  private String stuSch; // 学生所属学院，对应数据库的 stu_sch 字段

  @JsonProperty("stu_picture")
  private String stuPicture; // 学生照片存储路径，对应数据库的 stu_picture 字段

  @JsonProperty("stu_account")
  private String stuAccount; // 学生账号，对应数据库的 stu_account 字段

  @JsonProperty("stu_password")
  private String stuPassword; // 学生密码，对应数据库的 stu_password 字段

  @JsonProperty("stu_status")
  private Integer stuStatus; // 学生状态，对应数据库的 stu_status 字段

  // 默认构造函数
  public StudentTab() {}

  // 全参构造函数
  public StudentTab(Integer stuId, String stuName, String stuSex, String stuTel, Integer stuGrade, String stuSch, String stuPicture, String stuAccount, String stuPassword, Integer stuStatus) {
    this.stuId = stuId;
    this.stuName = stuName;
    this.stuSex = stuSex;
    this.stuTel = stuTel;
    this.stuGrade = stuGrade;
    this.stuSch = stuSch;
    this.stuPicture = stuPicture;
    this.stuAccount = stuAccount;
    this.stuPassword = stuPassword;
    this.stuStatus = stuStatus;
  }

  // Getters and Setters
  public Integer getStuId() {
    return stuId;
  }

  public void setStuId(Integer stuId) {
    this.stuId = stuId;
  }

  public String getStuName() {
    return stuName;
  }

  public void setStuName(String stuName) {
    this.stuName = stuName;
  }

  public String getStuSex() {
    return stuSex;
  }

  public void setStuSex(String stuSex) {
    this.stuSex = stuSex;
  }

  public String getStuTel() {
    return stuTel;
  }

  public void setStuTel(String stuTel) {
    this.stuTel = stuTel;
  }

  public Integer getStuGrade() {
    return stuGrade;
  }

  public void setStuGrade(Integer stuGrade) {
    this.stuGrade = stuGrade;
  }

  public String getStuSch() {
    return stuSch;
  }

  public void setStuSch(String stuSch) {
    this.stuSch = stuSch;
  }

  public String getStuPicture() {
    return stuPicture;
  }

  public void setStuPicture(String stuPicture) {
    this.stuPicture = stuPicture;
  }

  public String getStuAccount() {
    return stuAccount;
  }

  public void setStuAccount(String stuAccount) {
    this.stuAccount = stuAccount;
  }

  public String getStuPassword() {
    return stuPassword;
  }

  public void setStuPassword(String stuPassword) {
    this.stuPassword = stuPassword;
  }

  public Integer getStuStatus() {
    return stuStatus;
  }

  public void setStuStatus(Integer stuStatus) {
    this.stuStatus = stuStatus;
  }

  @Override
  public String toString() {
    return "StudentTab{" +
            "stuId=" + stuId +
            ", stuName='" + stuName + '\'' +
            ", stuSex='" + stuSex + '\'' +
            ", stuTel='" + stuTel + '\'' +
            ", stuGrade=" + stuGrade +
            ", stuSch='" + stuSch + '\'' +
            ", stuPicture='" + stuPicture + '\'' +
            ", stuAccount='" + stuAccount + '\'' +
            ", stuPassword='" + stuPassword + '\'' +
            ", stuStatus=" + stuStatus +
            '}';
  }
}