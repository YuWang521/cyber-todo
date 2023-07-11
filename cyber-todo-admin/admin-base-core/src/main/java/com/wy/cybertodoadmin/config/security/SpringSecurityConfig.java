package com.wy.cybertodoadmin.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author WangYu
 * @project cyber-todo
 * @description
 *  Spring Security 6.0 之前的版本，需要实现 WebSecurityConfigurerAdapter 类，重写 configure 方法，来实现自定义的安全策略。
 *  Spring Security 6.0 之后的版本，需要实现 SecurityFilterChain 接口，重写 filter 方法，来实现自定义的安全策略。
 *  Spring Security 6.0 版本中已经删除了 WebSecurityConfigurerAdapter 类，所以我们需要实现 SecurityFilterChain 接口来实现自定义的安全策略。
 * @date 2023/7/10 16:00:53
 */
@Configuration
@EnableWebSecurity(debug = false)
public class SpringSecurityConfig {

    /**
     * 密码加密器
     * 推荐顺序: BCryptPasswordEncoder > Pbkdf2PasswordEncoder > SCryptPasswordEncoder > StandardPasswordEncoder
     * bcrypt / scrypt :安全性最高,推荐用于密码加密
     * SHA-512 / SHA-256:安全性高于MD5,也是不错的选择
     * PBKDF2:折中选择,安全性能平衡
     * MD5:由于易受攻击不再推荐用于密码加密
     * @return 密码加密器
     */
    @Bean
    PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }





//
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests().anyRequest().authenticated();
//        http.formLogin();
//        http.httpBasic();
//        return http.build();
//    }
//
//    @Bean
//    WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().requestMatchers("/hello");
//    }



//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(authorize-> {
//                try {
//                    authorize
//                        // 放行登录接口
//                        .requestMatchers("/api/auth/login").permitAll()
//                        .requestMatchers("/api/auth/logout").permitAll()
//                        .requestMatchers("/api/auth/refreshToken").permitAll()
//                        .requestMatchers("doc.html#/home").permitAll()
//                        // 放行资源目录
//                        .requestMatchers("/static/**", "/resources/**").permitAll()
//                        // 其余的都需要权限校验
//                        .anyRequest().authenticated();
//                        // 防跨站请求伪造
////                        .and().csrf(AbstractHttpConfigurer::disable);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        ).build();
//    }

}
