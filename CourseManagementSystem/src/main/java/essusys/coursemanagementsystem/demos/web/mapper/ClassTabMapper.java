package essusys.coursemanagementsystem.demos.web.mapper;

import essusys.coursemanagementsystem.demos.web.entity.ClassTab;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public interface ClassTabMapper {
    // 实现条件选择查询和模糊查询的动态SQL语句
    public class ClassTabSqlProvider {
        public String findByOption(Map<String, Object> params) {
            SQL sql = new SQL();
            sql.SELECT("*")
                    .FROM("class_tab")
                    .WHERE("1=1");

            if (params.get("claCouCode") != null && !params.get("claCouCode").toString().isEmpty()) {
                sql.AND().WHERE("cla_cou_code = #{claCouCode}");
            }
            if (params.get("claTchId") != null) {
                sql.AND().WHERE("cla_tch_id = #{claTchId}");
            }
            // 对于学院条件，检查四个学院字段
            if (params.get("claToschool") != null && !params.get("claToschool").toString().isEmpty()) {
                String school = params.get("claToschool").toString();
                sql.AND().WHERE("(cla_toschool_1 = #{claToschool} OR " +
                        "cla_toschool_2 = #{claToschool} OR " +
                        "cla_toschool_3 = #{claToschool} OR " +
                        "cla_toschool_4 = #{claToschool})");
            }
            return sql.toString();
        }
    }

    // 根据课程码、教师ID、学院条件查询教学班信息
    @SelectProvider(type = ClassTabSqlProvider.class, method = "findByOption")
    @ResultMap("ClassTabResultMap")
    List<ClassTab> findByOption(@Param("claCouCode") String claCouCode,
                                @Param("claTchId") Integer claTchId,
                                @Param("claToschool") String claToschool);

    // 根据学号查询教学班信息
    @Select("SELECT DISTINCT c.* FROM class_tab c " +
            "JOIN score_tab s ON c.cla_id = s.sco_cla_id " +
            "WHERE s.sco_stu_id = #{stuId}")
    @ResultMap("ClassTabResultMap")
    List<ClassTab> getClassByStuId(@Param("stuId") Integer stuId);

    // 根据教职工号查询教学班号对应的课程代码（可能返回多个结果）
    @Select("SELECT cla_cou_code FROM class_tab WHERE cla_tch_id = #{claTchId}")
    List<String> getClassCouCodeByTchId(@Param("claTchId") Integer claTchId);

    // 根据教职工号查询教学班号（可能返回多个结果）
    @Select("SELECT * FROM class_tab WHERE cla_tch_id = #{claTchId}")
    @ResultMap("ClassTabResultMap")
    List<ClassTab> getClassByTchId(@Param("claTchId") Integer claTchId);

    // 更新教学班信息
    @Update("UPDATE class_tab SET cla_cou_code = #{claCouCode}, cla_tch_id = #{claTchId}, " +
            "cla_toschool_1 = #{claToschool1}, cla_toschool_2 = #{claToschool2}, " +
            "cla_toschool_3 = #{claToschool3}, cla_toschool_4 = #{claToschool4}, " +
            "cla_timeset = #{claTimeset}, cla_weekset = #{claWeekset}, " +
            "cla_classroom = #{claClassroom} " +
            "WHERE cla_id = #{claId}")
    void update(ClassTab classTab);

    // 插入新的教学班信息
    @Insert("INSERT INTO class_tab (cla_cou_code, cla_tch_id, cla_toschool_1, cla_toschool_2, cla_toschool_3, " +
            "cla_toschool_4, cla_timeset, cla_weekset, cla_classroom) " +
            "VALUES (#{claCouCode}, #{claTchId}, #{claToschool1}, #{claToschool2}, #{claToschool3}, " +
            "#{claToschool4}, #{claTimeset}, #{claWeekset}, #{claClassroom})")
    @Options(useGeneratedKeys = true, keyProperty = "claId")
    void insert(ClassTab classTab);

    // 根据ID删除教学班信息
    @Delete("DELETE FROM class_tab WHERE cla_id = #{claId}")
    void deleteById(@Param("claId") Integer claId);

}
