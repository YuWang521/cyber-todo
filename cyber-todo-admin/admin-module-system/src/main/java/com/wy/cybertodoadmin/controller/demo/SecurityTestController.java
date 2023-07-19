package com.wy.cybertodoadmin.controller.demo;

import com.wy.cybertodoadmin.core.vo.Res_;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "功能测试模块-测试 Spring Security API")
public class SecurityTestController {

    @GetMapping("/security")
    @Operation(summary = "测试 Spring Security API - 测试 Spring Security")
    public Res_<String> securityTest() {
        return Res_.ok("security test success");
    }
}
