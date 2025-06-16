package essusys.coursemanagementsystem.demos.web.mapper;

import essusys.coursemanagementsystem.demos.web.entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

@Mapper
public interface SeDeClassMapper {

    //查询可选课程
    public class StudentClassSqlProvider {
        public String findByOption(Map<String, Object> params) {
            SQL sql = new SQL();
            sql.SELECT("distinct c.cla_id,s.sco_stu_id,t.tch_name, c.cla_timeset,c.cla_weekset,c.cla_classroom,co.cou_term,co.cou_rank,co.cou_score,co.cou_name,co.cou_ifexam ")
                    .FROM("score_tab s,class_tab c, course_tab co, teacher_tab t,student_tab st")
                    .WHERE("s.sco_stu_id = #{scoStuId} and co.cou_status=1 and s.sco_stu_id=st.stu_id and (st.stu_sch=c.cla_toschool_1 or st.stu_sch=c.cla_toschool_2 or st.stu_sch=c.cla_toschool_3 or st.stu_sch=c.cla_toschool_4) and c.cla_tch_id=t.tch_id and c.cla_cou_code=co.cou_code");

//            if (params.get("scoStuId") != null) {
//                sql.AND().WHERE("score_tab.sco_stu_id = #{scoStuId}");
//            }
//            if (params.get("couTerm") != null ) {
//                sql.AND().WHERE("course_tab.cou_term = #{couTerm}");
//            }
//            if (params.get("couRank") != null) {
//                sql.AND().WHERE("course_tab.cou_rank = #{couRank}");
//                sql.AND().WHERE("score_tab.sco_cla_id=class_tab.cla_id and class_tab.cla_cou_code=course_tab.cou_code and teacher_tab.tch_id=class_tab.cla_tch_id");
//            }



            String finalSql = sql.toString();
            System.out.println("Generated SQL: " + finalSql);
            return finalSql;
        }
    }


    //查询已选课程
    public class StudentGradeSqlProvider2 {
        public String findByOption2(Map<String, Object> params) {
            SQL sql = new SQL();
            sql.SELECT("s.sco_stu_id,s.sco_cla_id ,c.cla_tch_id,t.tch_name, c.cla_timeset,c.cla_weekset,c.cla_classroom,co.cou_rank,co.cou_score,co.cou_name")
                    .FROM("score_tab s,class_tab c, course_tab co, teacher_tab t")
                    .WHERE("s.sco_stu_id = #{scoStuId} and c.cla_cou_code = co.cou_code and s.sco_cla_id = c.cla_id and t.tch_id=c.cla_tch_id");

//            if (params.get("claTchId") != null) {
//                sql.AND().WHERE("c.cla_tch_id = #{claTchId}");
//            }

            String finalSql = sql.toString();
            System.out.println("Generated SQL: " + finalSql);
            return finalSql;
        }
    }

    /*
     *
     * 查询模块
     *
     */


    // 根据学生id查询可选课程信息

    @SelectProvider(type = SeDeClassMapper.StudentClassSqlProvider.class, method = "findByOption")
    @ResultMap("SDClassTabResultMap")  // 引用XML中定义的结果映射
    List<SDClassTab> findByOption(@Param("scoStuId") Integer scoStuId);


    //根据学生id查询已选课程信息
    @SelectProvider(type = SeDeClassMapper.StudentGradeSqlProvider2.class, method = "findByOption2")
    @ResultMap("SD2ClassTabResultMap")   // 引用XML中定义的结果映射
    List<SD2ClassTab> findByOption2(@Param("scoStuId") Integer scoStuId);




    /*
     *
     * 增删改模块
     *
     */
    @Insert("INSERT INTO score_tab (sco_stu_id, sco_cla_id) " +
            "VALUES (#{scoStuId}, #{scoClaId})")
    //@Options(useGeneratedKeys = true, keyProperty = "scoStuId")    // 让系统自己生成自增长的主键
    void insert(ScoreTab scoreTab);


    @Delete("DELETE FROM score_tab WHERE sco_stu_id = #{scoStuId} and sco_cla_id = #{scoClaId} ")
    void deleteById(ScoreTab scoreTab);


}
