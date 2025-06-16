package essusys.coursemanagementsystem.demos.web.mapper;

import essusys.coursemanagementsystem.demos.web.entity.CourseTab;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface courseMapper {

    /*
     *
     * 根据课程识别号查询课程
     *
     */
    @Select("SELECT * FROM course_tab WHERE cou_code = #{code}")
    public CourseTab getCourseByCode(@Param("code") String code);

    /*
     *
     * 根据课程识别号查询课程名
     *
     */
    @Select("SELECT cou_name FROM course_tab WHERE cou_code = #{code}")
    public String getCourseNameByCode(@Param("code") String code);

    /*
     *
     * 根据课程识别号查询课程学分
     *
     */
    @Select("SELECT cou_score FROM course_tab WHERE cou_code = #{code}")
    public Integer getCourseScoreByCode(@Param("code") String code);

    /*
     *
     * 根据课程识别号查询课程类型
     *
     */
    @Select("SELECT cou_type FROM course_tab WHERE cou_code = #{code}")
    public Integer getCourseTypeByCode(@Param("code") String code);

    /*
     *
     * 根据课程识别号查询课程级别
     *
     */
    @Select("SELECT cou_rank FROM course_tab WHERE cou_code = #{code}")
    public Integer getCourseRankByCode(@Param("code") String code);

    /*
     *
     * 根据课程识别号查询课程语言
     *
     */
    @Select("SELECT cou_language FROM course_tab WHERE cou_code = #{code}")
    public String getCourseLanguageByCode(@Param("code") String code);

    /*
     *
     * 根据课程识别号查询课程大纲
     *
     */
    @Select("SELECT cou_text FROM course_tab WHERE cou_code = #{code}")
    public String getCourseTextByCode(@Param("code") String code);

    /*
     *
     * 根据课程识别号查询课程开设学期
     *
     */
    @Select("SELECT cou_term FROM course_tab WHERE cou_code = #{code}")
    public Integer getCourseTermByCode(@Param("code") String code);

    /*
     *
     * 根据课程识别号查询课程评分/评级
     *
     */
    @Select("SELECT cou_ifscore FROM course_tab WHERE cou_code = #{code}")
    public Integer getCourseIfscoreByCode(@Param("code") String code);
}
