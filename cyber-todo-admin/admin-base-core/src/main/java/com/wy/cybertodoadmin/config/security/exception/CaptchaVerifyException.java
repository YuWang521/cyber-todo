package com.wy.cybertodoadmin.config.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 验证码验证失败异常类
 * @date 2023/7/17 18:11:30
 */
public class CaptchaVerifyException extends AuthenticationException {
    public CaptchaVerifyException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CaptchaVerifyException(String msg) {
        super(msg);
    }
}
