package com.wy.cybertodoadmin.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wy.cybertodoadmin.system.entity.account.SysUserCore;

/**
 * @author WangYu
 * @project cyber-todo
 * @description 用户核心表服务层
 * @date 2023/7/11 15:04:54
 */
public interface ISysUserCoreService extends IService<SysUserCore> {

    /**
     * 按用户名进行查询
     */
    SysUserCore selectByAccountName(String accountName);


}
