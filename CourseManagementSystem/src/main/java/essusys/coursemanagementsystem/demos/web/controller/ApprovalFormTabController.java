package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.ApprovalFormTab;
import essusys.coursemanagementsystem.demos.web.entity.CourseTab;
import essusys.coursemanagementsystem.demos.web.mapper.ApprovalFormTabMapper;
import essusys.coursemanagementsystem.demos.web.mapper.CourseTabMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@Validated
@RequestMapping(value = "/adm/approvalForm")
public class ApprovalFormTabController {

    private final ApprovalFormTabMapper approvalFormTabMapper;
    private final CourseTabMapper courseTabMapper;

    public ApprovalFormTabController(ApprovalFormTabMapper approvalFormTabMapper, CourseTabMapper courseTabMapper) {
        this.approvalFormTabMapper = approvalFormTabMapper;
        this.courseTabMapper = courseTabMapper;
    }

    // 根据审批号查询审批表单-未实装
    /*
    @GetMapping("/{apfId}")
    public ResponseEntity<ApprovalFormTab> getApprovalFormById(@PathVariable Integer apfId) {
        ApprovalFormTab approvalForm = approvalFormTabMapper.findById(apfId);
        if (approvalForm != null) {
            return new ResponseEntity<>(approvalForm, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    */

    // 根据状态查询审批表单(输入-1查全部)
    /*
    @GetMapping(value = "/apfStatusSearch", produces = {"application/json"})
    public ResponseEntity<List<ApprovalFormTab>> getApprovalFormsByApfStatus(@RequestParam Integer apfStatus) {
        if (apfStatus==-1)
            return new ResponseEntity<>(approvalFormTabMapper.findAll(), HttpStatus.OK);
        else{
            List<ApprovalFormTab> approvalForms = approvalFormTabMapper.findByStatus(apfStatus);
            // System.out.println("Received apfStatus: " + apfStatus); // 打印参数值
            // System.out.println("Query result: " + approvalForms); // 打印查询结果
            return new ResponseEntity<>(approvalForms, HttpStatus.OK);
        }
    }
    */

    // 根据课程识别码、教师ID、状态条件查询审批表单
    @PostMapping(value = "/conditionSearch")
    public ResponseEntity<List<ApprovalFormTab>> getApprovalFormsByOption(@RequestBody Map<String, Object> params) {
        // 从请求参数中提取并转换数据类型
        String apfCouCode = params.get("apfCouCode") != null ? params.get("apfCouCode").toString() : null;
        Integer apfTchId = params.get("apfTchId") != null ? Integer.parseInt(params.get("apfTchId").toString()) : null;
        Integer apfStatus = params.get("apfStatus") != null ? Integer.parseInt(params.get("apfStatus").toString()) : null;

        List<ApprovalFormTab> approvalForms = approvalFormTabMapper.findByOption(apfCouCode, apfTchId, apfStatus);
        return new ResponseEntity<>(approvalForms, HttpStatus.OK);
    }

    // 根据课程识别码查询审批表单(需要查询参数couCode)
    /*
    @GetMapping(value = "/couCodeSearch", produces = {"application/json"})
    public ResponseEntity<ApprovalFormTab> getApprovalFormsByCourseCode(@RequestParam String apfCouCode) {
        ApprovalFormTab approvalForms = approvalFormTabMapper.findByCourseCode(apfCouCode);
        return new ResponseEntity<>(approvalForms, HttpStatus.OK);
    }
    */

    // 通过开课审批表
    @PutMapping(value = "/passAdd", produces = {"application/json" }, consumes = {"application/json" })
    public ResponseEntity<String> passAddApprovalForm(@RequestBody ApprovalFormTab approvalFormTab) {
        if (approvalFormTab.getApfId() == null || approvalFormTab.getApfTchId() == null ||
                approvalFormTab.getApfCouCode() == null || approvalFormTab.getApfStatus() == null) {
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.BAD_REQUEST);
        }

        // 将审批通过的课程状态变为3
        courseTabMapper.updateStatusTo3(approvalFormTab.getApfCouCode());
        // 更新审批表单
        if(approvalFormTab.getApfStatus() != 1)
            approvalFormTab.setApfStatus(1);
        approvalFormTabMapper.update(approvalFormTab);
        return new ResponseEntity<>("审批成功", HttpStatus.OK);
    }

    // 通过修改课程的审批表
    /*
        为了实现在审批阶段不更改课程的数据，需要在数据库先插入一个记录新数据的临时课程，
            审批通过后将原课程的数据替换为临时课程的数据，删除临时课程。
        临时课程的主键固定为为原课程码加上后缀“-TEMP”,同一时间只能就同一课程提交一次修改课程的审批,
            直至它被通过或手动删除为止(驳回无法自动删除临时表)。
        临时课程的课程状态只能为4或5,也就是说它无论如何不应该出现在选课或评分界面。
        注意要保存原课程的状态，修改的操作不应该改变状态。
    */
    @PutMapping(value = "/passChange", produces = {"application/json" }, consumes = {"application/json" })
    public ResponseEntity<String> passChangeApprovalForm(@RequestBody ApprovalFormTab approvalFormTab) {
        if (approvalFormTab.getApfId() == null || approvalFormTab.getApfTchId() == null ||
                approvalFormTab.getApfCouCode() == null || approvalFormTab.getApfStatus() == null) {
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.BAD_REQUEST);
        }
        // 处理后缀
        String code = approvalFormTab.getApfCouCode();
        String originCode = code;
        if (code.endsWith("-TEMP")) {
            originCode = code.substring(0, code.length() - 5); // 删除最后 5 个字符
        }
        else
            return new ResponseEntity<>("临时课程的课程码格式不正确",HttpStatus.NOT_FOUND);
        System.out.println(originCode);
        System.out.println(code);
        // 查询临时表数据并保存(注意这一步一定要使用深拷贝)
        CourseTab tempCourseTab = courseTabMapper.findByCode(code);
        CourseTab newCourseTab  = new CourseTab(tempCourseTab);
        // 将保存数据的课程码和状态改回为原课程的课程码和状态
        newCourseTab.setCouCode(originCode);
        newCourseTab.setCouStatus(courseTabMapper.getStatusByCode(originCode));
        System.out.println(newCourseTab);
        // 更新原课程
        courseTabMapper.update(newCourseTab);
        // 将审批表的课程码改回为原课程码,否则外键约束无法删除临时课程
        approvalFormTab.setApfCouCode(originCode);
        System.out.println(approvalFormTab);
        // 更新审批表单
        if(approvalFormTab.getApfStatus() != 1)
            approvalFormTab.setApfStatus(1);
        approvalFormTabMapper.update(approvalFormTab);
        // 删除临时课程
        courseTabMapper.deleteByCode(code);
        return new ResponseEntity<>("审批成功", HttpStatus.OK);
    }

    // 审批驳回
    @PutMapping(value = "/reject", produces = {"application/json" }, consumes = {"application/json" })
    public ResponseEntity<String> rejectApprovalForm(@RequestBody ApprovalFormTab approvalFormTab) {
        if (approvalFormTab.getApfId() == null || approvalFormTab.getApfTchId() == null ||
                approvalFormTab.getApfCouCode() == null || approvalFormTab.getApfStatus() == null) {
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.BAD_REQUEST);
        }
        if (approvalFormTab.getApfNote() == null || Objects.equals(approvalFormTab.getApfNote(), ""))
            return new ResponseEntity<>("驳回必须填写审批意见", HttpStatus.BAD_REQUEST);
        // 如果前端没有将状态值设置正确，后端将自动纠正
        if(approvalFormTab.getApfStatus() != 2)
            approvalFormTab.setApfStatus(2);

        // 将审批驳回的课程状态变为5
        courseTabMapper.updateStatusTo5(approvalFormTab.getApfCouCode());
        // 更新审批表单
        approvalFormTabMapper.update(approvalFormTab);
        return new ResponseEntity<>("驳回成功", HttpStatus.OK);
    }

    // 删除审批表单
    @DeleteMapping(value = "/delete", produces = {"application/json" }, consumes = {"application/json" })
    public ResponseEntity<Void> deleteApprovalForm(@RequestBody Integer apfId) {
        approvalFormTabMapper.deleteById(apfId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}