package com.wy.cybertodoadmin.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 使用redis索引解决集群会话并发控制
 * @date 2023/7/17 14:37:28
 */
@Configuration
@RequiredArgsConstructor
@EnableRedisIndexedHttpSession // 开启支持索引保存会话
public class SpringSessionBackedSessionRegistryConfig {

    // @EnableRedisIndexedHttpSession 会自动注册 FindByIndexNameSessionRepository
    // 注入即可
    private final FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    // 使用SpringSessionBackedSessionRegistry，会自动替换默认的 SessionRegistry
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry<>(sessionRepository);
    }

}
