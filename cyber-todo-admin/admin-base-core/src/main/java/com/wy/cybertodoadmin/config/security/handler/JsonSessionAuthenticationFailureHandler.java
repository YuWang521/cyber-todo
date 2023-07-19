package com.wy.cybertodoadmin.config.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * @author WangYu
 * @project cyber-todo
 * @description session认证失败处理器
 * @date 2023/7/18 11:11:54
 */
public class JsonSessionAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
        throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8"); // 返回JSON
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 状态码 401
        response.getWriter().write("{\"code\":401,\"msg\":\"session认证异常\"}");
    }
}
