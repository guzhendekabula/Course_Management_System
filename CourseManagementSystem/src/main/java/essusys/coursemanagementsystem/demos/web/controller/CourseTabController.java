package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.CourseTab;
import essusys.coursemanagementsystem.demos.web.mapper.CourseTabMapper;
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
@Api(value = "/courseManage")
@RequestMapping(value = "/courseManage",
        produces = { "application/json" },
        consumes = { "application/json" })
public class CourseTabController {

    private final CourseTabMapper courseTabMapper;

    public CourseTabController(CourseTabMapper courseTabMapper) {
        this.courseTabMapper = courseTabMapper;
    }

    /*
     *
     * 查询模块
     *
     */
    // 根据课程识别码查询课程信息(唯一)
    @Getter
    @Data
    public static class CourseCodeDTO {
        private String couCode;
        public CourseCodeDTO() {}
        public CourseCodeDTO(String couCode) {this.couCode = couCode;}
    }
    @PostMapping(value = "/codeSearch")
    public ResponseEntity<CourseTab> getCourseByCode(@RequestBody CourseCodeDTO dto) {
        CourseTab course = courseTabMapper.findByCode(dto.getCouCode());
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            System.out.println("Course not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 条件查询课程信息,允许缺省
    @PostMapping(value = "/conditionSearch")
    public ResponseEntity<List<CourseTab>> getCoursesByOption(@RequestBody Map<String, Object> params) {
        Integer couTerm = params.get("couTerm") != null ? Integer.parseInt(params.get("couTerm").toString()) : null;
        String couType = params.get("couType") != null ? params.get("couType").toString() : null;
        Integer couStatus = params.get("couStatus") != null ? Integer.parseInt(params.get("couStatus").toString()) : null;
        String couName = params.get("couName") != null ? params.get("couName").toString() : null;

        List<CourseTab> courses = courseTabMapper.findByOption(couTerm, couType, couStatus, couName);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    /*
     *
     * 增删改模块
     *
     */
    // 增加课程信息
    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody CourseTab courseTab) {
        if (courseTab.getCouCode() == null || courseTab.getCouName() == null || courseTab.getCouTerm() == null ||
                courseTab.getCouType() == null || courseTab.getCouStatus() == null || courseTab.getCouScore() == null) {
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.BAD_REQUEST);
        }
        if (courseTabMapper.findByCode(courseTab.getCouCode()) != null) {
            return new ResponseEntity<>("该课程识别码已存在", HttpStatus.BAD_REQUEST);
        }
        courseTabMapper.insert(courseTab);
        return new ResponseEntity<>("添加成功", HttpStatus.CREATED);
    }

    // 更新课程信息
    @PutMapping("/update")
    public ResponseEntity<String> updateCourse(@RequestBody CourseTab courseTab) {
        if (courseTab.getCouCode() == null || courseTab.getCouName() == null || courseTab.getCouTerm() == null ||
                courseTab.getCouType() == null || courseTab.getCouStatus() == null || courseTab.getCouScore() == null) {
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.BAD_REQUEST);
        }
        courseTabMapper.update(courseTab);
        return new ResponseEntity<>("更新成功", HttpStatus.OK);
    }

    // 删除课程信息
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCourse(@RequestParam String couCode) {
        courseTabMapper.deleteByCode(couCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}