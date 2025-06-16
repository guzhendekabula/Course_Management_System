package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.*;
import essusys.coursemanagementsystem.demos.web.mapper.SeDeClassMapper;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@Api(value = "stu/classTable")
@RequestMapping(value = "/stu/classTable",
        produces = { "application/json" },
        consumes = { "application/json" })

public class SeDeClassController {


        private final SeDeClassMapper seDeClassMapper;

        public SeDeClassController( SeDeClassMapper seDeClassMapper) {
            this.seDeClassMapper = seDeClassMapper;
        }

        /*
         *
         * 查询模块
         *
         */
        //      根据账户查询学生信息(唯一)



        // 查询学生可选课表信息
        @PostMapping(value = "/Search")
        public ResponseEntity<List<SDClassTab>> getStudentsByOption(@RequestBody Map<String, Object> params) {
            System.out.println("Received params: " + params);

            Integer scoStuId = params.get("scoStuId") != null ? Integer.parseInt(params.get("scoStuId").toString()) : null;

            List<SDClassTab> students = seDeClassMapper.findByOption(scoStuId);
            System.out.println("Query result: " + students); // 打印查询结果
            return new ResponseEntity<>(students, HttpStatus.OK);
        }


        // 查询学生已选课程信息
        @PostMapping(value = "/selected")
        public ResponseEntity<List<SD2ClassTab>> getclaIdByOption(@RequestBody Map<String, Object> params) {
            System.out.println("Received params: " + params);

            Integer scoStuId = params.get("scoStuId") != null ? Integer.parseInt(params.get("scoStuId").toString()) : null;


            List<SD2ClassTab> students = seDeClassMapper.findByOption2(scoStuId);
            System.out.println("Query result: " + students); // 打印查询结果
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
        /*
         *
         * 增删改模块
         *
         */
        // 增加学生成绩信息
        @PostMapping("/add")
        public ResponseEntity<String> addStudent(@RequestBody ScoreTab scoreTab) {
            seDeClassMapper.insert(scoreTab);
            return new ResponseEntity<>("添加成功",HttpStatus.CREATED);
        }

//        // 更新学生成绩信息
//        @PutMapping("/update")
//        public ResponseEntity<String> updateStudent(@RequestBody ScoreTab scoreTab) {
//            seDeClassMapper.update(scoreTab);
//            return new ResponseEntity<>("更新成功",HttpStatus.OK);
//        }

        // 删除学生成绩信息
        @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent( @RequestBody ScoreTab scoreTab) {
        seDeClassMapper.deleteById(scoreTab);
        return new ResponseEntity<>("删除成功",HttpStatus.OK);


    }

    }



