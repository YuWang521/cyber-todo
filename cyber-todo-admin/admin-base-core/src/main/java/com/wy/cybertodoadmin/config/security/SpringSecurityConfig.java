package com.wy.cybertodoadmin.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.wy.cybertodoadmin.config.security.encoder.SystemPasswordEncoder;
import com.wy.cybertodoadmin.config.security.endpoint.JsonAuthenticationEntryPoint;
import com.wy.cybertodoadmin.config.security.filter.CaptchaVerifyFilter;
import com.wy.cybertodoadmin.config.security.handler.*;
import com.wy.cybertodoadmin.config.security.strategy.JsonExpiredSessionStrategy;
import com.wy.cybertodoadmin.config.security.strategy.JsonInvalidSessionStrategy;

import jakarta.annotation.Resource;

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

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 自定义配置 SecurityFilterChain
     * 登录接口
     * swagger 接口文档不需要认证
     */
    private static final String[] AUTH_LIST =
        {"/xxl-job/**", "/druid/**", "/login/form", "/doc.html", "/doc.html/**", "/v3/api-docs/**",
            "/configuration/ui/**",
            "/swagger-resources/**", "/configuration/security/**", "/swagger-ui.html", "/webjars/**", "/swagger-ui/**",
            "/login/testCaptcha", "/oauth/auth/github"};

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
        http
            // 认证请求
            .authorizeHttpRequests(this::configureAuthorizeHttpRequests)
            // 通过所有认证，仅开发时使用
            //            .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
            // 会话管理
            .sessionManagement(this::configureSessionManagement)
            // 表单登录
            .formLogin(this::configureFormLogin)
            //          .formLogin(Customizer.withDefaults())
            // 未认证处理
            .exceptionHandling(this::configureExceptionHandling)
            // 注销登录
            .logout(this::configureLogout)
            // 记住我
            .rememberMe(Customizer.withDefaults())
            // HTTP Basic
            .httpBasic(Customizer.withDefaults())
            // 登录验证码过滤器， 添加在 UsernamePasswordAuthenticationFilter 之前
            .addFilterBefore(new CaptchaVerifyFilter(new JsonCaptchationFailureHandler(), stringRedisTemplate), UsernamePasswordAuthenticationFilter.class)
            // CSRF
            .csrf(AbstractHttpConfigurer::disable);
        //        ==================== OAuth2 ====================
        // oauth2
        http.oauth2Login(Customizer.withDefaults());
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
            .loginProcessingUrl("/login/form").permitAll() // 自定义登录处理URL
            //            .usernameParameter("username") // 自定义登录用户名参数名
            //            .passwordParameter("password") // 自定义登录密码参数名
            //            .defaultSuccessUrl("/index.html") // 登录成功后的跳转页面 重定向，地址不变
            //            .successForwardUrl("/index.html") // 登录成功后的跳转页面 转发，地址不变
            //            .failureUrl("/login.html?error")// 登录失败后的跳转页面 重定向，地址不变
            //            .failureForwardUrl("/login.html?error"); // 登录失败后的跳转页面 转发，地址不变
            // 自定义登录成功处理器
            .successHandler(new JsonAuthenticationSuccessHandler())
            // 自定义登录失败处理器
            .failureHandler(new JsonAuthenticationFailedHandler());
    }

    /**
     * 表单注销登录配置
     * spring security 默认的注销登录地址是 /logout
     * 注销接口支持多种请求方式，如 GET、POST、PUT、DELETE 等
     */
    private LogoutConfigurer<HttpSecurity> configureLogout(LogoutConfigurer<HttpSecurity> logout) {
        return logout
            // 自定义注销登录URL
            .logoutUrl("/logout")
            // 自定义注销登录成功处理器
            .logoutSuccessHandler(new JsonLogoutSuccessHandler())
            // 注销删除cookie
            .deleteCookies("SESSION");
    }

    /**
     * 配置认证处理 未认证，认证失败
     */
    private ExceptionHandlingConfigurer<HttpSecurity> configureExceptionHandling(ExceptionHandlingConfigurer<HttpSecurity> exceptionHandling) {
        return exceptionHandling
            // 未认证处理
            .authenticationEntryPoint(new JsonAuthenticationEntryPoint())
            // 认证失败处理
            .accessDeniedHandler(new JsonAccessDeniedHandler());
    }

    /**
     * 配置会话管理
     * 会话创建默认为IF_REQUIRED模式，即只有需要时才会创建会话
     * 会话的超时时间可以通过spring security的配置项server.servlet.session.timeout来配置
     *
     * @param sessionManagement 会话管理配置器
     * @return 会话管理配置器
     */
    private SessionManagementConfigurer<HttpSecurity> configureSessionManagement(SessionManagementConfigurer<HttpSecurity> sessionManagement) {
        return sessionManagement
            // 会话创建策略
            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            // 会话固定攻击保护
            .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::changeSessionId)
            // 会话失效策略
            .invalidSessionStrategy(new JsonInvalidSessionStrategy()).sessionAuthenticationFailureHandler(new JsonSessionAuthenticationFailureHandler())
            // 会话并发控制
            .sessionConcurrency(sessionConcurrency -> sessionConcurrency
                // 最大会话数
                .maximumSessions(1)
                // 异地登录
                .maxSessionsPreventsLogin(false)
                //  session过期策略
                .expiredSessionStrategy(new JsonExpiredSessionStrategy()));
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
