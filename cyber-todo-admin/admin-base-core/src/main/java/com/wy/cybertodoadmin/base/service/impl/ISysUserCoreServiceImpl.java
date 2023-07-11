package com.wy.cybertodoadmin.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.cybertodoadmin.base.mapper.SysUserCoreServiceMapper;
import com.wy.cybertodoadmin.base.service.ISysUserCoreService;
import com.wy.cybertodoadmin.system.entity.account.SysUserCore;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author WangYu
 * @project cyber-todo
 * @description
 * @date 2023/7/11 15:09:04
 */
@Slf4j
@Service
public class ISysUserCoreServiceImpl extends ServiceImpl<SysUserCoreServiceMapper, SysUserCore> implements ISysUserCoreService {


    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public SysUserCore selectByAccountName(String accountName) {
        LambdaUpdateWrapper<SysUserCore> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(SysUserCore::getAccountName, accountName);
        return this.getOne(lambdaUpdateWrapper);
    }

}
