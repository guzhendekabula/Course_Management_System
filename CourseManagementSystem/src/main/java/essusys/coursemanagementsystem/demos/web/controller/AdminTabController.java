package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.AdminTab;
import essusys.coursemanagementsystem.demos.web.mapper.AdminTabMapper;
import io.swagger.annotations.Api;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@Validated
@Api(value = "adm/adminManage")
@RequestMapping(value = "/adm/adminManage",
        produces = { "application/json" },
        consumes = { "application/json" })
public class AdminTabController {

    private final AdminTabMapper adminTabMapper;

    public AdminTabController(AdminTabMapper adminTabMapper) {
        this.adminTabMapper = adminTabMapper;
    }

    // 根据账户查询教务员等级
    @PostMapping
    public ResponseEntity<Integer> getRankByAccount(@RequestParam String admAccount) {
        Integer rank = adminTabMapper.getRankByAccount(admAccount);
        return new ResponseEntity<>(rank, HttpStatus.OK);
    }


    // 根据账户查询教务员信息
    @Getter
    @Data
    public static class AdminAccountDTO {
        private String admAccount;
        public AdminAccountDTO() {}
        public AdminAccountDTO(String admAccount) {this.admAccount = admAccount;}
    }
    @PostMapping(value = "/accountSearch")
    public ResponseEntity<AdminTab> getAdminByAccount(@RequestBody AdminAccountDTO dto) {
        AdminTab admin = adminTabMapper.findByAccount(dto.getAdmAccount());
        if (admin != null) {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 条件查询教务员信息
    @PostMapping(value = "/conditionSearch")
    public ResponseEntity<List<AdminTab>> getAdminsByOption(@RequestBody Map<String, Object> params) {
        String admName = params.get("admName") != null ? params.get("admName").toString() : null;
        Integer admRank = params.get("admRank") != null ? Integer.parseInt(params.get("admRank").toString()) : null;
        Integer admStatus = params.get("admStatus") != null ? Integer.parseInt(params.get("admStatus").toString()) : null;

        List<AdminTab> admins = adminTabMapper.findByOption(admName, admRank, admStatus);
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    // 增加教务员信息
    @PostMapping("/add")
    public ResponseEntity<String> addAdmin(@RequestBody AdminTab adminTab) {
        if(adminTab.getAdmName() == null || adminTab.getAdmRank() == null
                || adminTab.getAdmAccount() == null || adminTab.getAdmPassword() == null )
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.CREATED);
        if (adminTabMapper.findByAccount(adminTab.getAdmAccount())!=null)
            return new ResponseEntity<>("该账号已存在", HttpStatus.CREATED);
        adminTabMapper.insert(adminTab);
        return new ResponseEntity<>("添加成功", HttpStatus.CREATED);
    }

    // 更新教务员信息
    @PutMapping("/update")
    public ResponseEntity<String> updateAdmin(@RequestBody AdminTab adminTab) {
        if(adminTab.getAdmName() == null || adminTab.getAdmRank() == null
                || adminTab.getAdmAccount() == null || adminTab.getAdmPassword() == null )
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.CREATED);
        adminTabMapper.update(adminTab);
        return new ResponseEntity<>("更新成功", HttpStatus.OK);
    }

    // 删除教务员信息-未实装
    /*
    @DeleteMapping
    public ResponseEntity<Void> deleteAdmin(Integer admId) {
        adminTabMapper.deleteById(admId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
     */
}