package essusys.coursemanagementsystem.demos.web;

// 这是一个默认的响应类
public class resp {
    // 0: success, 1: error
    public boolean error;
    public String  message = "";

    public resp(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

}
