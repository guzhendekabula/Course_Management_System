package essusys.coursemanagementsystem.demos.web.mapper;

import essusys.coursemanagementsystem.demos.web.entity.AdminTab;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public interface AdminTabMapper {

    // 条件查询教务员信息
    @SelectProvider(type = AdminTabSqlProvider.class, method = "findByOption")
    @ResultMap("AdminTabResultMap")
    List<AdminTab> findByOption(@Param("admName") String admName,
                                @Param("admRank") Integer admRank,
                                @Param("admStatus") Integer admStatus);

    // 根据账户查询教务员信息
    @Select("SELECT * FROM administrator_tab WHERE adm_account = #{admAccount}")
    @ResultMap("AdminTabResultMap")
    AdminTab findByAccount(@Param("admAccount") String admAccount);

    // 根据账号查询教务员等级
    @Select("SELECT adm_rank FROM administrator_tab WHERE adm_account = #{admAccount}")
    Integer getRankByAccount(@Param("admAccount") String admAccount);

    // 插入教务员信息
    @Insert("INSERT INTO administrator_tab (adm_id, adm_name, adm_rank, adm_tel, adm_account, adm_password, adm_status) " +
            "VALUES (#{admId}, #{admName}, #{admRank}, #{admTel}, #{admAccount}, #{admPassword}, #{admStatus})")
    @Options(useGeneratedKeys = true, keyProperty = "admId")
    void insert(AdminTab adminTab);

    // 更新教务员信息
    @Update("UPDATE administrator_tab SET adm_name = #{admName}, adm_rank = #{admRank}, adm_tel = #{admTel}, " +
            "adm_account = #{admAccount}, adm_status = #{admStatus} WHERE adm_id = #{admId}")
    void update(AdminTab adminTab);

    // 根据账户修改密码
    @Update("UPDATE administrator_tab SET adm_password = #{admPassword} WHERE adm_account = #{admAccount}")
    void updatePassword(@Param("admAccount") String admAccount, @Param("admPassword") String admPassword);

    // 删除教务员信息
    @Delete("DELETE FROM administrator_tab WHERE adm_id = #{admId}")
    void deleteById(@Param("admId") Integer admId);

    // SQL 提供类
    class AdminTabSqlProvider {
        public String findByOption(Map<String, Object> params) {
            return new SQL() {{
                SELECT("*");
                FROM("administrator_tab");
                WHERE("1=1");
                if (params.get("admName") != null && !params.get("admName").toString().isEmpty()) {
                    AND().WHERE("adm_name LIKE CONCAT('%', #{admName}, '%')");
                }
                if (params.get("admRank") != null) {
                    AND().WHERE("adm_rank = #{admRank}");
                }
                if (params.get("admStatus") != null) {
                    AND().WHERE("adm_status = #{admStatus}");
                }
            }}.toString();
        }
    }
}