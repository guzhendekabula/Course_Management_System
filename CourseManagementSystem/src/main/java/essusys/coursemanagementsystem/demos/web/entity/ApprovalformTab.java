package essusys.coursemanagementsystem.demos.web.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "approvalform_tab")
public class ApprovalFormTab {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("apf_id")
  @Column(name = "apf_id")
  private Integer apfId; // 开课审批号

  @JsonProperty("apf_adm_id")
  @Column(name = "apf_adm_id")
  private Integer apfAdmId; // 审批教务员id

  @JsonProperty("apf_tch_id")
  @Column(name = "apf_tch_id", nullable = false)
  private Integer apfTchId; // 提交审批的教师id

  @JsonProperty("apf_cou_code")
  @Column(name = "apf_cou_code", nullable = false, length = 20)
  private String apfCouCode; // 待审批课程识别码

  @JsonProperty("apf_status")
  @Column(name = "apf_status", nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 0")
  private Integer apfStatus = 0; // 审批状态(0-未审批，1-审批通过，2-审批驳回)

  @JsonProperty("apf_note")
  @Column(name = "apf_note", length = 300)
  private String apfNote; // 审批意见

  // 默认构造函数
  public ApprovalFormTab() {}

  // 全参构造函数
  public ApprovalFormTab(Integer apfId, Integer apfAdmId, Integer apfTchId, String apfCouCode, Integer apfStatus, String apfNote) {
    this.apfId = apfId;
    this.apfAdmId = apfAdmId;
    this.apfTchId = apfTchId;
    this.apfCouCode = apfCouCode;
    this.apfStatus = apfStatus;
    this.apfNote = apfNote;
  }

  // Getters and Setters
  public Integer getApfId() {
    return apfId;
  }

  public void setApfId(Integer apfId) {
    this.apfId = apfId;
  }

  public Integer getApfAdmId() {
    return apfAdmId;
  }

  public void setApfAdmId(Integer apfAdmId) {
    this.apfAdmId = apfAdmId;
  }

  public Integer getApfTchId() {
    return apfTchId;
  }

  public void setApfTchId(Integer apfTchId) {
    this.apfTchId = apfTchId;
  }

  public String getApfCouCode() {
    return apfCouCode;
  }

  public void setApfCouCode(String apfCouCode) {
    this.apfCouCode = apfCouCode;
  }

  public Integer getApfStatus() {
    return apfStatus;
  }

  public void setApfStatus(Integer apfStatus) {
    this.apfStatus = apfStatus;
  }

  public String getApfNote() {
    return apfNote;
  }

  public void setApfNote(String apfNote) {
    this.apfNote = apfNote;
  }

  @Override
  public String toString() {
    return "ApprovalFormTab{" +
            "apfId=" + apfId +
            ", apfAdmId=" + apfAdmId +
            ", apfTchId=" + apfTchId +
            ", apfCouCode='" + apfCouCode + '\'' +
            ", apfStatus=" + apfStatus +
            ", apfNote='" + apfNote + '\'' +
            '}';
  }
}