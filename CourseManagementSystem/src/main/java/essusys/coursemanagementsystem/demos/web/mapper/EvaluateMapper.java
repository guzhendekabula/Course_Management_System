package essusys.coursemanagementsystem.demos.web.mapper;

import essusys.coursemanagementsystem.demos.web.entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

@Mapper
public interface EvaluateMapper {
    //查看评教
    public class EvaluateSqlProvider {
        public String findByOption(Map<String, Object> params) {
            SQL sql = new SQL();
            sql.SELECT("distinct e.eva_cla_id,co.cou_rank,co.cou_type,co.cou_name,co.cou_school,e.eva_tch_id,e.eva_stu_id,e.eva_score,e.eva_item_1,t.tch_name,e.eva_item_2,e.eva_item_3,e.eva_item_4,e.eva_text" )

                    .FROM("class_tab c, course_tab co, teacher_tab t,evaluation_tab e")
                    .WHERE("e.eva_tch_id = #{evaTchId} and e.eva_tch_id=t.tch_id and e.eva_tch_id=c.cla_tch_id and c.cla_cou_code=co.cou_code");


            String finalSql = sql.toString();
            System.out.println("Generated SQL: " + finalSql);
            return finalSql;
        }
    }

//查询评教
    @SelectProvider(type = EvaluateMapper.EvaluateSqlProvider.class, method = "findByOption")
    @ResultMap("EvaluationSTabResultMap")  // 引用XML中定义的结果映射
    List<EvaluationSTab> findByOption(@Param("evaTchId") Integer evaTchId);



//评教

    @Insert("INSERT INTO evaluation_tab (eva_tch_id, eva_stu_id, eva_cla_id, eva_score, eva_text, eva_item_1, eva_item_2, eva_item_3, eva_item_4)" +
            "VALUES (#{evaTchId}, #{evaStuId}, #{evaClaId}, #{evaScore}, #{evaText}, #{evaItem1}, #{evaItem2}, #{evaItem3}, #{evaItem4})")
    void insert(EvaluationTab evaluationTab);

}
