package com.wy.cybertodoadmin.config.security;

import com.wy.cybertodoadmin.config.security.encoder.SystemPasswordEncoder;
import com.wy.cybertodoadmin.config.security.handler.JsonAuthenticationFailedHandler;
import com.wy.cybertodoadmin.config.security.handler.JsonAuthenticationSuccessHandler;
import com.wy.cybertodoadmin.config.security.handler.JsonLogoutSuccessHandler;
import com.wy.cybertodoadmin.config.security.strategy.JsonInvalidSessionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
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
     * 自定义配置 SecurityFilterChain
     * 登录接口
     * swagger 接口文档不需要认证
     */
    private static final String[] AUTH_LIST = {
        "/login",
        "/doc.html",
        "/doc.html/**",
        "/v3/api-docs/**",
        "/configuration/ui/**",
        "/swagger-resources/**",
        "/configuration/security/**",
        "/swagger-ui.html",
        "/webjars/**"
    };

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
            return new SystemPasswordEncoder();
        }

    /***
     * 自定义监听器
     * 用于对会话进行并发控制
     * @return HttpSessionEventPublisher
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    /**
     * 自定义配置 SecurityFilterChain 用户名密码表单登录
     * <p>
     * 1. 配置表单登录项
     * 2. 配置注销登录项
     * 3. 配置记住账号项
     * 4. 配置HTTP Basic项
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(this::configureAuthorizeHttpRequests)
            .sessionManagement(this::configureSessionManagement)
            .formLogin(this::configureFormLogin).logout(this::configureLogout).rememberMe(Customizer.withDefaults()).httpBasic(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    /**
     * 配置认证Http请求
     * 允许可以直接访问的Http请求
     *
     * @param authorize Http请求授权配置器
     */
    private void configureAuthorizeHttpRequests(
        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorize) {
        AntPathRequestMatcher[] antPathRequestMatchers = new AntPathRequestMatcher[AUTH_LIST.length];
        for (int i = 0; i < AUTH_LIST.length; i++) {
            antPathRequestMatchers[i] = new AntPathRequestMatcher(AUTH_LIST[i]);
        }
        authorize.requestMatchers(antPathRequestMatchers).permitAll().anyRequest().authenticated();
//        authorize.requestMatchers(new AntPathRequestMatcher("/v3/api-docs")).permitAll().anyRequest().authenticated();
//        authorize.requestMatchers(new AntPathRequestMatcher("/configuration/ui")).permitAll().anyRequest().authenticated();
//        authorize.requestMatchers(new AntPathRequestMatcher("/swagger-resources/**")).permitAll().anyRequest().authenticated();
//        authorize.requestMatchers(new AntPathRequestMatcher("/configuration/security")).permitAll().anyRequest().authenticated();
//        authorize.requestMatchers(new AntPathRequestMatcher("/swagger-ui.html")).permitAll().anyRequest().authenticated();
//        authorize.requestMatchers(new AntPathRequestMatcher("/webjars/**")).permitAll().anyRequest().authenticated();
    }

    /**
     *  配置表单登录项
     * @param formLogin 表单登录配置器
     * @return 表单登录配置器
     */
    private FormLoginConfigurer<HttpSecurity> configureFormLogin(FormLoginConfigurer<HttpSecurity> formLogin) {
        return formLogin
            //            .loginPage("/login.html") // 自定义登录页面（注意要同步配置loginProcessingUrl）,如果和ProcessingUrl相同，会导致死循环
            //            .loginProcessingUrl("/login").permitAll() // 自定义登录处理URL
            //            .usernameParameter("username") // 自定义登录用户名参数名
            //            .passwordParameter("password") // 自定义登录密码参数名
            //            .defaultSuccessUrl("/index.html") // 登录成功后的跳转页面 重定向，地址不变
            //            .successForwardUrl("/index.html") // 登录成功后的跳转页面 转发，地址不变
            //            .failureUrl("/login.html?error")// 登录失败后的跳转页面 重定向，地址不变
            //            .failureForwardUrl("/login.html?error"); // 登录失败后的跳转页面 转发，地址不变
            .successHandler(new JsonAuthenticationSuccessHandler()).failureHandler(new JsonAuthenticationFailedHandler());
    }

    /**
     * 表单注销登录配置
     * spring security 默认的注销登录地址是 /logout
     * 注销接口支持多种请求方式，如 GET、POST、PUT、DELETE 等
     */
    private LogoutConfigurer<HttpSecurity> configureLogout(LogoutConfigurer<HttpSecurity> logout) {
        return logout.logoutUrl("/logout").logoutSuccessHandler(new JsonLogoutSuccessHandler())
            .deleteCookies("JSESSIONID");
    }

    /**
     * 配置会话管理
     * 会话创建默认为IF_REQUIRED模式，即只有需要时才会创建会话
     * 会话的超时时间可以通过spring security的配置项server.servlet.session.timeout来配置
     * @param sessionManagement 会话管理配置器
     * @return 会话管理配置器
     */
        private SessionManagementConfigurer<HttpSecurity> configureSessionManagement(SessionManagementConfigurer<HttpSecurity> sessionManagement) {
            return sessionManagement.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionStrategy(new JsonInvalidSessionStrategy());
        }

    // Bascie认证
    //    private HttpBasicConfigurer<HttpSecurity> configureHt tpBasic(HttpBasicConfigurer<HttpSecurity> httpBasic) {
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
