package controllercode;

import essusys.coursemanagementsystem.demos.web.api.classtable.ClasstableGetStuTableApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-20T21:44:39.267865700+08:00[Asia/Shanghai]")

@Controller
@RequestMapping("${openapi.OpenAPI-Spring.base-path:}")
public class ClasstableGetStuTableApiController implements ClasstableGetStuTableApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ClasstableGetStuTableApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
