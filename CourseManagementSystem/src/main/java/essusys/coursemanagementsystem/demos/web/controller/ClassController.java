package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.ClassTab;
import essusys.coursemanagementsystem.demos.web.mapper.ClassTabMapper;
import io.swagger.annotations.Api;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@Api(value = "adm/classManage")
@RequestMapping(value = "/adm/classManage",
        produces = { "application/json" },
        consumes = { "application/json" })
public class ClassController {

    private final ClassTabMapper classTabMapper;

    public ClassController(ClassTabMapper classTabMapper) {
        this.classTabMapper = classTabMapper;
    }

    /*
     * 查询模块
     */
    // 根据课程码、教师ID、学院条件查询教学班信息
    @PostMapping(value = "/conditionSearch")
    public ResponseEntity<List<ClassTab>> getClassesByOption(@RequestBody Map<String, Object> params) {
        String claCouCode = params.get("claCouCode") != null ? params.get("claCouCode").toString() : null;
        Integer claTchId = params.get("claTchId") != null ? Integer.parseInt(params.get("claTchId").toString()) : null;
        String claToschool = params.get("claToschool") != null ? params.get("claToschool").toString() : null;

        List<ClassTab> classes = classTabMapper.findByOption(claCouCode, claTchId, claToschool);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @Getter
    public static class StuIdDTO {
        private Integer stuId;
        public StuIdDTO() {}
        public StuIdDTO(Integer stuId) {this.stuId = stuId;}
    }
    // 根据学号查教学班信息
    @PostMapping(value = "/stuSearch")
    public ResponseEntity<List<ClassTab>> getClassesByStuId(@RequestBody StuIdDTO stuIdDTO) {
        Integer stuId = stuIdDTO.getStuId();
        List<ClassTab> classes = classTabMapper.getClassByStuId(stuId);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }



    /*
     * 增删改模块
     */
    // 增加教学班信息
    @PostMapping("/add")
    public ResponseEntity<String> addClass(@RequestBody ClassTab classTab) {
        if (classTab.getClaCouCode() == null || classTab.getClaTchId() == null
                || classTab.getClaToschool1() == null) {
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.BAD_REQUEST);
        }
        classTabMapper.insert(classTab);
        return new ResponseEntity<>("添加成功", HttpStatus.CREATED);
    }

    // 更新教学班信息
    @PutMapping("/update")
    public ResponseEntity<String> updateClass(@RequestBody ClassTab classTab) {
        if (classTab.getClaCouCode() == null || classTab.getClaTchId() == null
                || classTab.getClaToschool1() == null) {
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.BAD_REQUEST);
        }
        classTabMapper.update(classTab);
        return new ResponseEntity<>("更新成功", HttpStatus.OK);
    }

    @Getter
    public static class ClaIdDTO {
        private Integer claId;
        public ClaIdDTO() {}
        public ClaIdDTO(Integer claId) {this.claId = claId;}
    }
    // 删除教学班信息
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteClass(@RequestBody ClaIdDTO claIdDTO) {
        Integer claId = claIdDTO.getClaId();
        classTabMapper.deleteById(claId);
        return new ResponseEntity<>("删除成功", HttpStatus.OK);
    }
}
