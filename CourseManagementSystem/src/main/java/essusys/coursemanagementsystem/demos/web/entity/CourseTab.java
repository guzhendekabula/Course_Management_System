package essusys.coursemanagementsystem.demos.web.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseTab {

  @JsonProperty("cou_code")
  private String couCode; // 课程识别码，对应数据库的 cou_code 字段

  @JsonProperty("cou_name")
  private String couName; // 课程名，对应数据库的 cou_name 字段

  @JsonProperty("cou_ifexam")
  private String couIfexam; // 考查/考试，对应数据库的 cou_ifexam 字段

  @JsonProperty("cou_ifscore")
  private String couIfscore; // 评分/评级，对应数据库的 cou_ifscore 字段

  @JsonProperty("cou_term")
  private Integer couTerm; // 课程开设学期，对应数据库的 cou_term 字段

  @JsonProperty("cou_text")
  private String couText; // 课程大纲，对应数据库的 cou_text 字段

  @JsonProperty("cou_type")
  private String couType; // 课程类型，对应数据库的 cou_type 字段

  @JsonProperty("cou_language")
  private String couLanguage; // 课程语言，对应数据库的 cou_language 字段

  @JsonProperty("cou_rank")
  private String couRank; // 课程级别，对应数据库的 cou_rank 字段

  @JsonProperty("cou_status")
  private Integer couStatus; // 选课状态，对应数据库的 cou_status 字段

  @JsonProperty("cou_school")
  private String couSchool; // 授课单位名称，对应数据库的 cou_school 字段

  @JsonProperty("cou_score")
  private Integer couScore; // 课程学分，对应数据库的 cou_score 字段

  // 默认构造函数
  public CourseTab() {}

  // 全参构造函数
  public CourseTab(String couCode, String couName, String couIfexam, String couIfscore, Integer couTerm, String couText, String couType, String couLanguage, String couRank, Integer couStatus, String couSchool, Integer couScore) {
    this.couCode = couCode;
    this.couName = couName;
    this.couIfexam = couIfexam;
    this.couIfscore = couIfscore;
    this.couTerm = couTerm;
    this.couText = couText;
    this.couType = couType;
    this.couLanguage = couLanguage;
    this.couRank = couRank;
    this.couStatus = couStatus;
    this.couSchool = couSchool;
    this.couScore = couScore;
  }

  // 深拷贝构造函数
  public CourseTab(CourseTab tempCourseTab) {
      this.couCode = tempCourseTab.couCode;
      this.couName = tempCourseTab.couName;
      this.couIfexam = tempCourseTab.couIfexam;
      this.couIfscore = tempCourseTab.couIfscore;
      this.couTerm = tempCourseTab.couTerm;
      this.couText = tempCourseTab.couText;
      this.couType = tempCourseTab.couType;
      this.couLanguage = tempCourseTab.couLanguage;
      this.couRank = tempCourseTab.couRank;
      this.couStatus = tempCourseTab.couStatus;
      this.couSchool = tempCourseTab.couSchool;
      this.couScore = tempCourseTab.couScore;
  }

    // Getters and Setters
  public String getCouCode() {
    return couCode;
  }

  public void setCouCode(String couCode) {
    this.couCode = couCode;
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

  public String getCouIfscore() {
    return couIfscore;
  }

  public void setCouIfscore(String couIfscore) {
    this.couIfscore = couIfscore;
  }

  public Integer getCouTerm() {
    return couTerm;
  }

  public void setCouTerm(Integer couTerm) {
    this.couTerm = couTerm;
  }

  public String getCouText() {
    return couText;
  }

  public void setCouText(String couText) {
    this.couText = couText;
  }

  public String getCouType() {
    return couType;
  }

  public void setCouType(String couType) {
    this.couType = couType;
  }

  public String getCouLanguage() {
    return couLanguage;
  }

  public void setCouLanguage(String couLanguage) {
    this.couLanguage = couLanguage;
  }

  public String getCouRank() {
    return couRank;
  }

  public void setCouRank(String couRank) {
    this.couRank = couRank;
  }

  public Integer getCouStatus() {
    return couStatus;
  }

  public void setCouStatus(Integer couStatus) {
    this.couStatus = couStatus;
  }

  public String getCouSchool() {
    return couSchool;
  }

  public void setCouSchool(String couSchool) {
    this.couSchool = couSchool;
  }

  public Integer getCouScore() {
    return couScore;
  }

  public void setCouScore(Integer couScore) {
    this.couScore = couScore;
  }

  @Override
  public String toString() {
    return "CourseTab{" +
            "couCode='" + couCode + '\'' +
            ", couName='" + couName + '\'' +
            ", couIfexam='" + couIfexam + '\'' +
            ", couIfscore='" + couIfscore + '\'' +
            ", couTerm=" + couTerm +
            ", couText='" + couText + '\'' +
            ", couType='" + couType + '\'' +
            ", couLanguage='" + couLanguage + '\'' +
            ", couRank='" + couRank + '\'' +
            ", couStatus=" + couStatus +
            ", couSchool='" + couSchool + '\'' +
            ", couScore=" + couScore +
            '}';
  }
}