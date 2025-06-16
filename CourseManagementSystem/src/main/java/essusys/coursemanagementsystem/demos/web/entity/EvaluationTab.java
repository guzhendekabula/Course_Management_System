package essusys.coursemanagementsystem.demos.web.entity;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * evaluation_tab
 */
@ApiModel(description = "evaluation_tab")

public class EvaluationTab   {
  @JsonProperty("eva_tch_id")
  private Integer evaTchId;

  @JsonProperty("eva_stu_id")
  private Integer evaStuId;

  @JsonProperty("eva_cla_id")
  private Integer evaClaId;

  @JsonProperty("eva_score")
  private BigDecimal evaScore;

  @JsonProperty("eva_text")
  private String evaText = "null";

  @JsonProperty("eva_item_1")
  private Integer evaItem1;

  @JsonProperty("eva_item_2")
  private Integer evaItem2;

  @JsonProperty("eva_item_3")
  private Integer evaItem3;

  @JsonProperty("eva_item_4")
  private Integer evaItem4;

  public  EvaluationTab() {}

  public EvaluationTab evaTchId(Integer evaTchId) {
    this.evaTchId = evaTchId;
    return this;
  }

  /**
   * 教师id外键
   * minimum: 1
   * maximum: 2147483647
   * @return evaTchId
  */
  @ApiModelProperty(required = true, value = "教师id外键")
  @NotNull

@Min(1) @Max(2147483647) 
  public Integer getEvaTchId() {
    return evaTchId;
  }

  public void setEvaTchId(Integer evaTchId) {
    this.evaTchId = evaTchId;
  }

  public EvaluationTab evaStuId(Integer evaStuId) {
    this.evaStuId = evaStuId;
    return this;
  }

  /**
   * 学生id外键
   * minimum: 1
   * maximum: 2147483647
   * @return evaStuId
  */
  @ApiModelProperty(required = true, value = "学生id外键")
  @NotNull

@Min(1) @Max(2147483647) 
  public Integer getEvaStuId() {
    return evaStuId;
  }

  public void setEvaStuId(Integer evaStuId) {
    this.evaStuId = evaStuId;
  }

  public EvaluationTab evaClaId(Integer evaClaId) {
    this.evaClaId = evaClaId;
    return this;
  }

  /**
   * 教学班id外键
   * minimum: 1
   * maximum: 2147483647
   * @return evaClaId
  */
  @ApiModelProperty(required = true, value = "教学班id外键")
  @NotNull

@Min(1) @Max(2147483647) 
  public Integer getEvaClaId() {
    return evaClaId;
  }

  public void setEvaClaId(Integer evaClaId) {
    this.evaClaId = evaClaId;
  }

  public EvaluationTab evaScore(BigDecimal evaScore) {
    this.evaScore = evaScore;
    return this;
  }

  /**
   * 分数
   * @return evaScore
  */
  @ApiModelProperty(value = "分数")

  @Valid

  public BigDecimal getEvaScore() {
    return evaScore;
  }

  public void setEvaScore(BigDecimal evaScore) {
    this.evaScore = evaScore;
  }

  public EvaluationTab evaText(String evaText) {
    this.evaText = evaText;
    return this;
  }

  /**
   * 评语
   * @return evaText
  */
  @ApiModelProperty(value = "评语")

@Size(max=1024) 
  public String getEvaText() {
    return evaText;
  }

  public void setEvaText(String evaText) {
    this.evaText = evaText;
  }

  public EvaluationTab evaItem1(Integer evaItem1) {
    this.evaItem1 = evaItem1;
    return this;
  }

  /**
   * 评分项1
   * minimum: -2147483648
   * maximum: 2147483647
   * @return evaItem1
  */
  @ApiModelProperty(value = "评分项1")

@Min(-2147483648) @Max(2147483647) 
  public Integer getEvaItem1() {
    return evaItem1;
  }

  public void setEvaItem1(Integer evaItem1) {
    this.evaItem1 = evaItem1;
  }

  public EvaluationTab evaItem2(Integer evaItem2) {
    this.evaItem2 = evaItem2;
    return this;
  }

  /**
   * 评分项2
   * minimum: -2147483648
   * maximum: 2147483647
   * @return evaItem2
  */
  @ApiModelProperty(value = "评分项2")

@Min(-2147483648) @Max(2147483647) 
  public Integer getEvaItem2() {
    return evaItem2;
  }

  public void setEvaItem2(Integer evaItem2) {
    this.evaItem2 = evaItem2;
  }

  public EvaluationTab evaItem3(Integer evaItem3) {
    this.evaItem3 = evaItem3;
    return this;
  }

  /**
   * 评分项3
   * minimum: -2147483648
   * maximum: 2147483647
   * @return evaItem3
  */
  @ApiModelProperty(value = "评分项3")

@Min(-2147483648) @Max(2147483647) 
  public Integer getEvaItem3() {
    return evaItem3;
  }

  public void setEvaItem3(Integer evaItem3) {
    this.evaItem3 = evaItem3;
  }

  public EvaluationTab evaItem4(Integer evaItem4) {
    this.evaItem4 = evaItem4;
    return this;
  }

  /**
   * 评分项4
   * minimum: -2147483648
   * maximum: 2147483647
   * @return evaItem4
  */
  @ApiModelProperty(value = "评分项4")

@Min(-2147483648) @Max(2147483647) 
  public Integer getEvaItem4() {
    return evaItem4;
  }

  public void setEvaItem4(Integer evaItem4) {
    this.evaItem4 = evaItem4;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EvaluationTab evaluationTab = (EvaluationTab) o;
    return Objects.equals(this.evaTchId, evaluationTab.evaTchId) &&
        Objects.equals(this.evaStuId, evaluationTab.evaStuId) &&
        Objects.equals(this.evaClaId, evaluationTab.evaClaId) &&
        Objects.equals(this.evaScore, evaluationTab.evaScore) &&
        Objects.equals(this.evaText, evaluationTab.evaText) &&
        Objects.equals(this.evaItem1, evaluationTab.evaItem1) &&
        Objects.equals(this.evaItem2, evaluationTab.evaItem2) &&
        Objects.equals(this.evaItem3, evaluationTab.evaItem3) &&
        Objects.equals(this.evaItem4, evaluationTab.evaItem4);
  }

  @Override
  public int hashCode() {
    return Objects.hash(evaTchId, evaStuId, evaClaId, evaScore, evaText, evaItem1, evaItem2, evaItem3, evaItem4);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EvaluationTab {\n");
    
    sb.append("    evaTchId: ").append(toIndentedString(evaTchId)).append("\n");
    sb.append("    evaStuId: ").append(toIndentedString(evaStuId)).append("\n");
    sb.append("    evaClaId: ").append(toIndentedString(evaClaId)).append("\n");
    sb.append("    evaScore: ").append(toIndentedString(evaScore)).append("\n");
    sb.append("    evaText: ").append(toIndentedString(evaText)).append("\n");
    sb.append("    evaItem1: ").append(toIndentedString(evaItem1)).append("\n");
    sb.append("    evaItem2: ").append(toIndentedString(evaItem2)).append("\n");
    sb.append("    evaItem3: ").append(toIndentedString(evaItem3)).append("\n");
    sb.append("    evaItem4: ").append(toIndentedString(evaItem4)).append("\n");
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

