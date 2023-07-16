package com.wy.cybertodoadmin.core.vo;

import java.io.Serializable;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 验证码POJO类
 * @date 2023/7/13 15:40:45
 */
public class CaptchaVO implements Serializable {

    // 唯一id
    private String uuid;

    // 验证码图片
    private String base64Img;

}
