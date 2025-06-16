package essusys.coursemanagementsystem.demos.web.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminTab {

  @JsonProperty("adm_id")
  private Integer admId; // 教务员ID，对应数据库的 adm_id 字段

  @JsonProperty("adm_name")
  private String admName; // 教务员姓名，对应数据库的 adm_name 字段

  @JsonProperty("adm_rank")
  private Integer admRank; // 教务员等级，对应数据库的 adm_rank 字段

  @JsonProperty("adm_tel")
  private String admTel; // 教务员电话，对应数据库的 adm_tel 字段

  @JsonProperty("adm_account")
  private String admAccount; // 教务员账号，对应数据库的 adm_account 字段

  @JsonProperty("adm_password")
  private String admPassword; // 教务员密码，对应数据库的 adm_password 字段

  @JsonProperty("adm_status")
  private Integer admStatus; // 教务员状态，对应数据库的 adm_status 字段

  // 默认构造函数
  public AdminTab() {}

  // 全参构造函数
  public AdminTab(Integer admId, String admName, Integer admRank, String admTel, String admAccount, String admPassword, Integer admStatus) {
    this.admId = admId;
    this.admName = admName;
    this.admRank = admRank;
    this.admTel = admTel;
    this.admAccount = admAccount;
    this.admPassword = admPassword;
    this.admStatus = admStatus;
  }

  // Getters and Setters
  public Integer getAdmId() {
    return admId;
  }

  public void setAdmId(Integer admId) {
    this.admId = admId;
  }

  public String getAdmName() {
    return admName;
  }

  public void setAdmName(String admName) {
    this.admName = admName;
  }

  public Integer getAdmRank() {
    return admRank;
  }

  public void setAdmRank(Integer admRank) {
    this.admRank = admRank;
  }

  public String getAdmTel() {
    return admTel;
  }

  public void setAdmTel(String admTel) {
    this.admTel = admTel;
  }

  public String getAdmAccount() {
    return admAccount;
  }

  public void setAdmAccount(String admAccount) {
    this.admAccount = admAccount;
  }

  public String getAdmPassword() {
    return admPassword;
  }

  public void setAdmPassword(String admPassword) {
    this.admPassword = admPassword;
  }

  public Integer getAdmStatus() {
    return admStatus;
  }

  public void setAdmStatus(Integer admStatus) {
    this.admStatus = admStatus;
  }

  @Override
  public String toString() {
    return "AdminTab{" +
            "admId=" + admId +
            ", admName='" + admName + '\'' +
            ", admRank=" + admRank +
            ", admTel='" + admTel + '\'' +
            ", admAccount='" + admAccount + '\'' +
            ", admPassword='" + admPassword + '\'' +
            ", admStatus=" + admStatus +
            '}';
  }
}