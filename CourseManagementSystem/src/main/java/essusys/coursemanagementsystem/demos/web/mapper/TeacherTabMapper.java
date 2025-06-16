package essusys.coursemanagementsystem.demos.web.mapper;

import essusys.coursemanagementsystem.demos.web.entity.TeacherTab;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public interface TeacherTabMapper {

    // 条件查询教师信息
    @SelectProvider(type = TeacherTabSqlProvider.class, method = "findByOption")
    @ResultMap("TeacherTabResultMap")
    List<TeacherTab> findByOption(@Param("tchName") String tchName,
                                  @Param("tchSch") String tchSch,
                                  @Param("tchStatus") Integer tchStatus);

    // 根据账户查询教师信息
    @Select("SELECT * FROM teacher_tab WHERE tch_account = #{tchAccount}")
    @ResultMap("TeacherTabResultMap")
    TeacherTab findByAccount(@Param("tchAccount") String tchAccount);

    // 根据教师Id查询教师信息
    @Select("SELECT * FROM teacher_tab WHERE tch_id = #{tchId}")
    @ResultMap("TeacherTabResultMap")
    TeacherTab findById(@Param("tchId") Integer tchId);

    // 插入教师信息
    @Insert("INSERT INTO teacher_tab (tch_id, tch_name, tch_sex, tch_sch, tch_tel, tch_picture, tch_account, tch_password, tch_status) " +
            "VALUES (#{tchId}, #{tchName}, #{tchSex}, #{tchSch}, #{tchTel}, #{tchPicture}, #{tchAccount}, #{tchPassword}, #{tchStatus})")
    @Options(useGeneratedKeys = true, keyProperty = "tchId")
    void insert(TeacherTab teacherTab);

    // 更新教师信息
    @Update("UPDATE teacher_tab SET tch_name = #{tchName}, tch_sex = #{tchSex}, tch_sch = #{tchSch}, tch_tel = #{tchTel}, " +
            "tch_picture = #{tchPicture}, tch_account = #{tchAccount}, tch_status = #{tchStatus} WHERE tch_id = #{tchId}")
    void update(TeacherTab teacherTab);

    // 根据账户修改密码
    @Update("UPDATE teacher_tab SET tch_password = #{tchPassword} WHERE tch_account = #{tchAccount}")
    void updatePassword(@Param("tchAccount") String tchAccount, @Param("tchPassword") String tchPassword);

    // 删除教师信息
    @Delete("DELETE FROM teacher_tab WHERE tch_id = #{tchId}")
    void deleteById(@Param("tchId") Integer tchId);

    // SQL 提供类
    class TeacherTabSqlProvider {
        public String findByOption(Map<String, Object> params) {
            return new SQL() {{
                SELECT("*");
                FROM("teacher_tab");
                WHERE("1=1");
                if (params.get("tchName") != null && !params.get("tchName").toString().isEmpty()) {
                    AND().WHERE("tch_name LIKE CONCAT('%', #{tchName}, '%')");
                }
                if (params.get("tchSch") != null && !params.get("tchSch").toString().isEmpty()) {
                    AND().WHERE("tch_sch = #{tchSch}");
                }
                if (params.get("tchStatus") != null) {
                    AND().WHERE("tch_status = #{tchStatus}");
                }
            }}.toString();
        }
    }
}