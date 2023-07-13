package com.wy.cybertodoadmin.base.constant;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 通用常量
 * @date 2023/7/4 21:46:36
 */
public interface CommonConstant {

    /**
     * 操作日志
     */
    int OperateLog = 0;

    /**
     * 登录日志
     */
    int LoginLog = 1;

    /**
     * 定时任务日志
     */
    int ScheduleLog = 2;

    // ======================== 通用常量 ===========================
    String STRING_NULL = "null";

    // ======================== 配置信息常量 ===========================

    /**
     * 登录密码 算法前缀 {bcrypt}
     */
    String LOGIN_PASSWORD_PREFIX = "{bcrypt}";

    /**
     * 系统数据库加密密码和盐值 HashSalt
     */
    int HASH_SALT_LENGTH = 74;

    // ======================== 标识符，分隔符 ===========================

    /**
     * 哈希值，盐值分隔符
     */
    String HASH_SALT_SEPARATOR = "*";

    /**
     * 分隔符表达式
     */
    String SPLIT_EXPRESSION = "\\*";

}
