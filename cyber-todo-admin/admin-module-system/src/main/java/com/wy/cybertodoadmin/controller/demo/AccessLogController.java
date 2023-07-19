package com.wy.cybertodoadmin.controller.demo;

import com.wy.cybertodoadmin.base.aspect.annotation.SystemLog;
import com.wy.cybertodoadmin.core.vo.Res_;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 测试 AOP 拦截控制器
 * @date 2023/7/4 21:59:04
 */
@RestController
@RequestMapping("/log")
@Tag(name = "功能测试模块-测试 AOP 拦截API")
public class AccessLogController {
    @GetMapping("/save")
    @Operation(summary = "测试 AOP 拦截API - 保存")
    @SystemLog(logType = 0, value = "aop test api")
    public Res_<String> save(String name) {
        return Res_.ok("name: " + name + " save success");
    }
}
