package com.wy.cybertodoadmin.controller;

import com.wy.cybertodoadmin.base.aspect.annotation.SystemLog;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
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
public class RedisTestConroller {

//    @Resource
//    private RedisTemplate redisTemplate;

    @RequestMapping("/test")
    @SystemLog(logType = 0, value = "redis test api")
    public String test(String name) {
//        redisTemplate.opsForValue().set("name", name);
        return "name: " + name + " save success";
    }

}
