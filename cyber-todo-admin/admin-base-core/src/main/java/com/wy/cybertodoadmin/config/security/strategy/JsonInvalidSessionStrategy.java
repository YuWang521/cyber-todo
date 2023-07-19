package com.wy.cybertodoadmin.config.security.strategy;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.InvalidSessionStrategy;

import java.io.IOException;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 会话失效JSON返回失效策略
 * @date 2023/7/12 17:25:26
 */
public class JsonInvalidSessionStrategy implements InvalidSessionStrategy {
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8"); // 返回JSON
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 状态码 401
        response.getWriter().write("{\"code\":401,\"msg\":\"无会话记录或会话失效\"}");
    }
}
