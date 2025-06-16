package essusys.coursemanagementsystem.demos.web.mapper;

import essusys.coursemanagementsystem.demos.web.entity.CourseTab;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public interface CourseTabMapper {

    // 实现条件选择查询和模糊查询的动态SQL语句
    public class CourseTabSqlProvider {
        public String findByOption(Map<String, Object> params) {
            SQL sql = new SQL();
            sql.SELECT("*")
                    .FROM("course_tab")
                    .WHERE("1=1");

            if (params.get("couTerm") != null) {
                sql.AND().WHERE("cou_term = #{couTerm}");
            }
            if (params.get("couType") != null && !params.get("couType").toString().isEmpty()) {
                sql.AND().WHERE("cou_type = #{couType}");
            }
            if (params.get("couStatus") != null) {
                sql.AND().WHERE("cou_status = #{couStatus}");
            }
            if (params.get("couName") != null && !params.get("couName").toString().isEmpty()) {
                sql.AND().WHERE("cou_name LIKE CONCAT('%', #{couName}, '%')");
            }

            return sql.toString();
        }
    }

    /*
     *
     * 查询模块
     *
     */
    // 根据条件查询课程,支持课程名模糊查询
    @SelectProvider(type = CourseTabSqlProvider.class, method = "findByOption")
    @ResultMap("CourseTabResultMap")  // 引用XML中定义的结果映射
    List<CourseTab> findByOption(@Param("couTerm") Integer couTerm,
                                 @Param("couType") String couType,
                                 @Param("couStatus") Integer couStatus,
                                 @Param("couName") String couName);

    // 根据课程识别码查询课程(唯一)
    @Select("SELECT * FROM course_tab WHERE cou_code = #{couCode}")
    @ResultMap("CourseTabResultMap")
    CourseTab findByCode(@Param("couCode") String couCode);

    // 根据课程识别码查询课程状态(唯一)
    @Select("SELECT cou_status FROM course_tab WHERE cou_code = #{couCode}")
    Integer getStatusByCode(@Param("couCode") String couCode);

    /*
     *
     * 增删改模块
     *
     */
    @Insert("INSERT INTO course_tab (cou_code, cou_name, cou_ifexam, cou_ifscore, cou_term, cou_text, cou_type, cou_language, cou_rank, cou_status, cou_school, cou_score) " +
            "VALUES (#{couCode}, #{couName}, #{couIfexam}, #{couIfscore}, #{couTerm}, #{couText}, #{couType}, #{couLanguage}, #{couRank}, #{couStatus}, #{couSchool}, #{couScore})")
    void insert(CourseTab courseTab);

    @Update("UPDATE course_tab SET cou_name = #{couName}, cou_ifexam = #{couIfexam}, cou_ifscore = #{couIfscore}, cou_term = #{couTerm}, cou_text = #{couText}, " +
            "cou_type = #{couType}, cou_language = #{couLanguage}, cou_rank = #{couRank}, cou_status = #{couStatus}, cou_school = #{couSchool}, cou_score = #{couScore} WHERE cou_code = #{couCode}")
    void update(CourseTab courseTab);

    // 修改课程状态码
    @Update("UPDATE course_tab SET cou_status = #{couStatus} WHERE cou_code = #{couCode}")
    void updateStatus(@Param("couStatus") Integer couStatus, @Param("couCode") String couCode);

    // 通过审批(修改课程状态码为3-退课关闭)
    @Update("UPDATE course_tab SET cou_status = 3 WHERE cou_code = #{couCode}")
    void updateStatusTo3(@Param("couCode") String couCode);

    // 审批驳回(修改课程状态码为5-审批驳回)
    @Update("UPDATE course_tab SET cou_status = 3 WHERE cou_code = #{couCode}")
    void updateStatusTo5(@Param("couCode") String couCode);

    // 修改课程开设学期
    @Update("UPDATE course_tab SET cou_term = #{couTerm} WHERE cou_code = #{couCode}")
    void updateTerm(@Param("couTerm") Integer couTerm, @Param("couCode") String couCode);

    // 删除临时表
    @Delete("DELETE FROM course_tab WHERE cou_code = #{couCode}")
    void deleteByCode(@Param("couCode") String couCode);
}

