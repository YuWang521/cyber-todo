package com.wy.cybertodoadmin.system.entity.account;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 用户核心表实体
 * @date 2023/7/11 15:11:05
 */
@Data
@TableName("sys_user_core")
public class SysUserCore implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // id
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    // account_name
    private String accountName;

    // account_name
    private String accountPwd;

    // account_salt
    private String accountSalt;

    // account_email
    private String accountEmail;

    // account_phone
    private String accountPhone;

    // account_avatar
    private String accountAvatar;

    // is_lock 默认为true

    private Boolean isLock;




}
