package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.StudentTab;
import essusys.coursemanagementsystem.demos.web.mapper.StudentTabMapper;
import io.swagger.annotations.Api;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@Api(value = "adm/studentManage")
@RequestMapping(value = "/adm/studentManage",
        produces = { "application/json" },
        consumes = { "application/json" })
public class StudentTabController {

    private final StudentTabMapper studentTabMapper;

    public StudentTabController(StudentTabMapper studentTabMapper) {
        this.studentTabMapper = studentTabMapper;
    }

    /*
     *
     * 查询模块
     *
     */
    // 根据账户查询学生信息(唯一)
    // DTO
    @Getter
    @Data
    public static class StudentAccountDTO {
        private String stuAccount;
        public StudentAccountDTO() {}
        public StudentAccountDTO(String stuAccount) {this.stuAccount = stuAccount;}
    }
    @PostMapping(value = "/accountSearch")
    public ResponseEntity<StudentTab> getStudentByAccount(@RequestBody StudentAccountDTO dto) {
        StudentTab student = studentTabMapper.findByAccount(dto.getStuAccount());
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            System.out.println("Student not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 条件查询学生信息,允许缺省
    @PostMapping(value = "/conditionSearch")
    public ResponseEntity<List<StudentTab>> getStudentsByOption(@RequestBody Map<String, Object> params) {
        // System.out.println("Received params: " + params);

        Integer stuGrade = params.get("stuGrade") != null ? Integer.parseInt(params.get("stuGrade").toString()) : null;
        String stuSch = params.get("stuSch") != null ? params.get("stuSch").toString() : null;
        Integer stuStatus = params.get("stuStatus") != null ? Integer.parseInt(params.get("stuStatus").toString()) : null;
        String stuName = params.get("stuName") != null ? params.get("stuName").toString() : null;

        List<StudentTab> students = studentTabMapper.findByOption(stuGrade, stuSch, stuStatus, stuName);
        // System.out.println("Query result: " + students);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /*
     *
     * 增删改模块
     *
     */
    // 增加学生信息
    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody StudentTab studentTab) {
       if (studentTab.getStuName() == null || studentTab.getStuSex() == null
               || studentTab.getStuGrade() == null || studentTab.getStuSch() == null
               || studentTab.getStuAccount() == null || studentTab.getStuPassword() == null) {
           return new ResponseEntity<>("必要信息不能为空",HttpStatus.CREATED);
       }
        if (studentTabMapper.findByAccount(studentTab.getStuAccount())!=null) {
            return new ResponseEntity<>("该账号已存在",HttpStatus.CREATED);
        }
        studentTabMapper.insert(studentTab);
        return new ResponseEntity<>("添加成功",HttpStatus.CREATED);
    }

    // 更新学生信息
    @PutMapping("/update")
    public ResponseEntity<String> updateStudent(@RequestBody StudentTab studentTab) {
        if (studentTab.getStuName() == null || studentTab.getStuSex() == null
                || studentTab.getStuGrade() == null || studentTab.getStuSch() == null
                || studentTab.getStuAccount() == null || studentTab.getStuPassword() == null) {
            return new ResponseEntity<>("必要信息不能为空",HttpStatus.CREATED);
        }
        studentTabMapper.update(studentTab);
        return new ResponseEntity<>("更新成功",HttpStatus.OK);
    }

    // 删除学生信息-未实装
    /*
    @DeleteMapping
    public ResponseEntity<Void> deleteStudent(Integer stuId) {
        studentTabMapper.deleteById(stuId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    */
}