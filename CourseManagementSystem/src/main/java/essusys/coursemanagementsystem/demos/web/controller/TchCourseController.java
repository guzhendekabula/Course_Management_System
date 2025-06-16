package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.ApprovalFormTab;
import essusys.coursemanagementsystem.demos.web.entity.ClassTab;
import essusys.coursemanagementsystem.demos.web.entity.CourseTab;
import essusys.coursemanagementsystem.demos.web.mapper.ApprovalFormTabMapper;
import essusys.coursemanagementsystem.demos.web.mapper.ClassTabMapper;
import essusys.coursemanagementsystem.demos.web.mapper.CourseTabMapper;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@Validated
@RequestMapping(value = "/tch/approvalForm",
        produces = {"application/json" },
        consumes = { "application/json" })
public class TchCourseController {
    private final ClassTabMapper classTabMapper;
    private final CourseTabMapper courseTabMapper;
    private final ApprovalFormTabMapper approvalFormTabMapper;

    public TchCourseController(ApprovalFormTabController approvalFormTabController, CourseTabController courseTabController, ClassTabMapper classTabMapper, CourseTabMapper courseTabMapper, ApprovalFormTabMapper approvalFormTabMapper) {
        this.classTabMapper = classTabMapper;
        this.courseTabMapper = courseTabMapper;
        this.approvalFormTabMapper = approvalFormTabMapper;
    }

    public static class TchIdDTO {
        private Integer tchId;
        public TchIdDTO() {}
        public TchIdDTO(Integer tchId) {this.tchId = tchId;}
        public Integer getTchId() {
            return tchId;
        }
    }
    // 根据教师职工号查询教学班号
    @PostMapping("/tchIdClassSearch")
    public ResponseEntity< List<ClassTab> > getClassByTchId(@RequestBody TchIdDTO tchIdDTO ){
        if (tchIdDTO.getTchId() == null )
            // System.out.println("空请求体");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {
            List<ClassTab> classTabs = classTabMapper.getClassByTchId(tchIdDTO.getTchId());
            if (classTabs == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else return new ResponseEntity<>(classTabs,HttpStatus.OK);
        }
    }

    // 根据教师职工号查询审批表
    @PostMapping(value = "/tchIdSearch")
    public ResponseEntity<List<ApprovalFormTab>> getApprovalFormsByTeacherId(@RequestBody TchIdDTO tchIdDTO) {
        if (tchIdDTO.getTchId() == null )
            // System.out.println("空请求体");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {
            List<ApprovalFormTab> approvalForms = approvalFormTabMapper.findByTeacherId(tchIdDTO.getTchId());
            return new ResponseEntity<>(approvalForms, HttpStatus.OK);
        }
    }

    @Getter
    public static class ApfCouCodeDTO {
        private String apfCouCode;
        public ApfCouCodeDTO() {}
        public ApfCouCodeDTO(String apfCouCode) {this.apfCouCode = apfCouCode;}
    }
    // 根据课程识别码查询审批表单(需要查询参数couCode)
    @PostMapping(value = "/couCodeSearch")
    public ResponseEntity<ApprovalFormTab> getApprovalFormsByCourseCode(@RequestBody ApfCouCodeDTO apfCouCodeDTO) {
        String apfCouCode = apfCouCodeDTO.getApfCouCode();
        ApprovalFormTab approvalForms = approvalFormTabMapper.findByCourseCode(apfCouCode);
        return new ResponseEntity<>(approvalForms, HttpStatus.OK);
    }

    // 根据教师职工号查询课程
    @PostMapping("/tchIdCourseSearch")
    public ResponseEntity< List<CourseTab> > getCourseByTchId(@RequestBody TchIdDTO tchIdDTO ){
        if (tchIdDTO.getTchId() == null)
            // System.out.println("空请求体");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else{
            // 查课程码List
            List<String> codes = classTabMapper.getClassCouCodeByTchId(tchIdDTO.getTchId());
            if (codes == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            // 去重
            List<String> newCodes = codes.stream().distinct().toList();
            // System.out.println(codes);
            // System.out.println(newCodes);
            List<CourseTab> courseTabs = new ArrayList<>();
            // 遍历课程码List逐个查询并加入返回List
            for (String i : newCodes){
                CourseTab courseTab = courseTabMapper.findByCode(i);
                if(courseTab == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                else courseTabs.add(courseTab);
            }
            return new ResponseEntity<>(courseTabs,HttpStatus.OK);
        }
    }

    @Getter
    public static class ApprovalDTO {
        private ApprovalFormTab  approvalFormTab;
        private CourseTab courseTab;
        public ApprovalDTO() {}
        public ApprovalDTO(ApprovalFormTab approvalFormTab, CourseTab courseTab) {
            this.approvalFormTab = approvalFormTab;
            this.courseTab = courseTab;
        }
    }
    // 提交开课审批(新增审批表单的同时新增课程)
    @PostMapping("/add")
    public ResponseEntity<String> addApprovalForm(@RequestBody ApprovalDTO approvalDTO) {
        ApprovalFormTab approvalFormTab =  approvalDTO.getApprovalFormTab();
        CourseTab courseTab = approvalDTO.getCourseTab();
        if (approvalFormTab == null || courseTab == null)
            return new ResponseEntity<>("请求体错误",HttpStatus.BAD_REQUEST);
        if (courseTabMapper.findByCode(courseTab.getCouCode())!=null)
            return new ResponseEntity<>("已经存在相同课程码",HttpStatus.BAD_REQUEST);
        if( courseTab.getCouCode() == null || courseTab.getCouName() == null || courseTab.getCouTerm() == null ||
                courseTab.getCouType() == null || courseTab.getCouStatus() == null || courseTab.getCouScore() == null ||
                courseTab.getCouRank() == null){
            // System.out.println(courseTab.getCouCode());
            return new ResponseEntity<>("必要信息不能为空",HttpStatus.BAD_REQUEST);
        }

        if (approvalFormTab.getApfTchId() == null || approvalFormTab.getApfCouCode() == null || approvalFormTab.getApfStatus() == null) {
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.BAD_REQUEST);
        }
        if(!Objects.equals(approvalFormTab.getApfCouCode(), courseTab.getCouCode()))
            return new ResponseEntity<>("课程码不一致",HttpStatus.BAD_REQUEST);

        // 确定为提交状态
        approvalFormTab.setApfStatus(0);
        courseTab.setCouStatus(4);

        courseTabMapper.insert(courseTab);
        approvalFormTabMapper.insert(approvalFormTab);
        return new ResponseEntity<>("添加成功", HttpStatus.CREATED);
    }

    // 对被驳回(状态为2)的审批表修改后重新提交审批
    @PutMapping("/rejectRedo")
    public ResponseEntity<String> rejectApprovalForm(@RequestBody ApprovalDTO approvalDTO) {
        ApprovalFormTab approvalFormTab =  approvalDTO.getApprovalFormTab();
        CourseTab courseTab = approvalDTO.getCourseTab();
        if (approvalFormTab == null || courseTab == null)
            return new ResponseEntity<>("请求体错误",HttpStatus.BAD_REQUEST);
        if( courseTab.getCouCode() == null || courseTab.getCouName() == null || courseTab.getCouTerm() == null ||
                courseTab.getCouType() == null || courseTab.getCouStatus() == null || courseTab.getCouScore() == null ||
                courseTab.getCouRank() == null)
            return new ResponseEntity<>("必要信息不能为空",HttpStatus.BAD_REQUEST);
        if (approvalFormTab.getApfTchId() == null || approvalFormTab.getApfCouCode() == null || approvalFormTab.getApfStatus() == null) {
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.BAD_REQUEST);
        }
        if(!Objects.equals(approvalFormTab.getApfCouCode(), courseTab.getCouCode()))
            return new ResponseEntity<>("课程码不一致",HttpStatus.BAD_REQUEST);

        // 重新转为提交状态
        approvalFormTab.setApfStatus(0);
        courseTab.setCouStatus(4);

        // 更新课程
        courseTabMapper.update(courseTab);
        // 更新审批表
        approvalFormTabMapper.update(approvalFormTab);
        return new ResponseEntity<>("添加成功", HttpStatus.CREATED);
    }

    // 提交修改审批(新增审批表单的同时新增临时课程)
    /*
        为了实现在审批阶段不更改课程的数据，需要在数据库先插入一个记录新数据的临时课程，
            审批通过后将原课程的数据替换为临时课程的数据，删除临时课程。
        临时课程的主键固定为为原课程码加上后缀“-TEMP”,同一时间只能就同一课程提交一次修改课程的审批,
            直至它被通过或手动删除为止(驳回无法自动删除临时表)。
        临时课程的课程状态只能为4或5,也就是说它无论如何不应该出现在选课或评分界面。
    */
    @PostMapping("/change")
    public ResponseEntity<String> changeApprovalForm(@RequestBody ApprovalDTO approvalDTO) {
        ApprovalFormTab approvalFormTab =  approvalDTO.getApprovalFormTab();
        CourseTab courseTab = approvalDTO.getCourseTab();
        if (approvalFormTab == null || courseTab == null)
            return new ResponseEntity<>("请求体错误",HttpStatus.BAD_REQUEST);
        if( courseTab.getCouCode() ==null || courseTab.getCouName() == null || courseTab.getCouTerm() == null ||
                courseTab.getCouType() == null || courseTab.getCouStatus() == null || courseTab.getCouScore() == null ||
                courseTab.getCouRank() == null)
            return new ResponseEntity<>("必要信息不能为空",HttpStatus.BAD_REQUEST);
        if (approvalFormTab.getApfTchId() == null || approvalFormTab.getApfCouCode() == null || approvalFormTab.getApfStatus() == null) {
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.BAD_REQUEST);
        }
        if(!Objects.equals(approvalFormTab.getApfCouCode(), courseTab.getCouCode()))
            return new ResponseEntity<>("课程码不一致",HttpStatus.BAD_REQUEST);

        // 添加后缀，审批表也需要加
        courseTab.setCouCode(courseTab.getCouCode() + "-TEMP");
        approvalFormTab.setApfCouCode(courseTab.getCouCode());

        courseTabMapper.insert(courseTab);
        approvalFormTabMapper.insert(approvalFormTab);
        return new ResponseEntity<>("添加成功", HttpStatus.CREATED);
    }

}
