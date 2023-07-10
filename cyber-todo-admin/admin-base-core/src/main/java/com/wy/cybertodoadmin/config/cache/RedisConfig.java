package com.wy.cybertodoadmin.config.cache;

import com.alibaba.fastjson2.support.spring.data.redis.FastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.util.Arrays;

/**
 * @author WangYu
 * @project cyber-todo
 * @description
 * @date 2023/7/6 17:23:04
 */
@Configuration
@EnableCaching
@Slf4j
// 在Spring Boot 3中使用Redisson作为缓存提供者,是不需要继承CachingConfigurerSupport类
public class RedisConfig{

    /**
     * 在Spring Boot 3中,RedisTemplate和Redisson都是可用的。
     * 但二者在功能和用法上有较大差异。RedisTemplate更加原生,Redisson提供了更高级的功能。
     * 在Spring Boot应用中配置了Redisson,而没有显式配置RedisTemplate,是可以使用RedisTemplate的。
     * <p>
     * Redisson在启动时,会自动根据配置创建RedisConnectionFactory并注册到Spring容器中。
     * Spring Data Redis会自动检测到这个RedisConnectionFactory,并创建一个默认的RedisTemplate注入到容器中。
     * 所以,在Spring Boot应用中,Redisson和RedisTemplate是可以同时使用的。
     * <p>
     * 注入默认创建的RedisTemplate，因此通过RedisConfig对RedisTemplate和Redisson进行配置
     */
    @Value("${redisson.singleServerConfig.masterName}")
    private String masterName;

    // 有坑，这里的host是redis的host，不是sentinel的host，同时后续的配置逻辑也需要修改
    @Value("${redisson.singleServerConfig.host}")
    private String host;

    @Value("${redisson.singleServerConfig.address}")
    private String address;


    @Value("${redisson.singleServerConfig.port}")
    private String password;

    @Value("${redisson.singleServerConfig.password}")
    private String sentinel;

    @Value("${redisson.singleServerConfig.database}")
    private int database;

    /**
     * 单机模式 redisson 客户端工厂
     * @param config 单机模式配置
     * @return RedisConnectionFactory
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory(Config config) {
        return new RedissonConnectionFactory(config);
    }

    /**
     * 单机模式 redisson 客户端
     * @param config 单机模式配置
     * @return RedissonClient
     */
    @Bean
    public RedissonClient redissonClient(Config config) {
        config = this.getConfig();
        return Redisson.create(config);
    }

    /**
     * casheManager
     * redisson-spring-boot-starter只提供 RedissonSpringCacheManager,而不是 RedisCacheManager。
     * 使用时注意注解一定要与该方法名相同
     * 例： @Cacheable(cacheManager="cacheManager")
     */
    @Bean
    public CacheManager cacheManager(RedissonClient redisson) {
        return new RedissonSpringCacheManager(redisson);
    }

    /**
     * 序列化方式
     * @param connectionFactory redis连接工厂
     * @return RedisTemplate
     *  RedissonClient 内部也使用 RedisTemplate 来连接 Redis 服务器,
     *  所以如果配置了 RedisTemplate 的序列化器,
     *  RedissonClient 在序列化缓存对象时也会使用这个序列化器
     */
    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate<Object, Object> redisTemplate(@Qualifier("redisConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

//        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);
        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
    @Bean
    public Config getConfig() {
        Config config = new Config();
        if ("true".equals(this.sentinel)){
            SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
            sentinelServersConfig.setDatabase(database).setPassword(password).setMasterName(masterName).setSentinelAddresses(Arrays.asList(host.split(",")));
        }else{
            SingleServerConfig singleServerConfig = config.useSingleServer();
            singleServerConfig.setDatabase(database).setAddress(address);

        }
        return config;
    }

}
