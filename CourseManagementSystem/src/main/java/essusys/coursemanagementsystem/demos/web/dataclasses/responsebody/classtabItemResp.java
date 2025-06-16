package essusys.coursemanagementsystem.demos.web.dataclasses.responsebody;

import java.util.Set;

/*
 *
 * 获得课表接口的响应体列表单元
 *
 */

public class classtabItemResp {
    private String cou_name;    // 课程名
    private String cla_timeset;   // 上课节次
    private String cla_weekset;   // 上课周次
    private String cla_classroom;   // 上课地点
    private String tch_name;    //上课教师姓名

    // // ! 必须要有无参构造函数，否则MyBatis无法正确映射数据库数据类 !
    public classtabItemResp() {}

    public classtabItemResp(String cou_name, String cla_timeset, String cla_weekset, String cla_classroom, String tch_name) {
        this.cou_name = cou_name;
        this.cla_timeset = cla_timeset;
        this.cla_weekset = cla_weekset;
        this.cla_classroom = cla_classroom;
        this.tch_name = tch_name;
    }

    public String getCou_name() {
        return cou_name;
    }

    public  String getCla_timeset() {
        return cla_timeset;
    }

    public String getCla_weekset() {
        return cla_weekset;
    }

    public String getCla_classroom() {
        return cla_classroom;
    }

    public String getTch_name() {
        return tch_name;
    }
}
