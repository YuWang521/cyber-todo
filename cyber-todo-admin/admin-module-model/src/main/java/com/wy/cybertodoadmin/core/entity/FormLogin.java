package com.wy.cybertodoadmin.core.entity;

import lombok.Data;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 表单登录请求类
 * @date 2023/7/19 18:28:08
 */
@Data
public class FormLogin {

    private String username;
    private String password;
    private String captcha;
    private String captchaId;

}
