//package com.wy.cybertodoadmin.base.service.impl;
//
//import com.wy.cybertodoadmin.system.entity.account.SysUserCore;
//import com.wy.cybertodoadmin.system.entity.account.SystemUserDetails;
//import jakarta.annotation.Resource;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.security.KeyPair;
//import java.util.List;
//
///**
// * @author WangYu
// * @project cyber-todo
// * @description 系统统一认证用户信息账户实现类
// * @date 2023/7/11 15:01:41
// */
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Resource
//    private ISysUserCoreServiceImpl sysUserCoreService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 数据库查询
//        SysUserCore sysUserCore = sysUserCoreService.selectByAccountName(username);
//        if (sysUserCore == null) {
//            throw new UsernameNotFoundException("用户不存在");
//        }else{
//            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
//            return new SystemUserDetails(sysUserCore.getAccountName(),sysUserCore.getAccountPwd(),sysUserCore.getAccountPhone(),authorityList,true,true,true,true);
//
//        }
//
//
//    }
//
//
//
//
//
//}
