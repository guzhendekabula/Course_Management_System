package essusys.coursemanagementsystem.demos.web.mapper;

import essusys.coursemanagementsystem.demos.web.entity.StudentTab;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public interface StudentTabMapper {
    // 实现条件选择查询和模糊查询的动态SQL语句
    public class StudentTabSqlProvider {
        public String findByOption(Map<String, Object> params) {
            SQL sql = new SQL();
            sql.SELECT("*")
                    .FROM("student_tab")
                    .WHERE("1=1");

            if (params.get("stuGrade") != null) {
                sql.AND().WHERE("stu_grade = #{stuGrade}");
            }
            if (params.get("stuSch") != null && !params.get("stuSch").toString().isEmpty()) {
                sql.AND().WHERE("stu_sch = #{stuSch}");
            }
            if (params.get("stuStatus") != null) {
                sql.AND().WHERE("stu_status = #{stuStatus}");
            }
            if (params.get("stuName") != null && !params.get("stuName").toString().isEmpty()) {
                sql.AND().WHERE("stu_name LIKE CONCAT('%', #{stuName}, '%')");
            }

            // System.out.println("Generated SQL: " + finalSql);
            return sql.toString();
        }
    }


    /*
     *
     * 查询模块
     *
     */
    // 根据条件查询学生,支持姓名模糊查询
    @SelectProvider(type = StudentTabSqlProvider.class, method = "findByOption")
    @ResultMap("StudentTabResultMap")  // 引用XML中定义的结果映射
    List<StudentTab> findByOption(@Param("stuGrade") Integer stuGrade,
                                  @Param("stuSch") String stuSch,
                                  @Param("stuStatus") Integer stuStatus,
                                  @Param("stuName") String stuName);

    // 根据账户查询学生(唯一)
    @Select("SELECT * FROM student_tab WHERE stu_account = #{stuAccount}")
    @ResultMap("StudentTabResultMap")
    StudentTab findByAccount(@Param("stuAccount") String stuAccount);

    /*
     *
     * 增删改模块
     *
     */
    @Insert("INSERT INTO student_tab (stu_id, stu_name, stu_sex, stu_tel, stu_grade, stu_sch, stu_picture, stu_account, stu_password, stu_status) " +
            "VALUES (#{stuId}, #{stuName}, #{stuSex}, #{stuTel}, #{stuGrade}, #{stuSch}, #{stuPicture}, #{stuAccount}, #{stuPassword}, #{stuStatus})")
    @Options(useGeneratedKeys = true, keyProperty = "stuId")    // 让系统自己生成自增长的主键
    void insert(StudentTab studentTab);

    // 根据账户修改密码
    @Update("UPDATE student_tab SET stu_password = #{stuPassword} WHERE stu_account = #{stuAccount}")
    void updatePassword(@Param("stuAccount") String stuAccount, @Param("stuPassword") String stuPassword);

    // 教务员无权更改用户密码
    @Update("UPDATE student_tab SET stu_name = #{stuName}, stu_sex = #{stuSex}, stu_tel = #{stuTel}, stu_grade = #{stuGrade}, stu_sch = #{stuSch}, " +
            "stu_picture = #{stuPicture}, stu_account = #{stuAccount}, stu_status = #{stuStatus} WHERE stu_id = #{stuId}")
    void update(StudentTab studentTab);

    @Delete("DELETE FROM student_tab WHERE stu_id = #{stuId}")
    void deleteById(@Param("stuId") Integer stuId);
}
