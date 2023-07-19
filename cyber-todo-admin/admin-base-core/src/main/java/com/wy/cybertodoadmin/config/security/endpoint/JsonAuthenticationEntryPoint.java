package com.wy.cybertodoadmin.config.security.endpoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 未登录认证JSON处理
 * @date 2023/7/18 10:22:53
 */
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        setJsonResponse(response);
    }

    /**
     * 设置JSON响应
     */
    private static void setJsonResponse(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8"); // 返回JSON
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 状态码 401
        response.getWriter().write("{\"code\":401,\"msg\":\"未登录认证\"}");
    }
}
