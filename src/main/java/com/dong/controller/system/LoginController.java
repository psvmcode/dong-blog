package com.dong.controller.system;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.dong.common.ResponseResult;
import com.dong.service.LoginService;
import com.dong.dto.LoginDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@Api(tags = "登录-接口")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @RequestMapping(value = "/captchaImage", method = RequestMethod.GET)
    @ApiOperation(value = "获取验证码", httpMethod = "GET", response = ResponseResult.class, notes = "获取验证码")
    public ResponseResult getCode(HttpServletResponse response) throws IOException {
        Map<String, String> result = loginService.getCode(response);
        return ResponseResult.success("获取验证码成功", result);
    }

    @PostMapping("login")
    public ResponseResult login(@Validated @RequestBody LoginDTO vo) {
        return loginService.login(vo);
    }

    @SaCheckLogin
    @GetMapping("logout")
    public ResponseResult logout() {
        StpUtil.logout();
        return ResponseResult.success("退出成功");
    }

}
