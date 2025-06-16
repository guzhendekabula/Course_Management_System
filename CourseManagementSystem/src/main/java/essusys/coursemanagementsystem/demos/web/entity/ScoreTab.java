package essusys.coursemanagementsystem.demos.web.entity;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * ScoreTab
 */

public class ScoreTab   {
  @JsonProperty("sco_stu_id")
  private Integer scoStuId;

  @JsonProperty("sco_cla_id")
  private Integer scoClaId;

  @JsonProperty("sco_score_1")
  private BigDecimal scoScore1;

  @JsonProperty("sco_item_1")
  private Integer scoItem1;

  @JsonProperty("sco_item_2")
  private Integer scoItem2;

  @JsonProperty("sco_item_3")
  private Integer scoItem3;

  @JsonProperty("sco_item_4")
  private Integer scoItem4;

  @JsonProperty("sco_note")
  private String scoNote;

  @JsonProperty("sco_score_2")
  private BigDecimal scoScore2;

  public ScoreTab() {}

  public ScoreTab scoStuId(Integer scoStuId) {
    this.scoStuId = scoStuId;
    return this;
  }

  /**
   * 学生id外键
   * minimum: -2147483648
   * maximum: 2147483647
   * @return scoStuId
  */
  @ApiModelProperty(required = true, value = "学生id外键")
  @NotNull

@Min(-2147483648) @Max(2147483647) 
  public Integer getScoStuId() {
    return scoStuId;
  }

  public void setScoStuId(Integer scoStuId) {
    this.scoStuId = scoStuId;
  }

  public ScoreTab scoClaId(Integer scoClaId) {
    this.scoClaId = scoClaId;
    return this;
  }

  /**
   * 课程i教学班id外键
   * minimum: -2147483648
   * maximum: 2147483647
   * @return scoClaId
  */
  @ApiModelProperty(required = true, value = "课程i教学班id外键")
  @NotNull

@Min(-2147483648) @Max(2147483647) 
  public Integer getScoClaId() {
    return scoClaId;
  }

  public void setScoClaId(Integer scoClaId) {
    this.scoClaId = scoClaId;
  }

  public ScoreTab scoScore1(BigDecimal scoScore1) {
    this.scoScore1 = scoScore1;
    return this;
  }

  /**
   * 成绩
   * @return scoScore1
  */
  @ApiModelProperty(value = "成绩")

  @Valid

  public BigDecimal getScoScore1() {
    return scoScore1;
  }

  public void setScoScore1(BigDecimal scoScore1) {
    this.scoScore1 = scoScore1;
  }

  public ScoreTab scoItem1(Integer scoItem1) {
    this.scoItem1 = scoItem1;
    return this;
  }

  /**
   * 评分项1
   * minimum: -2147483648
   * maximum: 2147483647
   * @return scoItem1
  */
  @ApiModelProperty(value = "评分项1")

@Min(-2147483648) @Max(2147483647) 
  public Integer getScoItem1() {
    return scoItem1;
  }

  public void setScoItem1(Integer scoItem1) {
    this.scoItem1 = scoItem1;
  }

  public ScoreTab scoItem2(Integer scoItem2) {
    this.scoItem2 = scoItem2;
    return this;
  }

  /**
   * 评分项2
   * minimum: -2147483648
   * maximum: 2147483647
   * @return scoItem2
  */
  @ApiModelProperty(value = "评分项2")

@Min(-2147483648) @Max(2147483647) 
  public Integer getScoItem2() {
    return scoItem2;
  }

  public void setScoItem2(Integer scoItem2) {
    this.scoItem2 = scoItem2;
  }

  public ScoreTab scoItem3(Integer scoItem3) {
    this.scoItem3 = scoItem3;
    return this;
  }

  /**
   * 评分项3
   * minimum: -2147483648
   * maximum: 2147483647
   * @return scoItem3
  */
  @ApiModelProperty(value = "评分项3")

@Min(-2147483648) @Max(2147483647) 
  public Integer getScoItem3() {
    return scoItem3;
  }

  public void setScoItem3(Integer scoItem3) {
    this.scoItem3 = scoItem3;
  }

  public ScoreTab scoItem4(Integer scoItem4) {
    this.scoItem4 = scoItem4;
    return this;
  }

  /**
   * 评分项4
   * minimum: -2147483648
   * maximum: 2147483647
   * @return scoItem4
  */
  @ApiModelProperty(value = "评分项4")

@Min(-2147483648) @Max(2147483647) 
  public Integer getScoItem4() {
    return scoItem4;
  }

  public void setScoItem4(Integer scoItem4) {
    this.scoItem4 = scoItem4;
  }

  public ScoreTab scoNote(String scoNote) {
    this.scoNote = scoNote;
    return this;
  }

  /**
   * 备注
   * @return scoNote
  */
  @ApiModelProperty(value = "备注")

@Size(max=200) 
  public String getScoNote() {
    return scoNote;
  }

  public void setScoNote(String scoNote) {
    this.scoNote = scoNote;
  }

  public ScoreTab scoScore2(BigDecimal scoScore2) {
    this.scoScore2 = scoScore2;
    return this;
  }

  /**
   * 折合绩点
   * @return scoScore2
  */
  @ApiModelProperty(value = "折合绩点")

  @Valid

  public BigDecimal getScoScore2() {
    return scoScore2;
  }

  public void setScoScore2(BigDecimal scoScore2) {
    this.scoScore2 = scoScore2;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScoreTab scoreTab = (ScoreTab) o;
    return Objects.equals(this.scoStuId, scoreTab.scoStuId) &&
        Objects.equals(this.scoClaId, scoreTab.scoClaId) &&
        Objects.equals(this.scoScore1, scoreTab.scoScore1) &&
        Objects.equals(this.scoItem1, scoreTab.scoItem1) &&
        Objects.equals(this.scoItem2, scoreTab.scoItem2) &&
        Objects.equals(this.scoItem3, scoreTab.scoItem3) &&
        Objects.equals(this.scoItem4, scoreTab.scoItem4) &&
        Objects.equals(this.scoNote, scoreTab.scoNote) &&
        Objects.equals(this.scoScore2, scoreTab.scoScore2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scoStuId, scoClaId, scoScore1, scoItem1, scoItem2, scoItem3, scoItem4, scoNote, scoScore2);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScoreTab {\n");
    
    sb.append("    scoStuId: ").append(toIndentedString(scoStuId)).append("\n");
    sb.append("    scoClaId: ").append(toIndentedString(scoClaId)).append("\n");
    sb.append("    scoScore1: ").append(toIndentedString(scoScore1)).append("\n");
    sb.append("    scoItem1: ").append(toIndentedString(scoItem1)).append("\n");
    sb.append("    scoItem2: ").append(toIndentedString(scoItem2)).append("\n");
    sb.append("    scoItem3: ").append(toIndentedString(scoItem3)).append("\n");
    sb.append("    scoItem4: ").append(toIndentedString(scoItem4)).append("\n");
    sb.append("    scoNote: ").append(toIndentedString(scoNote)).append("\n");
    sb.append("    scoScore2: ").append(toIndentedString(scoScore2)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

