package com.wy.cybertodoadmin.controller.demo;

import com.wy.cybertodoadmin.base.aspect.annotation.SystemLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 测试 AOP 拦截控制器
 * @date 2023/7/4 21:59:04
 */
@RestController
@RequestMapping("/log")
public class AccessLogController {
    @RequestMapping("/save")
    @SystemLog(logType = 0, value = "aop test api")
    public String save(String name) {
        return "name: " + name + " save success";
    }
}
