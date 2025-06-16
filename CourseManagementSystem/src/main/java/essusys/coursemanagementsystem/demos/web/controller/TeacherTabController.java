package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.TeacherTab;
import essusys.coursemanagementsystem.demos.web.mapper.TeacherTabMapper;
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
@Api(value = "adm/teacherManage")
@RequestMapping(value = "/adm/teacherManage",
        produces = { "application/json" },
        consumes = { "application/json" })
public class TeacherTabController {

    private final TeacherTabMapper teacherTabMapper;

    public TeacherTabController(TeacherTabMapper teacherTabMapper) {
        this.teacherTabMapper = teacherTabMapper;
    }

    // 根据账户查询教师信息
    @Getter
    @Data
    public static class TeacherAccountDTO {
        private String tchAccount;
        public TeacherAccountDTO() {}
        public TeacherAccountDTO(String tchAccount) {this.tchAccount = tchAccount;}
    }
    @PostMapping(value = "/accountSearch")
    public ResponseEntity<TeacherTab> getTeacherByAccount(@RequestBody TeacherAccountDTO dto) {
        TeacherTab teacher = teacherTabMapper.findByAccount(dto.getTchAccount());
        if (teacher != null) {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 根据教师ID查询教师信息
    @Getter
    @Data
    public static class TeacherIdDTO {
        private Integer tchId;
        public TeacherIdDTO() {}
        public TeacherIdDTO(Integer tchId) {this.tchId = tchId;}
    }
    @PostMapping("/tchIdSearch")
    public ResponseEntity<TeacherTab> getTeacherById(@RequestBody TeacherIdDTO dto) {
        TeacherTab teacher = teacherTabMapper.findById(dto.getTchId());
        if (teacher != null) {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 条件查询教师信息
    @PostMapping(value = "/conditionSearch")
    public ResponseEntity<List<TeacherTab>> getTeachersByOption(@RequestBody Map<String, Object> params) {
        String tchName = params.get("tchName") != null ? params.get("tchName").toString() : null;
        String tchSch = params.get("tchSch") != null ? params.get("tchSch").toString() : null;
        Integer tchStatus = params.get("tchStatus") != null ? Integer.parseInt(params.get("tchStatus").toString()) : null;

        List<TeacherTab> teachers = teacherTabMapper.findByOption(tchName, tchSch, tchStatus);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    // 增加教师信息
    @PostMapping("/add")
    public ResponseEntity<String> addTeacher(@RequestBody TeacherTab teacherTab) {
        if(teacherTab.getTchName() == null || teacherTab.getTchAccount() == null ||  teacherTab.getTchPassword() == null ){
            return new ResponseEntity<>("必要信息不能为空",HttpStatus.CREATED);
        }
        if (teacherTabMapper.findByAccount(teacherTab.getTchAccount())!=null) {
            return new ResponseEntity<>("该账号已存在",HttpStatus.CREATED);
        }
        teacherTabMapper.insert(teacherTab);
        return new ResponseEntity<>("添加成功", HttpStatus.CREATED);
    }

    // 更新教师信息
    @PutMapping("/update")
    public ResponseEntity<String> updateTeacher(@RequestBody TeacherTab teacherTab) {
        if(teacherTab.getTchName() == null || teacherTab.getTchAccount() == null ||  teacherTab.getTchPassword() == null ){
            return new ResponseEntity<>("必要信息不能为空",HttpStatus.CREATED);
        }
        teacherTabMapper.update(teacherTab);
        return new ResponseEntity<>("更新成功", HttpStatus.OK);
    }

    // 删除教师信息-未实装
    /*
    @DeleteMapping
    public ResponseEntity<Void> deleteTeacher(Integer tchId) {
        teacherTabMapper.deleteById(tchId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    */
}