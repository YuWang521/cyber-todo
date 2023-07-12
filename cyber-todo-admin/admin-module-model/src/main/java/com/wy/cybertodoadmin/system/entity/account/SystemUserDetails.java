package com.wy.cybertodoadmin.system.entity.account;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 系统实现security的UserDetails的自定义拓展实现类
 * @date 2023/7/11 14:52:03
 */
@Data
@Slf4j
public class SystemUserDetails implements UserDetails {

    private String password;
    private final String username;
    private final String phone; // 扩展字段，手机号放入用户信息中
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public SystemUserDetails( String username,String password, String phone, List<GrantedAuthority> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.password = password;
        this.phone = phone;
        this.username = username;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities)); // 非空判断+排序
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet(new SystemUserDetails.AuthorityComparator());
        for (GrantedAuthority grantedAuthority : authorities) {
//            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = 600L;
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            } else {
                return g1.getAuthority() == null ? 1 : g1.getAuthority().compareTo(g2.getAuthority());
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password; // 密码不返回
    }

    @Override
    public String getUsername() {
        return this.username; // 用户名返回
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired; // 账户是否过期
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked; // 账户是否锁定
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired; // 密码是否过期
    }

    @Override
    public boolean isEnabled() {
        return this.enabled; // 账户是否可用
    }
}
