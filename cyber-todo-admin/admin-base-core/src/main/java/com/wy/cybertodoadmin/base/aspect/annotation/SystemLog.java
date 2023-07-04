package com.wy.cybertodoadmin.base.aspect.annotation;

import com.wy.cybertodoadmin.base.constant.CommonConstant;

import java.lang.annotation.*;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 系统日志注解
 * @date 2023/7/4 21:43:07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    /**
     * 日志类型
     *
     * @return 0:操作日志;1:登录日志;2:定时任务;
     */
    int logType() default CommonConstant.OperateLog;

    /**
     * 日志内容
     *
     * @return 日志内容
     */
    String value() default "";

}
