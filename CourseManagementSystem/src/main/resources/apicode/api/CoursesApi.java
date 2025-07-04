/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package apicode.api;

import essusys.coursemanagementsystem.demos.web.controller.ApiUtil;
import io.swagger.annotations.*;
import openapitools.model.InlineObject;
import openapitools.model.InlineObject1;
import openapitools.model.InlineResponse2003;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import javax.validation.Valid;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-20T21:44:39.267865700+08:00[Asia/Shanghai]")

@Validated
@Api(value = "courses", description = "the courses API")
public interface CoursesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /courses : 删除课程
     * 教务员可以对提交的课程进行删除操作
     *
     * @param inlineObject1  (optional)
     * @return  (status code 200)
     *         or  (status code 404)
     */
    @ApiOperation(value = "删除课程", nickname = "coursesDelete", notes = "教务员可以对提交的课程进行删除操作", response = InlineResponse2003.class, tags={ "增删改查课程，评教（XW）", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = InlineResponse2003.class),
        @ApiResponse(code = 404, message = "", response = Object.class) })
    @RequestMapping(value = "/courses",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    default ResponseEntity<InlineResponse2003> coursesDelete(@ApiParam(value = ""  )  @Valid @RequestBody(required = false) InlineObject1 inlineObject1) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"msg\" : \"msg\", \"error\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /courses : 修改课程
     * 对于待审批状态或驳回状态的课程，老师需要进行修改后可通过此接口提交修改后的课程信息
     *
     * @param inlineObject  (optional)
     * @return  (status code 200)
     *         or  (status code 404)
     */
    @ApiOperation(value = "修改课程", nickname = "coursesPost", notes = "对于待审批状态或驳回状态的课程，老师需要进行修改后可通过此接口提交修改后的课程信息", response = InlineResponse2003.class, tags={ "增删改查课程，评教（XW）", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "", response = InlineResponse2003.class),
        @ApiResponse(code = 404, message = "", response = Object.class) })
    @RequestMapping(value = "/courses",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<InlineResponse2003> coursesPost(@ApiParam(value = ""  )  @Valid @RequestBody(required = false) InlineObject inlineObject) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"msg\" : \"msg\", \"error\" : true }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
