package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.*;
import essusys.coursemanagementsystem.demos.web.mapper.StudentGradeMapper;
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

import lombok.Data;
import lombok.Getter;

@RestController
@Validated
@Api(value = "tch/studentGrade")
@RequestMapping(value = "/tch/studentGrade",
        produces = { "application/json" },
        consumes = { "application/json" })

public class StudentGradeController {



    private final StudentGradeMapper studentGradeMapper;

    public StudentGradeController( StudentGradeMapper studentGradeMapper) {
        this.studentGradeMapper = studentGradeMapper;
    }

    /*
     *
     * 查询模块
     *
     */
    //      根据账户查询学生信息(唯一)



    // 查询学生成绩信息
    @GetMapping(value = "/conditionSearch")
    public ResponseEntity<List<ScClCouTab>> getStudentsByOption(@RequestBody Map<String, Object> params) {
        System.out.println("Received params: " + params);

        Integer scoStuId = params.get("scoStuId") != null ? Integer.parseInt(params.get("scoStuId").toString()) : null;
        Integer couTerm = params.get("couTerm") != null ? Integer.parseInt(params.get("couTerm").toString()): null;
        String couRank = params.get("couRank") != null ? params.get("couRank").toString() : null;

        List<ScClCouTab> students = studentGradeMapper.findByOption(scoStuId, couTerm, couRank);
        System.out.println("Query result: " + students); // 打印查询结果
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


    // 查询教师教学班id
    @PostMapping(value = "/conditionSearch2")
    public ResponseEntity<List<ClaCouTab>> getclaIdByOption(@RequestBody Map<String, Object> params) {
        System.out.println("Received params: " + params);

        Integer claTchId = params.get("claTchId") != null ? Integer.parseInt(params.get("claTchId").toString()):null;


        List<ClaCouTab> students = studentGradeMapper.findByOption2(claTchId);
        System.out.println("Query result: " + students); // 打印查询结果
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping(value = "/conditionSearch3")
    public ResponseEntity<List<ScoreTab>> getclaIdByOption2(@RequestBody Map<String, Object> params) {
        System.out.println("Received params: " + params);

        Integer scoClaId = params.get("scoClaId") != null ? Integer.parseInt(params.get("scoClaId").toString()):null;


        List<ScoreTab> students = studentGradeMapper.findByOption3(scoClaId);
        System.out.println("Query result: " + students); // 打印查询结果
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /*
     *
     * 增删改模块
     *
     */
    // 增加学生成绩信息
//        @PostMapping("/add")
//        public ResponseEntity<String> addStudent(@RequestBody ScoreTab scoreTab) {
//            studentGradeMapper.insert(scoreTab);
//            return new ResponseEntity<>("添加成功",HttpStatus.CREATED);
//        }

    // 更新学生成绩信息
    @PutMapping("/update")
    public ResponseEntity<String> updateStudent(@RequestBody ScoreTab scoreTab) {
        studentGradeMapper.update(scoreTab);
        return new ResponseEntity<>("更新成功",HttpStatus.OK);
    }

    // 删除学生成绩信息
    @DeleteMapping
    public ResponseEntity<Void> deleteStudent(Integer scoStuId,Integer scoClaId) {
        studentGradeMapper.deleteById(scoStuId,scoClaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

