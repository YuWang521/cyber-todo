package com.wy.cybertodoadmin.controller.system.login;

import com.wy.cybertodoadmin.base.aspect.annotation.SystemLog;
import com.wy.cybertodoadmin.core.entity.FormLogin;
import com.wy.cybertodoadmin.core.vo.CaptchaVO;
import com.wy.cybertodoadmin.core.vo.Res_;
import com.wy.cybertodoadmin.service.system.login.ISysLoginFormLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 统一认证登录控制器
 * @date 2023/7/19 17:36:52
 */
@RestController
@RequestMapping("/login")
@Tag(name = "系统模块-统一认证登录API")
public class SysLoginController {

    @Resource
    ISysLoginFormLoginService sysLoginFormLoginService;

    // ==================== 表单登录 ====================

    // 表单验证码
    @Operation(summary = "统一认证登录API - 表单验证码")
    @RequestMapping("/form/captcha")
    @ResponseBody
    @SystemLog(logType = 1, value = "统一认证登录API - 表单验证码")
    public Res_<CaptchaVO> formCaptcha(HttpServletRequest request) {
        CaptchaVO captcha = sysLoginFormLoginService.captcha();
        return Res_.ok(captcha);
    }

    // 表单登录
    // /login/form
    @Operation(summary = "统一认证登录API - 表单登录")
    @RequestMapping("/form-login")
    @ResponseBody
    @SystemLog(logType = 1, value = "统一认证登录API - 表单登录")
    public FormLogin formLogin(HttpServletRequest request) {
        ///login/form
        // 走spring security 表单验证
        return null;
    }

    // ==================== 手机号登录 ====================

    // ==================== 第三方登录 ====================

    // ==================== 注册 ====================

    // ==================== 注销 ====================

    // 账号注销
    // 系统默认注册接口 /logout

    // ==================== 账号监控 ====================

    // 在线账号列表

    // 强制下线
    @Operation(summary = "统一认证登录API - 强制下线")
    @RequestMapping("/force-logout")
    @ResponseBody
    @SystemLog(logType = 1, value = "统一认证登录API - 强制下线")
    public Res_<String> forceLogout(String username) {

        return null;
    }
}
