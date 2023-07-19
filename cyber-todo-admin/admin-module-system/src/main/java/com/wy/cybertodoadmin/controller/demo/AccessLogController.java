package com.wy.cybertodoadmin.controller.demo;

import com.alibaba.fastjson2.JSON;
import com.wy.cybertodoadmin.base.aspect.annotation.SystemLog;
import com.wy.cybertodoadmin.base.controller.BaseController;
import com.wy.cybertodoadmin.base.until.UserSessionUtil;
import com.wy.cybertodoadmin.core.vo.Res_;
import com.wy.cybertodoadmin.system.entity.account.SystemUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 测试 AOP 拦截控制器
 * @date 2023/7/4 21:59:04
 */
@RestController
@RequestMapping("/log")
@Tag(name = "功能测试模块-测试 AOP 拦截API")
public class AccessLogController extends BaseController {
    @GetMapping("/save")
    @Operation(summary = "测试 AOP 拦截API - 保存")
    @SystemLog(logType = 0, value = "aop test api")
    public Res_<String> save(String name) {

        return Res_.ok("name: " + name + " save success");
    }

    @GetMapping("/user-info")
    @Operation(summary = "测试 AOP 拦截API - 获取用户信息")
    @SystemLog(logType = 0, value = "aop test api")
    public Res_<String> userInfo(String name) {
        SystemUserDetails userDetails = UserSessionUtil.getUserDetails();
        String jsonString = JSON.toJSONString(userDetails);
        return Res_.ok("jsonString: " + jsonString);
    }

    @GetMapping("/user-auth")
    @Operation(summary = "测试 AOP 拦截API - 获取用户权限")
    @SystemLog(logType = 0, value = "aop test api")
    public Res_<String> userAuth(String name) {
        Authentication authentication = UserSessionUtil.getAuthentication();
        String jsonString = JSON.toJSONString(authentication.getAuthorities());
        return Res_.ok("jsonString: " + jsonString);
    }
}
