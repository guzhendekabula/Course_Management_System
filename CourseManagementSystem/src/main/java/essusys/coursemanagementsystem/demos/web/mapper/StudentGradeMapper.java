package essusys.coursemanagementsystem.demos.web.mapper;

import essusys.coursemanagementsystem.demos.web.entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

import java.util.List;
import java.util.Map;
@Mapper
public interface StudentGradeMapper {

    // 实现条件选择查询和模糊查询的动态SQL语句(Claud-3.5生成)
    public class StudentGradeSqlProvider {
        public String findByOption(Map<String, Object> params) {
            SQL sql = new SQL();
            sql.SELECT("course_tab.cou_name ,teacher_tab.tch_name,class_tab.cla_tch_id,course_tab.cou_term,course_tab.cou_rank,course_tab.cou_score,score_tab.sco_score_1, score_tab.sco_score_2 ")
                    .FROM("score_tab ,class_tab, course_tab, teacher_tab")
                    .WHERE("1=1");

            if (params.get("scoStuId") != null) {
                sql.AND().WHERE("score_tab.sco_stu_id = #{scoStuId}");
            }
            if (params.get("couTerm") != null ) {
                sql.AND().WHERE("course_tab.cou_term = #{couTerm}");
            }
            if (params.get("couRank") != null) {
                sql.AND().WHERE("course_tab.cou_rank = #{couRank}");
                sql.AND().WHERE("score_tab.sco_cla_id=class_tab.cla_id and class_tab.cla_cou_code=course_tab.cou_code and teacher_tab.tch_id=class_tab.cla_tch_id");
//                sql.GROUP_BY("score_tab.sco_stu_id," +
//                        "    teacher_tab.tch_name," +
//                        "    class_tab.cla_tch_id," +
//                        "    course_tab.cou_term," +
//                        "    course_tab.cou_rank," +
//                        "    course_tab.cou_score," +
//                        "    score_tab.sco_score_1," +
//                        "    score_tab.sco_score_2");
            }
            //   if((params.get("scoStuId") != null) && (params.get("couTerm") != null) && (params.get("couRank") != null)){

            //  }


            String finalSql = sql.toString();
            System.out.println("Generated SQL: " + finalSql);
            return finalSql;
        }
    }


//        @Select("SUM(course_tab.cou_score) AS total_credits, ((course_tab.cou_score * score_tab.sco_score_2) /SUM(course_tab.cou_score) ) AS avg_gpa from score_tab ,class_tab, course_tab, teacher_tab")
//        Double getTotalCredits(@Param("someCondition") String someCondition);



    public class StudentGradeSqlProvider2 {
        public String findByOption2(Map<String, Object> params) {
            SQL sql = new SQL();
            sql.SELECT("c.cla_id, co.cou_name,s.sco_stu_id")
                    .FROM("class_tab c, course_tab co, score_tab s")
                    .WHERE("c.cla_cou_code = co.cou_code and s.sco_cla_id = c.cla_id");

            if (params.get("claTchId") != null) {
                sql.AND().WHERE("c.cla_tch_id = #{claTchId}");
            }

            String finalSql = sql.toString();
            System.out.println("Generated SQL: " + finalSql);
            return finalSql;
        }
    }

    public class StudentGradeSqlProvider3 {
        public String findByOption3(Map<String, Object> params) {
            SQL sql = new SQL();
            sql.SELECT("sco_stu_id,sco_cla_id,sco_score_1,sco_score_2,sco_item_1,sco_item_2,sco_item_3,sco_item_4,sco_note")
                    .FROM(" score_tab ")
                    .WHERE("sco_cla_id = #{scoClaId}");


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


    // 根据条件查询学生成绩,支持姓名模糊查询

    @SelectProvider(type = StudentGradeSqlProvider.class, method = "findByOption")
    @ResultMap("ScClCouTabResultMap")  // 引用XML中定义的结果映射
    List<ScClCouTab> findByOption(@Param("scoStuId") Integer scoStuId,
                                  @Param("couTerm") Integer couTerm,
                                  @Param("couRank") String couRank);

    //根据教师id查询教学班id
    @SelectProvider(type = StudentGradeSqlProvider2.class, method = "findByOption2")
    @ResultMap("ClaCouTabResultMap")  // 引用XML中定义的结果映射
    List<ClaCouTab> findByOption2(@Param("claTchId") Integer claTchId);

    //根据教学班id查询成绩
    @SelectProvider(type = StudentGradeSqlProvider3.class, method = "findByOption3")
    @ResultMap("ScoreTabResultMap")  // 引用XML中定义的结果映射
    List<ScoreTab> findByOption3(@Param("scoClaId") Integer scoClaId);



    //根据教师号查询教学班(唯一)
    // @ResultMap("StudentTabResultMap")
    @Select("SELECT cla_id,cla_cou_code FROM class_tab WHERE cla_tch_id = #{claTchId}")
    ClassTab findByTchId(@Param("claTchId") Integer claTchId);

    //根据课程号查询课程名
    @Select("SELECT cou_name FROM course_tab WHERE cou_code = #{couCode}")
    ClassTab findByCou(@Param("couCode") String couCode);

    /*
     *
     * 增删改模块
     *
     */
//    @Insert("INSERT INTO score_tab (sco_stu_id, sco_cla_id, sco_score_1, sco_item_1, sco_item_2, sco_item_3, sco_item_4, sco_note, sco_score_2) " +
//            "VALUES (#{scoStuId}, #{scoClaId}, #{scoScore1}, #{scoItem1}, #{scoItem2}, #{scoItem3}, #{scoItem4}, #{scoNote}, #{scoScore2})")
//    //@Options(useGeneratedKeys = true, keyProperty = "scoStuId")    // 让系统自己生成自增长的主键
//    void insert(ScoreTab scoreTab);

    @Update("UPDATE score_tab SET  sco_score_1 = #{scoScore1}, sco_item_1 = #{scoItem1}, sco_item_2 = #{scoItem2}, " +
            "sco_item_3 = #{scoItem3}, sco_item_4 = #{scoItem4}, sco_note = #{scoNote} ,sco_score_2 = #{scoScore2} WHERE sco_stu_id = #{scoStuId} and sco_cla_id = #{scoClaId}")
    void update(ScoreTab scoreTab);

    @Delete("DELETE FROM score_tab WHERE sco_stu_id = #{scoStuId} and sco_cla_id = #{scoClaId} ")
    void deleteById(@Param("scoStuId") Integer scoStuId, @Param("scoClaId") Integer scoClaId);
}
