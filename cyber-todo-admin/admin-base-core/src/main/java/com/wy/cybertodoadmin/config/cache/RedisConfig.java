package com.wy.cybertodoadmin.config.cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangYu
 * @project cyber-todo
 * @description
 * @date 2023/7/6 17:23:04
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

}
