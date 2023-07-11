package com.wy.cybertodoadmin.base.service;

import com.wy.cybertodoadmin.CyberTodoAdminApplication;
import com.wy.cybertodoadmin.system.entity.account.SysUserCore;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author WangYu
 * @project cyber-todo
 * @description
 * @date 2023/7/11 16:15:40
 */
@SpringBootTest(classes = CyberTodoAdminApplication.class)
class ISysUserCoreServiceTest {

    @Resource
    private ISysUserCoreService sysUserCoreService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void insert() {
        SysUserCore sysUserCore = new SysUserCore();
        // 前端传递过来的用户的密码使用

        // 解码后获取到用户信息
        String pwd = "123456";
        String encode = bCryptPasswordEncoder.encode(pwd);
        // 将encode $2a$10$q62nokLdayPAuloJqoXMvugHgTdLV2xHaSM7WZQQNs17uziEdyEVq   拆分为密码和盐
        // 将$2a$10$q62nokLdayPAuloJqoXMvugHgTdLV2xHaSM7WZQQNs17uziEdyEVq 拆分为 $2a$10$后八位 和 ayPAuloJqoXMvugHgTdLV2xHaSM7WZQQNs17uziEdyEVq




        sysUserCore.setAccountName("admin");
        sysUserCore.setAccountPwd("admin");
        sysUserCore.setIsLock(false);

        sysUserCoreService.save(sysUserCore);
    }

}