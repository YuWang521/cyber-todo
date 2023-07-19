package com.wy.cybertodoadmin.core.vo;

import com.wy.cybertodoadmin.core.constant.CommonConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 接口返回数据
 * @date 2023/7/13 15:50:20
 */
@Data
@Schema(description = "统一接口返回数据")
public class Res_<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    @Schema(name = "成功标志", description = "成功标志")
    private boolean success = true;

    /**
     * 返回处理消息
     */
    //    @ApiModelProperty(value = "返回处理消息")
    @Schema(name = "返回处理消息", description = "返回处理消息")
    private String message = "";

    /**
     * 返回代码
     */
    @Schema(name = "返回代码", description = "返回代码")
    private Integer code = 0;

    /**
     * 返回数据对象 data
     */
    @Schema(name = "返回数据对象", description = "返回数据对象")
    private T result;

    /**
     * 时间戳
     */
    @Schema(name = "时间戳", description = "时间戳")
    private long timestamp = System.currentTimeMillis();

    public Res_() {
    }

    public Res_(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Res_<T> success(String message) {
        this.message = message;
        this.code = CommonConstant.RES_OK;
        this.success = true;
        return this;
    }

    public static <T> Res_<T> ok() {
        Res_<T> r = new Res_<T>();
        r.setSuccess(true);
        r.setCode(CommonConstant.RES_OK);
        return r;
    }

    public static <T> Res_<T> ok(T data) {
        Res_<T> r = new Res_<T>();
        r.setSuccess(true);
        r.setCode(CommonConstant.RES_OK);
        r.setResult(data);
        return r;
    }

    public static <T> Res_<T> ok(String msg) {
        Res_<T> r = new Res_<T>();
        r.setSuccess(true);
        r.setCode(CommonConstant.RES_OK);
        //Result OK(String msg)方法会造成兼容性问题 issues/I4IP3D
        r.setResult((T)msg);
        r.setMessage(msg);
        return r;
    }

    public static <T> Res_<T> error(String msg, T data) {
        Res_<T> r = new Res_<T>();
        r.setSuccess(false);
        r.setCode(CommonConstant.RES_FAIL);
        r.setMessage(msg);
        r.setResult(data);
        return r;
    }

    public static <T> Res_<T> error(String msg) {
        return error(CommonConstant.RES_FAIL, msg);
    }

    public static <T> Res_<T> error(int code, String msg) {
        Res_<T> r = new Res_<T>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

}
