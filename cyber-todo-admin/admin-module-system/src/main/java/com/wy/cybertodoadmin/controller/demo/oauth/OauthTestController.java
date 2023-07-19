package com.wy.cybertodoadmin.controller.demo.oauth;

import com.wy.cybertodoadmin.base.aspect.annotation.SystemLog;
import com.wy.cybertodoadmin.core.vo.Res_;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangYu
 * @project cyber-todo
 * @description Oauthk控制器
 * @date 2023/7/19 16:03:16
 */
@RestController

@Tag(name = "功能测试模块-Oauth控制器")
public class OauthTestController {

    @GetMapping("/oauth/auth/github")
    @Operation(summary = "测试 Oauth 授权 - github")
    @SystemLog(logType = 0, value = "测试 Oauth 授权 - github")
    @ResponseBody
    public Res_<Object> github() {

        return Res_.ok(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/user-info")
    @Operation(summary = "测试 Oauth 授权 - github")
    @SystemLog(logType = 0, value = "测试 Oauth 授权 - github")
    @ResponseBody
    public Object userInfo() {

        return SecurityContextHolder.getContext().getAuthentication();
    }

}
