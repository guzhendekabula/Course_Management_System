package essusys.coursemanagementsystem.demos.web.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ActivityControlTab {

  @JsonProperty("ctrl_id")
  private Integer ctrlId; // 活动ID，对应数据库的 ctrl_id 字段

  @JsonProperty("ctrl_name")
  private String ctrlName; // 活动名称，对应数据库的 ctrl_name 字段

  @JsonProperty("ctrl_number")
  private Integer ctrlNumber; // 活动轮次序号，对应数据库的 ctrl_number 字段

  @JsonProperty("ctrl_startdate")
  private LocalDateTime ctrlStartDate; // 活动开始日期，对应数据库的 ctrl_startdate 字段

  @JsonProperty("ctrl_enddate")
  private LocalDateTime ctrlEndDate; // 活动结束日期，对应数据库的 ctrl_enddate 字段

  @JsonProperty("ctrl_status")
  private Integer ctrlStatus; // 活动状态，对应数据库的 ctrl_status 字段

  @JsonProperty("ctrl_type")
  private Integer ctrlType; // 活动类型，对应数据库的 ctrl_type 字段

  // 默认构造函数
  public ActivityControlTab() {}

  // 全参构造函数
  public ActivityControlTab(Integer ctrlId, String ctrlName, Integer ctrlNumber, LocalDateTime ctrlStartDate, LocalDateTime ctrlEndDate, Integer ctrlStatus, Integer ctrlType) {
    this.ctrlId = ctrlId;
    this.ctrlName = ctrlName;
    this.ctrlNumber = ctrlNumber;
    this.ctrlStartDate = ctrlStartDate;
    this.ctrlEndDate = ctrlEndDate;
    this.ctrlStatus = ctrlStatus;
    this.ctrlType = ctrlType;
  }

  @Override
  public String toString() {
    return "ActivityControlTab{" +
            "ctrlId=" + ctrlId +
            ", ctrlName='" + ctrlName + '\'' +
            ", ctrlNumber=" + ctrlNumber +
            ", ctrlStartDate=" + ctrlStartDate +
            ", ctrlEndDate=" + ctrlEndDate +
            ", ctrlStatus=" + ctrlStatus +
            ", ctrlType=" + ctrlType +
            '}';
  }
}