package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.AdminTab;
import essusys.coursemanagementsystem.demos.web.entity.StudentTab;
import essusys.coursemanagementsystem.demos.web.entity.TeacherTab;
import essusys.coursemanagementsystem.demos.web.mapper.AdminTabMapper;
import essusys.coursemanagementsystem.demos.web.mapper.StudentTabMapper;
import essusys.coursemanagementsystem.demos.web.mapper.TeacherTabMapper;
import io.swagger.annotations.Api;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@Api(value = "/personalPage")
@RequestMapping(value = "/personalPage",
        produces = { "application/json" })
public class PersonalpageController {
    private final StudentTabMapper studentTabMapper;
    private final AdminTabMapper adminTabMapper;
    private final TeacherTabMapper teacherTabMapper;

    public PersonalpageController(StudentTabMapper studentTabMapper, AdminTabMapper adminTabMapper, TeacherTabMapper teacherTabMapper) {
        this.studentTabMapper = studentTabMapper;
        this.adminTabMapper = adminTabMapper;
        this.teacherTabMapper = teacherTabMapper;
    }

    @GetMapping("/stu/{account}")
    public ResponseEntity<StudentTab> getStudentByAccount(@PathVariable String account) {
        // 调用 Mapper 查询学生信息
        StudentTab student = studentTabMapper.findByAccount(account);

        // 判断查询结果
        if (student != null) {
            // 如果找到学生信息，返回 200 OK 和查询结果
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            // 如果未找到学生信息，返回 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tch/{account}")
    public ResponseEntity<TeacherTab> getTeacherByAccount(@PathVariable String account) {
        // 调用 Mapper 查询教师信息
        TeacherTab teacher = teacherTabMapper.findByAccount(account);

        // 判断查询结果
        if (teacher != null) {
            // 如果找到教师信息，返回 200 OK 和查询结果
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            // 如果未找到教师信息，返回 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/adm/{account}")
    public ResponseEntity<AdminTab> getAdminByAccount(@PathVariable String account) {
        // 调用 Mapper 查询学生信息
        AdminTab admin = adminTabMapper.findByAccount(account);

        // 判断查询结果
        if (admin != null) {
            // 如果找到学生信息，返回 200 OK 和查询结果
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            // 如果未找到学生信息，返回 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Getter
    public static class changePasswordDTO{
        private String oldPassword;
        private String newPassword;
        private String account;
        public changePasswordDTO() {}
        public changePasswordDTO(String oldPassword, String newPassword, String account) {
            this.oldPassword = oldPassword;
            this.newPassword = newPassword;
            this.account = account;
        }
    }

    // 确定输入的原密码和新密码修改密码
    @PutMapping("/stu/{account}/changePassword")
    public ResponseEntity<String> updateStudentPassword(@RequestBody changePasswordDTO dto) {
        StudentTab student = studentTabMapper.findByAccount(dto.getAccount());
        if (student == null) {
            return new ResponseEntity<>("用户不存在", HttpStatus.BAD_REQUEST);
        }
        if (student.getStuPassword().equals(dto.getOldPassword())) {
            studentTabMapper.updatePassword(dto.getAccount(), dto.getNewPassword());
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("原密码错误", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/tch/{account}/changePassword")
    public ResponseEntity<String> updateTeacherPassword(@RequestBody changePasswordDTO dto) {
        TeacherTab teacher = teacherTabMapper.findByAccount(dto.getAccount());
        if (teacher == null) {
            return new ResponseEntity<>("用户不存在", HttpStatus.BAD_REQUEST);
        }
        if (teacher.getTchPassword().equals(dto.getOldPassword())) {
            teacherTabMapper.updatePassword(dto.getAccount(), dto.getNewPassword());
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("原密码错误", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/adm/{account}/changePassword")
    public ResponseEntity<String> updateAdminPassword(@RequestBody changePasswordDTO dto) {
        AdminTab admin = adminTabMapper.findByAccount(dto.getAccount());
        if (admin == null) {
            return new ResponseEntity<>("用户不存在", HttpStatus.BAD_REQUEST);
        }
        if (admin.getAdmPassword().equals(dto.getOldPassword())) {
            adminTabMapper.updatePassword(dto.getAccount(), dto.getNewPassword());
            return new ResponseEntity<>("修改成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("原密码错误", HttpStatus.BAD_REQUEST);
        }
    }





}
