package com.wy.cybertodoadmin.service.system.login.Impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import com.wy.cybertodoadmin.core.constant.CommonConstant;
import com.wy.cybertodoadmin.core.vo.CaptchaVO;
import com.wy.cybertodoadmin.service.system.login.ISysLoginFormLoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author WangYu
 * @project cyber-todo
 * @description
 * @date 2023/7/19 18:01:46
 */
@Service
@Slf4j
public class ISysLoginFormLoginServiceImpl implements ISysLoginFormLoginService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public CaptchaVO captcha() {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = createLineCaptcha();
        String code = lineCaptcha.getCode();// 验证码
        log.info("生成验证码：" + code);
        String imageBase64 = lineCaptcha.getImageBase64Data(); // 验证码图片BASE64
        // 创建验证码对象
        CaptchaVO captchaVO = createCaptcha(imageBase64);
        // 缓存验证码，4分钟有效
        cacheCaptcha(captchaVO.getUuid(), code);
        return captchaVO;
    }

    // 生成验证码工具类
    private LineCaptcha createLineCaptcha() {
        // 定义图形验证码的长和宽
        return CaptchaUtil.createLineCaptcha(200, 100);
    }

    // 创建验证码对象
    private CaptchaVO createCaptcha(String imageBase64) {
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setUuid(UUID.randomUUID().toString());
        captchaVO.setBase64Img(imageBase64);
        return captchaVO;
    }

    // 缓存验证码
    private void cacheCaptcha(String uuid, String code) {
        stringRedisTemplate.opsForValue().set(CommonConstant.CAPTCHA_NAMESPACE + ":" + uuid, code, CommonConstant.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
    }

}
