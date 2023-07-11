package com.wy.cybertodoadmin.config.security.handler;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 自定义认证成功后返回JSON
 * @date 2023/7/12 14:52:30
 */
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * 登录成功后直接返回JSON
     * @param request 请求
     * @param response 响应
     * @param authentication 认证
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8"); // 返回JSON
        response.setStatus(HttpStatus.OK.value());  // 状态码 200
        Map<String, Object> result = new HashMap<>(); // 返回结果
        result.put("msg", "登录成功");
        result.put("code", 200);
        result.put("data", authentication);
        String jsonString = JSON.toJSONString(result);
        response.getWriter().write(jsonString);
    }
}
