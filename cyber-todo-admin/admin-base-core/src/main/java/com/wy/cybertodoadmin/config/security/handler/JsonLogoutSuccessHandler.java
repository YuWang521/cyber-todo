package com.wy.cybertodoadmin.config.security.handler;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 自定义注销登录返回JSON
 * @date 2023/7/12 16:26:44
 */
public class JsonLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 被下线用户点击注销
        String data = "null";
        if (authentication != null) {
            data = authentication.getName();
        }
        response.setContentType("application/json;charset=utf-8"); // 返回JSON
        response.setStatus(HttpStatus.OK.value());  // 状态码 200
        Map<String, Object> result = new HashMap<>(); // 返回结果
        result.put("msg", "注销成功");
        result.put("code", 200);
        result.put("data", data);
        response.getWriter().write(JSON.toJSONString(result));
    }
}
