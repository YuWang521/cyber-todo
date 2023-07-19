package com.wy.cybertodoadmin.controller.demo;

import com.wy.cybertodoadmin.base.aspect.annotation.SystemLog;
import com.wy.cybertodoadmin.core.vo.Res_;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 测试redis连接
 * @date 2023/7/6 17:03:06
 */
@RestController
@RequestMapping("/redis")
@Tag(name = "功能测试模块-测试redis连接API")
public class RedisTestConroller {

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/test")
    @SystemLog(logType = 0, value = "redis test api")
    @Operation(summary = "测试redis连接API - 测试opsForValue")
    public Res_<String> test(String name) {
        redisTemplate.opsForValue().set("name", name);
        return Res_.ok("name: " + name + " save success");
    }

}
