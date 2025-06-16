package essusys.coursemanagementsystem.demos.web.dataclasses.responsebody;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * 获得课表接口的响应体列表(用类包装)
 *
 */
public class classtablistResp {
    public List<classtabItemResp> classtabResp;

    // 默认构造函数，初始化 classtabResp
    public classtablistResp() {
        this.classtabResp = new ArrayList<>();
    }

    // 带参构造函数
    public classtablistResp(List<classtabItemResp> classtabResp) {
        this.classtabResp = classtabResp;
    }

    public List<classtabItemResp> getClasstabResp() {
        return classtabResp;
    }

    public void add(classtabItemResp classtabItemResp) {
        classtabResp.add(classtabItemResp);
    }

    public void clear() {
        classtabResp.clear();
    }
}