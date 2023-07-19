package com.wy.cybertodoadmin.core.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 验证码POJO类
 * @date 2023/7/13 15:40:45
 */
@Data
@Schema(description = "验证码POJO类")
public class CaptchaVO implements Serializable {

    // 唯一id
    private String uuid;

    // 验证码图片
    private String base64Img;

}
