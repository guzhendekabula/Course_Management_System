package essusys.coursemanagementsystem.demos.web.mapper;

import essusys.coursemanagementsystem.demos.web.entity.ApprovalFormTab;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApprovalFormTabMapper {
    // 实现条件选择查询和模糊查询的动态SQL语句
    public class ApprovalFormTabSqlProvider {
        public String findByOption(Map<String, Object> params) {
            SQL sql = new SQL();
            sql.SELECT("*")
                    .FROM("approvalform_tab")
                    .WHERE("1=1");

            if (params.get("apfCouCode") != null && !params.get("apfCouCode").toString().isEmpty()) {
                sql.AND().WHERE("apf_cou_code = #{apfCouCode}");
            }
            if (params.get("apfTchId") != null) {
                sql.AND().WHERE("apf_tch_id = #{apfTchId}");
            }
            if (params.get("apfStatus") != null) {
                sql.AND().WHERE("apf_status = #{apfStatus}");
            }
            return sql.toString();
        }
    }
    // 根据课程识别码、教师ID、状态条件查询审批表单
    @SelectProvider(type = ApprovalFormTabSqlProvider.class, method = "findByOption")
    @ResultMap("ApprovalFormTabResultMap")  // 引用XML中定义的结果映射
    List<ApprovalFormTab> findByOption(@Param("apfCouCode") String apfCouCode,
                                       @Param("apfTchId") Integer apfTchId,
                                       @Param("apfStatus") Integer apfStatus);


    // 查询全部审批表
    @Select("SELECT * FROM approvalform_tab")
    @ResultMap("ApprovalFormTabResultMap")
    List<ApprovalFormTab> findAll();



    // 根据状态查询审批表
    @Select("SELECT * FROM approvalform_tab WHERE apf_status = #{apfStatus}")
    @ResultMap("ApprovalFormTabResultMap")
    List<ApprovalFormTab> findByStatus(@Param("apfStatus") Integer apfStatus);

    // 根据审批号查询审批表单
    @Select("SELECT * FROM approvalform_tab WHERE apf_id = #{apfId}")
    @ResultMap("ApprovalFormTabResultMap")
    ApprovalFormTab findById(@Param("apfId") Integer apfId);

    // 根据教务员ID查询审批表单
    @Select("SELECT * FROM approvalform_tab WHERE apf_adm_id = #{apfAdmId}")
    @ResultMap("ApprovalFormTabResultMap")
    List<ApprovalFormTab> findByAdminId(@Param("apfAdmId") Integer apfAdmId);

    // 根据教师ID查询审批表单
    @Select("SELECT * FROM approvalform_tab WHERE apf_tch_id = #{apfTchId}")
    @ResultMap("ApprovalFormTabResultMap")
    List<ApprovalFormTab> findByTeacherId(@Param("apfTchId") Integer apfTchId);

    // 根据课程识别码查询审批表单
    @Select("SELECT * FROM approvalform_tab WHERE apf_cou_code = #{apfCouCode}")
    @ResultMap("ApprovalFormTabResultMap")
    ApprovalFormTab findByCourseCode(@Param("apfCouCode") String apfCouCode);

    // 插入审批表单
    @Insert("INSERT INTO approvalform_tab (apf_id, apf_adm_id, apf_tch_id, apf_cou_code, apf_status, apf_note) " +
            "VALUES (#{apfId}, #{apfAdmId}, #{apfTchId}, #{apfCouCode}, #{apfStatus}, #{apfNote})")
    @Options(useGeneratedKeys = true, keyProperty = "apfId")
    void insert(ApprovalFormTab approvalFormTab);

    // 更新审批表单
    @Update("UPDATE approvalform_tab SET apf_tch_id = #{apfTchId}, apf_cou_code = #{apfCouCode}, " +
            "apf_status = #{apfStatus}, apf_note = #{apfNote} WHERE apf_id = #{apfId}")
    void update(ApprovalFormTab approvalFormTab);

    // 删除审批表单
    @Delete("DELETE FROM approvalform_tab WHERE apf_id = #{apfId}")
    void deleteById(@Param("apfId") Integer apfId);
}