package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.entity.ActivityControlTab;
import essusys.coursemanagementsystem.demos.web.mapper.ActivityControlTabMapper;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@Api(value = "adm/activityManage")
@RequestMapping(value = "/adm/activityManage",
        produces = { "application/json" },
        consumes = { "application/json" })
public class ActivityControlTabController {

    private final ActivityControlTabMapper activityControlTabMapper;

    public ActivityControlTabController(ActivityControlTabMapper activityControlTabMapper) {
        this.activityControlTabMapper = activityControlTabMapper;
    }

    // 根据条件查询活动
    @PostMapping(value = "/conditionSearch")
    public ResponseEntity<List<ActivityControlTab>> getActivitiesByOption(@RequestBody Map<String, Object> params) {
        Integer ctrlStatus = params.get("ctrlStatus") != null ? Integer.parseInt(params.get("ctrlStatus").toString()) : null;
        Integer ctrlType = params.get("ctrlType") != null ? Integer.parseInt(params.get("ctrlType").toString()) : null;
        String ctrlName = params.get("ctrlName") != null ? params.get("ctrlName").toString() : null;

        List<ActivityControlTab> activities = activityControlTabMapper.findByOption(ctrlStatus, ctrlType, ctrlName);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // 根据ID查询活动
    /*
    @GetMapping("/{ctrlId}")
    public ResponseEntity<ActivityControlTab> getActivityById(@PathVariable Integer ctrlId) {
        ActivityControlTab activity = activityControlTabMapper.findById(ctrlId);
        if (activity != null) {
            return new ResponseEntity<>(activity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    */

    // 添加活动
    @PostMapping("/add")
    public ResponseEntity<String> addActivity(@RequestBody ActivityControlTab activityControlTab) {
        if (activityControlTab.getCtrlName() == null || activityControlTab.getCtrlStartDate() == null
                || activityControlTab.getCtrlEndDate() == null || activityControlTab.getCtrlStatus() == null
                || activityControlTab.getCtrlType() == null) {
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.BAD_REQUEST);
        }
        activityControlTabMapper.insert(activityControlTab);
        return new ResponseEntity<>("添加成功", HttpStatus.CREATED);
    }

    // 更新活动
    @PutMapping("/update")
    public ResponseEntity<String> updateActivity(@RequestBody ActivityControlTab activityControlTab) {
        if (activityControlTab.getCtrlId() == null || activityControlTab.getCtrlName() == null
                || activityControlTab.getCtrlStartDate() == null || activityControlTab.getCtrlEndDate() == null
                || activityControlTab.getCtrlStatus() == null || activityControlTab.getCtrlType() == null) {
            return new ResponseEntity<>("必要信息不能为空", HttpStatus.BAD_REQUEST);
        }
        activityControlTabMapper.update(activityControlTab);
        return new ResponseEntity<>("更新成功", HttpStatus.OK);
    }

    // 删除活动
    @DeleteMapping("/delete/{ctrlId}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Integer ctrlId) {
        activityControlTabMapper.deleteById(ctrlId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}