package com.wy.cybertodoadmin.service.demo.Impl;

import org.springframework.stereotype.Service;

import com.wy.cybertodoadmin.service.demo.xxlJobService;

/*******************************************************************
 * <pre></pre>
 * 
 * @文件名称： xxlJobServiceImpl.java
 * 
 * @包 路 径： com.wy.cybertodoadmin.service.demo.Impl
 * @Copyright：北京数字医信责任有限公司 (C) 2023 *
 * @Description:
 * @Version: V1.0 @Author： WY @Date：2023/9/6 11:09 @Modify：
 */
@Service
public class xxlJobServiceImpl implements xxlJobService {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(xxlJobServiceImpl.class);

    @Override
    public void xxlJob() {
        logger.info("xxlJobServiceImpl.xxlJob");
    }
}
