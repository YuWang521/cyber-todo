package com.wy.cybertodoadmin.controller.demo;

import com.wy.cybertodoadmin.base.aspect.annotation.SystemLog;
import com.wy.cybertodoadmin.core.vo.Res_;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import com.wy.cybertodoadmin.system.entity.CasheObject;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 测试 redisson 缓存管理器
 * @date 2023/7/7 11:52:53
 */
@RestController
@RequestMapping("/rediscache")
@CacheConfig(cacheNames = "testCache")
@Tag(name = "功能测试模块-测试 redisson 缓存管理器API")
public class RedissonCacheManagerTestController {

    /**
     * 使用 @Cacheable 等注解实现的缓存逻辑,
     * 本质是通过请求参数作为缓存键,判断缓存中是否存在对应的数据,
     * 如果存在则直接返回缓存值,否则执行方法体并将结果缓存。
     * @param id 请求参数
     * @return 返回值
     */
    @Cacheable("products")
    @SystemLog(value = "redisson cache test api")
    @GetMapping("/String/{id}")
    @Operation(summary = "测试 redisson 缓存管理器API - 字符串类型")
    public int getProduct(@PathVariable int id) {
        // 第一次调用会查询数据库,后续调用会返回缓存结果
        // 延时 5s
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 缓存对象
     */
    @Cacheable(cacheNames = "object-cache", key = "#casheObject.#id")
    @SystemLog(value = "redisson cache test api")
    @GetMapping("/Object/{id}")
    @Operation(summary = "测试 redisson 缓存管理器API - 对象类型")
    public Res_<CasheObject> getObject(@PathVariable int id, @RequestBody CasheObject casheObject) {
        // 第一次调用会查询数据库,后续调用会返回缓存结果
        // 延时 5s
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Res_.ok(casheObject);
    }







}
