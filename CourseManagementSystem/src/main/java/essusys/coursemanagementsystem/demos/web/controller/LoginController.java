package essusys.coursemanagementsystem.demos.web.controller;

import essusys.coursemanagementsystem.demos.web.dataclasses.requestbody.LoginRequest;
import essusys.coursemanagementsystem.demos.web.mapper.ClassTabMapper;
import essusys.coursemanagementsystem.demos.web.mapper.userMapper;
import essusys.coursemanagementsystem.demos.web.resp;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/*
 * POST /login : 登陆界面
 */

@RestController
@Validated
@Api(value = "login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    // 这里是API的注解头，用于标明API的名称、描述、请求方法、请求路径等信息，删除不影响编译
    @ApiOperation(value = "登陆界面", nickname = "loginPost", notes = "", response = Object.class, tags = { "登陆界面（CYN）" })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login successful", response = Object.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Object.class),
            @ApiResponse(code = 400, message = "Invalid input", response = Object.class)
    })
    // API寻址
    @RequestMapping(value = "/login",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<Object> loginPost(
            @ApiParam(value = "Login request containing username and password details")
            @Valid @RequestBody LoginRequest loginRequest) {

        // 检查请求体是否包含有效的用户名和密码
        if (loginRequest == null || loginRequest.getUsername() == null || loginRequest.getPassword() == null || loginRequest.getUsertype() == null ) {
            // 错误的请求
            return new ResponseEntity<>("Invalid login request", HttpStatus.BAD_REQUEST);
        }

        // 调用 AuthService 验证用户身份
        resp _resp = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getUsertype());

        if (_resp.message.equals("登陆成功")) {
            // 登录成功
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else if (_resp.message.equals("用户不存在") || _resp.message.equals("密码错误") )  {
            // 用户名或密码错误
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
        else if (_resp.message.equals("账户已停用"))  {
            // 用户名或密码错误
            return new ResponseEntity<>("Account has been disabled", HttpStatus.UNAUTHORIZED);
        }
        else {
            // 用户类型错误
            return new ResponseEntity<>("Invalid user type", HttpStatus.UNAUTHORIZED);
        }
    }


    // 逻辑实现业务代码
    @Service
    public static class AuthService {

        private final essusys.coursemanagementsystem.demos.web.mapper.userMapper userMapper;
        // private final essusys.coursemanagementsystem.demos.web.mapper.ClassTabMapper ClassTabMapper;

        public AuthService(userMapper userMapper, essusys.coursemanagementsystem.demos.web.mapper.ClassTabMapper classTabMapper) {
            this.userMapper = userMapper;
            // ClassTabMapper = classTabMapper;
        }

        public resp authenticate(String username, String password, Integer userType) {
            // 用户身份验证逻辑
            // System.out.println(userType);
            if (userType == 2) {
                // 学生身份验证
                // System.out.println(ClassTabMapper.getCouCodeByClaId(1));
                // System.out.println("学生用户身份验证" + username+ " " +password);
                String _password = userMapper.getStuPasswordByAccount(username);
                Integer  _status = userMapper.getStuStatusByAccount(username);
                return getResp(password, _password, _status);

            } else if (userType == 1) {
                // 教师身份验证
                // System.out.println("教师用户身份验证" + username+ " " + password);
                String _password = userMapper.getTchPasswordByAccount(username);
                Integer  _status = userMapper.getTchStatusByAccount(username);
                return getResp(password, _password, _status);
            }
            else if (userType == 0){
                // 教务员身份验证
                // System.out.println("教务员用户身份验证" + username+ " " + password);
                String _password = userMapper.getAdmPasswordByAccount(username);
                Integer  _status = userMapper.getAdmStatusByAccount(username);
                return getResp(password, _password, _status);
            }
            else {
                return new resp(true,"用户类型错误");
            }

        }

        private resp getResp(String password, String _password, Integer _status) {
            if (_password  == null) {
                return new resp(true, "用户不存在");
            }
            else if (!_password.equals(password)) {
                return new resp(true, "密码错误");
            }
            else if (_status == 0) {
                return new resp(true, "账户已停用");
            }
            else {
                return new resp(false, "登陆成功");
            }
        }
    }

}
