package com.wy.cybertodoadmin.config.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 认证失败JSON处理
 * @date 2023/7/18 10:41:19
 */
public class JsonAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
        throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8"); // 返回JSON
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);  // 状态码 403
        response.getWriter().write("{\"code\":403,\"msg\":\"认证失败\"}");
    }
}
