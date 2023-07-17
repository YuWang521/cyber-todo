package com.wy.cybertodoadmin.config.security.strategy;

import jakarta.servlet.ServletException;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 会话过期JSON返回过期策略
 * @date 2023/7/17 15:02:39
 */
public class JsonExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        event.getResponse().setContentType("application/json;charset=utf-8"); // 返回JSON
        event.getResponse().setStatus(401);  // 状态码 401
        event.getResponse().getWriter().write("{\"code\":401,\"msg\":\"会话过期\"}");
    }
}
