package com.wy.cybertodoadmin.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author WangYu
 * @project cyber-todo
 * @description Spring Security 6.0 之前的版本，需要实现 WebSecurityConfigurerAdapter 类，重写 configure 方法，来实现自定义的安全策略。
 * Spring Security 6.0 之后的版本，需要实现 SecurityFilterChain 接口，重写 filter 方法，来实现自定义的安全策略。
 * Spring Security 6.0 版本中已经删除了 WebSecurityConfigurerAdapter 类，所以我们需要实现 SecurityFilterChain 接口来实现自定义的安全策略。
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
     *
     * @return 密码加密器
     */
    @Bean
    PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义配置 SecurityFilterChain
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //        // 配置所有的Http请求必须认证
        //        http.authorizeHttpRequests()
        //            .requestMatchers(new AntPathRequestMatcher("/login.html")).permitAll()
        //            .anyRequest().authenticated();
        //        // 开启表单登录
        //        http.formLogin()
        //            .loginPage("/login.html") // 自定义登录页面（注意要同步配置loginProcessingUrl）
        //            .loginProcessingUrl("/login"); // 自定义登录处理URL
        //        // 开启Basic认证
        //        http.httpBasic();
        //        // 关闭 CSRF
        //        http.csrf().disable();
        //        return http.build();
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/login.html").permitAll().anyRequest().authenticated())
            .formLogin(this::configureFormLogin)
            .rememberMe(Customizer.withDefaults()).httpBasic(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    // 配置表单登录项
    private FormLoginConfigurer<HttpSecurity> configureFormLogin(FormLoginConfigurer<HttpSecurity> formLogin) {
        return formLogin.loginPage("/login.html") // 自定义登录页面（注意要同步配置loginProcessingUrl）,如果和ProcessingUrl相同，会导致死循环
            .loginProcessingUrl("/login").permitAll() // 自定义登录处理URL
            .usernameParameter("username") // 自定义登录用户名参数名
            .passwordParameter("password") // 自定义登录密码参数名
            .defaultSuccessUrl("/index.html") // 登录成功后的跳转页面 重定向，地址不变
//            .successForwardUrl("/index.html") // 登录成功后的跳转页面 转发，地址不变
            .failureUrl("/login.html?error"); // 登录失败后的跳转页面 重定向，地址不变
//            .failureForwardUrl("/login.html?error"); // 登录失败后的跳转页面 转发，地址不变
    }

    // Bascie认证
//    private HttpBasicConfigurer<HttpSecurity> configureHttpBasic(HttpBasicConfigurer<HttpSecurity> httpBasic) {
//        return httpBasic.realmName("my-basic-realm");
//    }



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
