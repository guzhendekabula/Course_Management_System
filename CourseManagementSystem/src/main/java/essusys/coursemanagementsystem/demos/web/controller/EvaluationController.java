package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.*;
import essusys.coursemanagementsystem.demos.web.mapper.EvaluateMapper;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@Api(value = "eva")
@RequestMapping(value = "/eva",
        produces = { "application/json" },
        consumes = { "application/json" })


public class EvaluationController {

    private final EvaluateMapper evaluateMapper;

    public EvaluationController( EvaluateMapper evaluateMapper) {
        this.evaluateMapper = evaluateMapper;
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<EvaluationSTab>> getStudentsByOption(@RequestBody Map<String, Object> params) {
        System.out.println("Received params: " + params);

        Integer evaTchId = params.get("evaTchId") != null ? Integer.parseInt(params.get("evaTchId").toString()) : null;

        List<EvaluationSTab> students = evaluateMapper.findByOption(evaTchId);
        System.out.println("Query result: " + students); // 打印查询结果
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    //评教
    @PostMapping("/eva")
    public ResponseEntity<String> addStudent(@RequestBody EvaluationTab evaluateTab) {
        evaluateMapper.insert(evaluateTab);
        return new ResponseEntity<>("添加成功",HttpStatus.CREATED);
    }
}
