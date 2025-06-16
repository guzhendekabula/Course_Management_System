package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.ClassTab;
import essusys.coursemanagementsystem.demos.web.dataclasses.requestbody.classtabRequest;
import essusys.coursemanagementsystem.demos.web.dataclasses.responsebody.classtabItemResp;
import essusys.coursemanagementsystem.demos.web.dataclasses.responsebody.classtablistResp;
import essusys.coursemanagementsystem.demos.web.mapper.ClassTabMapper;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * GET /classtable : 课表界面
 */

@RestController
@Validated
@Api(value = "classtable")
public class ClasstabController {

    List<classtabItemResp> classtabResp  = null;
    private final ClassTabMapper ClassTabMapper;
    private final essusys.coursemanagementsystem.demos.web.mapper.userMapper userMapper;
    private final packagingRespbody packagingRespbody;

    public ClasstabController(ClassTabMapper ClassTabMapper, essusys.coursemanagementsystem.demos.web.mapper.userMapper userMapper, ClasstabController.packagingRespbody packagingRespbody) {
        this.ClassTabMapper = ClassTabMapper;
        this.userMapper = userMapper;
        this.packagingRespbody = packagingRespbody;
    }

    @ApiOperation(value = "获取课表", nickname = "classtab", notes = "", response = classtablistResp.class, tags={ "课表", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = classtablistResp.class),
            @ApiResponse(code = 404, message = "", response = Object.class)
    })
    @RequestMapping(value = "/classtable",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)

    public ResponseEntity<Object> classtabPost(
            @ApiParam(value = "Login request containing username and Usertype details")
            @Valid @RequestBody classtabRequest classtabrequest) {

        if (classtabrequest == null || classtabrequest.getUsername() == null || classtabrequest.getUsertype() == null ) {
            // 错误的请求
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }

        if (classtabrequest.getUsertype() == 2) {
            // 先根据账户查询到学号
            Integer stuId = this.userMapper.getStuidByAccount(classtabrequest.getUsername());
            System.out.println(stuId);

            // 再根据学号查询到所有对应的教学班
            List<ClassTab> listclasstab = this.ClassTabMapper.getClassByStuId(stuId);
            if (listclasstab == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("数据库映射错误"+classtabrequest.getUsername());
            }

            // 把所有对应的教学班在业务代码中全部打包为响应体(列表)类
            packagingRespbody.stuPackaging(listclasstab);
            // 返回响应体(列表)类
            return ResponseEntity.status(HttpStatus.OK).body(packagingRespbody.classtablistresp);
        }

        else if (classtabrequest.getUsertype() == 1) {
            // 先根据账户查询到职工号
            Integer tchId = this.userMapper.getTchidByAccount(classtabrequest.getUsername());
            // 再根据职工号查询到所有对应的教学班
            List<ClassTab> listclasstab = this.ClassTabMapper.getClassByTchId(tchId);
            if (listclasstab == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("数据库映射错误"+classtabrequest.getUsername());
            }

            // 把所有对应的教学班在业务代码中全部打包为响应体(列表)类
            packagingRespbody.tchPackaging(listclasstab);
            // 返回响应体(列表)类
            return ResponseEntity.status(HttpStatus.OK).body(packagingRespbody.classtablistresp);
        }

        else {
            return new ResponseEntity<>("Invalid usertype", HttpStatus.UNAUTHORIZED);
        }

    }

    // 业务代码，负责将classtablist转化为classtablistResp
    @Service
    public static class packagingRespbody {
        private final essusys.coursemanagementsystem.demos.web.mapper.courseMapper courseMapper;
        private final essusys.coursemanagementsystem.demos.web.mapper.userMapper userMapper;
        public classtablistResp classtablistresp = new classtablistResp();

        public packagingRespbody(essusys.coursemanagementsystem.demos.web.mapper.courseMapper courseMapper, essusys.coursemanagementsystem.demos.web.mapper.userMapper userMapper) {
            this.courseMapper = courseMapper;
            this.userMapper = userMapper;
        }

        // 学生打包(需要教师名)
        public void stuPackaging(List<ClassTab> listclasstab) {
            // 因为classtablistresp在static类型中，所以需要先清除之前的数据，否则会存在查询结果叠加
            if(classtablistresp.classtabResp != null)
                classtablistresp.clear();

            for (ClassTab classtab : listclasstab) {
                // if ( classtab == null ) System.out.println("classtab is null");
                String courseName = this.courseMapper.getCourseNameByCode(classtab.getClaCouCode());
                String tchName = this.userMapper.getTchNameByTchId(classtab.getClaTchId());


                classtablistresp.add(new classtabItemResp(courseName, classtab.getClaTimeset(), classtab.getClaWeekset(), classtab.getClaClassroom(), tchName));
            }
        }

        // 教师打包(无需教师名)
        public void tchPackaging(List<ClassTab> listclasstab) {
            // 因为classtablistresp在static类型中，所以需要先清除之前的数据，否则会存在查询结果叠加
            if(classtablistresp.classtabResp != null)
                classtablistresp.clear();

            for (ClassTab classtab : listclasstab) {
                String courseName = this.courseMapper.getCourseNameByCode(classtab.getClaCouCode());
                String tchName = null;
                classtablistresp.add(new classtabItemResp(courseName, classtab.getClaTimeset(), classtab.getClaWeekset(), classtab.getClaClassroom(), tchName));
            }
        }
    }

}
