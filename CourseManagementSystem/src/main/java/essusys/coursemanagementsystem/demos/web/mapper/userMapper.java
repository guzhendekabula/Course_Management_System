package essusys.coursemanagementsystem.demos.web.mapper;

import essusys.coursemanagementsystem.demos.web.entity.AdminTab;
import essusys.coursemanagementsystem.demos.web.entity.StudentTab;
import essusys.coursemanagementsystem.demos.web.entity.TeacherTab;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

public interface userMapper {

    /*
     *
     * 根据账户查询数据体
     *
     */
    @Select("SELECT * FROM student_tab WHERE stu_account = #{account}")
    StudentTab getStudentByAccount(@Param("account") String account);

    @Select("SELECT * FROM teacher_tab WHERE tch_account = #{account}")
    TeacherTab getTeacherByAccount(@Param("account") String account);

    @Select("SELECT * FROM administrator_tab WHERE adm_account = #{account}")
    AdminTab getAdminByAccount(@Param("account") String account);


   /*
    *
    * 根据账户查询密码
    *
    */
   @Select("SELECT stu_password FROM student_tab WHERE stu_account = #{account}")
   String getStuPasswordByAccount(@Param("account") String account);

   @Select("SELECT tch_password FROM teacher_tab WHERE tch_account = #{account}")
   String getTchPasswordByAccount(@Param("account") String account);

   @Select("SELECT adm_password FROM administrator_tab WHERE adm_account = #{account}")
   String getAdmPasswordByAccount(@Param("account") String account);


    /*
     *
     * 根据账户查询id
     *
     */
    @Select("SELECT stu_id FROM student_tab WHERE stu_account = #{account}")
    Integer getStuidByAccount(@Param("account") String account);

    @Select("SELECT tch_id FROM teacher_tab WHERE tch_account = #{account}")
    Integer getTchidByAccount(@Param("account") String account);

    @Select("SELECT adm_id FROM administrator_tab WHERE adm_account = #{account}")
    Integer getAdmidByAccount(@Param("account") String account);

    /*
     *
     * 根据账户查询状态
     *
     */
    @Select("SELECT stu_status FROM student_tab WHERE stu_account = #{account}")
    Integer getStuStatusByAccount(@Param("account") String account);

    @Select("SELECT tch_status FROM teacher_tab WHERE tch_account = #{account}")
    Integer getTchStatusByAccount(@Param("account") String account);

    @Select("SELECT adm_status FROM administrator_tab WHERE adm_account = #{account}")
    Integer getAdmStatusByAccount(@Param("account") String account);


    /*
     *
     * 根据id查询姓名
     *
     */
    @Select("SELECT tch_name FROM teacher_tab WHERE tch_id = #{id}")
    String getTchNameByTchId(@Param("id") Integer id);

    @Select("SELECT adm_name FROM administrator_tab WHERE adm_id = #{id}")
    String getAdmNameByAdmid(@Param("id") Integer id);

    @Select("SELECT stu_name FROM student_tab WHERE stu_id = #{id}")
    String getStuNameByStuid(@Param("id") Integer id);


}
