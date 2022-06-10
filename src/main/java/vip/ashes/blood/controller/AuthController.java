package vip.ashes.blood.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.ashes.blood.constants.UserConstants;
import vip.ashes.blood.entity.User;
import vip.ashes.blood.service.AuthService;
import vip.ashes.blood.service.UserService;
import vip.ashes.blood.utils.Result;
import vip.ashes.blood.utils.ResultCode;


/**
 * @author lovleiness
 * @description 认证授权
 **/
@RestController
@RequestMapping("/auth")
@Api(tags = "认证")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public Result login(@ApiParam("用户Bean") @RequestBody User loginRequest,
                        @ApiParam("随机生成的鉴权码") @RequestParam String captchaVerification,
                        @ApiParam("验证码") @RequestParam String code) {

        if (userService.checkCaptcha(captchaVerification, code)) {
            return authService.createToken(loginRequest);
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }

    @PostMapping("/logout")
    @ApiOperation("退出")
    public Result logout() {
        return authService.removeToken();
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody User userRegister,
                           @ApiParam("验证码") @RequestParam String code) {

        if (userService.checkCaptcha(UserConstants.REGISTER_MAIL + userRegister.getEmail(), code)) {
            userService.register(userRegister);
            return Result.ok();
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }

    @PostMapping("/captcha")
    @ApiOperation("获取验证码")
    public Result captcha() {
        return userService.generateCaptcha();
    }

    @PostMapping("/registerMail")
    @ApiOperation("获取邮箱验证码,用于注册时")
    public Result registerMail(@RequestBody User user,
                               @ApiParam("随机生成的鉴权码") @RequestParam String captchaVerification,
                               @ApiParam("验证码") @RequestParam String code) {
        if (userService.checkCaptcha(captchaVerification, code)) {
            return userService.registerMail(user);
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }
}
