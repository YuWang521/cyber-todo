package com.wy.cybertodoadmin.service.system.login;

import com.wy.cybertodoadmin.core.vo.CaptchaVO;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 统一认证登录-表单服务层api
 * @date 2023/7/19 17:59:43
 */
public interface ISysLoginFormLoginService {

    // 验证码服务
    CaptchaVO captcha();

}
