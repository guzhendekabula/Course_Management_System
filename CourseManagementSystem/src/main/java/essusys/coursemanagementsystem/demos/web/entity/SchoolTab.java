package essusys.coursemanagementsystem.demos.web.entity;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;

/**
 * SchoolTab
 */

public class SchoolTab   {
  @JsonProperty("sch_id")
  private Integer schId;

  @JsonProperty("sch_name")
  private String schName;

  public SchoolTab schId(Integer schId) {
    this.schId = schId;
    return this;
  }

  public SchoolTab() {}

  /**
   * Get schId
   * minimum: -2147483648
   * maximum: 2147483647
   * @return schId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Min(-2147483648) @Max(2147483647) 
  public Integer getSchId() {
    return schId;
  }

  public void setSchId(Integer schId) {
    this.schId = schId;
  }

  public SchoolTab schName(String schName) {
    this.schName = schName;
    return this;
  }

  /**
   * Get schName
   * @return schName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

@Size(max=25) 
  public String getSchName() {
    return schName;
  }

  public void setSchName(String schName) {
    this.schName = schName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SchoolTab schoolTab = (SchoolTab) o;
    return Objects.equals(this.schId, schoolTab.schId) &&
        Objects.equals(this.schName, schoolTab.schName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(schId, schName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SchoolTab {\n");
    
    sb.append("    schId: ").append(toIndentedString(schId)).append("\n");
    sb.append("    schName: ").append(toIndentedString(schName)).append("\n");
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

