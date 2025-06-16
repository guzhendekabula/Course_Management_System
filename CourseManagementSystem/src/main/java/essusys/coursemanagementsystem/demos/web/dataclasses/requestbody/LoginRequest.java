package essusys.coursemanagementsystem.demos.web.dataclasses.requestbody;

public class LoginRequest {
    // ! java数据类型和MySQL数据类型必须正确对应，否则MyBatis无法正确从数据库映射数据类 !
    private String username;
    private String password;
    private Integer usertype;

    // ! 必须要有无参构造函数，否则Springboot无法正确接受请求体 !
    public LoginRequest() {}

    // ! get函数要按照java规范惯例命名，否则Springboot无法正确识别 !
    // get函数命名规则: get + 首字母大写的变量名

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUsertype() {
        return usertype;
    }
}
