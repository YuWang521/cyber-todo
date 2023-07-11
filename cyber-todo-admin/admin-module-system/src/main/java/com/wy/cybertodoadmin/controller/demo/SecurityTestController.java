package com.wy.cybertodoadmin.controller.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 测试 Spring Security
 * @date 2023/7/11 11:49:41
 */
@RestController
@Slf4j
public class SecurityTestController {

    @GetMapping("/security")
    public String securityTest() {
        return "security test success";
    }
}
