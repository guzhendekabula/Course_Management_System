package essusys.coursemanagementsystem.demos.web.mapper;

import essusys.coursemanagementsystem.demos.web.entity.ActivityControlTab;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public interface ActivityControlTabMapper {
    // 动态SQL提供类
    class ActivityControlTabSqlProvider {
        public String findByOption(Map<String, Object> params) {
            SQL sql = new SQL();
            sql.SELECT("*")
                    .FROM("activitycontrol_tab")
                    .WHERE("1=1");

            if (params.get("ctrlStatus") != null) {
                sql.AND().WHERE("ctrl_status = #{ctrlStatus}");
            }
            if (params.get("ctrlType") != null) {
                sql.AND().WHERE("ctrl_type = #{ctrlType}");
            }
            if (params.get("ctrlName") != null && !params.get("ctrlName").toString().isEmpty()) {
                sql.AND().WHERE("ctrl_name LIKE CONCAT('%', #{ctrlName}, '%')");
            }

            return sql.toString();
        }
    }
    // 根据条件查询活动
    @SelectProvider(type = ActivityControlTabSqlProvider.class, method = "findByOption")
    @ResultMap("ActivityControlTabResultMap")
    List<ActivityControlTab> findByOption(@Param("ctrlStatus") Integer ctrlStatus,
                                          @Param("ctrlType") Integer ctrlType,
                                          @Param("ctrlName") String ctrlName);

    // 根据ID查询活动
    /*
    @Select("SELECT * FROM activitycontrol_tab WHERE ctrl_id = #{ctrlId}")
    @ResultMap("ActivityControlTabResultMap")
    ActivityControlTab findById(@Param("ctrlId") Integer ctrlId);
    */

    // 插入活动
    @Insert("INSERT INTO activitycontrol_tab (ctrl_name, ctrl_number, ctrl_startdate, ctrl_enddate, ctrl_status, ctrl_type) " +
            "VALUES (#{ctrlName}, #{ctrlNumber}, #{ctrlStartDate}, #{ctrlEndDate}, #{ctrlStatus}, #{ctrlType})")
    @Options(useGeneratedKeys = true, keyProperty = "ctrlId")
    void insert(ActivityControlTab activityControlTab);

    // 更新活动
    @Update("UPDATE activitycontrol_tab SET ctrl_name = #{ctrlName}, ctrl_number = #{ctrlNumber}, ctrl_startdate = #{ctrlStartDate}, " +
            "ctrl_enddate = #{ctrlEndDate}, ctrl_status = #{ctrlStatus}, ctrl_type = #{ctrlType} WHERE ctrl_id = #{ctrlId}")
    void update(ActivityControlTab activityControlTab);

    // 删除活动
    @Delete("DELETE FROM activitycontrol_tab WHERE ctrl_id = #{ctrlId}")
    void deleteById(@Param("ctrlId") Integer ctrlId);

}