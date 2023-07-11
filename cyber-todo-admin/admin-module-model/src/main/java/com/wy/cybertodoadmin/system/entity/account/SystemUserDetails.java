package com.wy.cybertodoadmin.system.entity.account;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 系统实现security的UserDetails的自定义拓展实现类
 * @date 2023/7/11 14:52:03
 */
@Data
@Slf4j
public class SystemUserDetails implements UserDetails {

    // 账户名 account_name
    private String accountName;

    // 密码 account_pwd
    private String accountPwd;

    // 盐 account_salt
    private String accountSalt;

    // 邮箱 account_email
    private String accountEmail;

    // 手机号 account_phone
    private String accountPhone;

    // 头像 account_avatar
    private String accountAvatar;

    // is_lock
    private Boolean isLock;

    public SystemUserDetails(String accountName, String accountPwd, String accountSalt, String accountEmail, String accountPhone, String accountAvatar, Boolean isLock) {
        this.accountName = accountName;
        this.accountPwd = accountPwd;
        this.accountSalt = accountSalt;
        this.accountEmail = accountEmail;
        this.accountPhone = accountPhone;
        this.accountAvatar = accountAvatar;
        this.isLock = isLock;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.accountPwd;
    }

    @Override
    public String getUsername() {
        return this.accountName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.isLock;
    }
}
