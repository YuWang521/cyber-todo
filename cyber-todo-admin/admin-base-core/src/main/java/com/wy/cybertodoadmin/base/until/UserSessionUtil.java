package com.wy.cybertodoadmin.base.until;

import com.wy.cybertodoadmin.system.entity.account.SystemUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author WangYu
 * @project cyber-todo
 * @description
 * @date 2023/7/13 14:01:42
 */
public class UserSessionUtil {

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户实体
     */
    public static SystemUserDetails getUserDetails() {
        return (SystemUserDetails) getAuthentication().getPrincipal();
    }

    /**
     * 获取用户ID
     */
    public static String getUserId() {
        return getUserDetails().getId();
    }




}
