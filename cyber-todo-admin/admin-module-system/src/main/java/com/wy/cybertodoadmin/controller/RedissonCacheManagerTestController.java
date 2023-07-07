package com.wy.cybertodoadmin.controller;

import com.wy.cybertodoadmin.base.aspect.annotation.SystemLog;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangYu
 * @project cyber-todo
 * @description
 * @date 2023/7/7 11:52:53
 */
@RestController
@RequestMapping("/rediscache")
@CacheConfig(cacheNames = "testCache")
public class RedissonCacheManagerTestController {

    /**
     * 使用 @Cacheable 等注解实现的缓存逻辑,
     * 本质是通过请求参数作为缓存键,判断缓存中是否存在对应的数据,
     * 如果存在则直接返回缓存值,否则执行方法体并将结果缓存。
     * @param id 请求参数
     * @return 返回值
     */
    @Cacheable("products")
    @SystemLog(logType = 0, value = "redisson cache test api")
    @GetMapping("/String/{id}")
    public int getProduct(@PathVariable int id) {
        // 第一次调用会查询数据库,后续调用会返回缓存结果
        // 延时 5s
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (int)id;
    }

    /**
     * 缓存对象
     *
     */







}
