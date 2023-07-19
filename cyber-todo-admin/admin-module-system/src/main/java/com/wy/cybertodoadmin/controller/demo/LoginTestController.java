package com.wy.cybertodoadmin.controller.demo;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import com.wy.cybertodoadmin.core.constant.CommonConstant;
import com.wy.cybertodoadmin.core.vo.CaptchaVO;
import com.wy.cybertodoadmin.core.vo.Res_;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 统一认证登录测试控制器
 * @date 2023/7/13 15:43:53
 */
@RestController
@RequestMapping("/login")
@Slf4j
@Tag(name = "功能测试模块-统一认证登录测试API")
public class LoginTestController {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Operation(summary = "统一认证登录测试API - 测试验证码")
    @GetMapping("/testCaptcha")
    @ResponseBody
    public Res_<CaptchaVO> testCaptcha(HttpServletRequest request) {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        String code = lineCaptcha.getCode();// 验证码
        log.info("生成验证码：" + lineCaptcha.getCode());
        String imageBase64 = lineCaptcha.getImageBase64Data(); // 验证码图片BASE64
        // 创建验证码对象
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setUuid(UUID.randomUUID().toString());
        captchaVO.setBase64Img(imageBase64);
        // 缓存验证码，4分钟有效
        stringRedisTemplate.opsForValue()
            .set(CommonConstant.CAPTCHA_NAMESPACE + ":" + captchaVO.getUuid(), code, CommonConstant.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        return Res_.ok(captchaVO);
    }

    // 测试通过session-id进行下线操作
    @Operation(summary = "统一认证登录测试API - 测试下线操作")
    @GetMapping("/testLogout")
    @ResponseBody
    public Res_ testLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return Res_.ok();
    }

}
