package com.wy.cybertodoadmin.jobhandler;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wy.cybertodoadmin.service.demo.xxlJobService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;

import jakarta.annotation.Resource;

/*******************************************************************
 * <pre></pre>
 * 
 * @文件名称： DemoXXlJob.java
 * 
 * @包 路 径： com.wy.cybertodoadmin.base.jobhandler
 * @Copyright：北京数字医信责任有限公司 (C) 2023 *
 * @Description:
 * @Version: V1.0 @Author： WY @Date：2023/9/6 10:33 @Modify：
 */
@Component
public class DemoXXlJob {
    private static Logger logger = LoggerFactory.getLogger(DemoXXlJob.class);

    @Resource
    private xxlJobService xxlJobService;

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoHandler")
    public void demoJobHandler() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");

        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(1);
        }
        logger.info(
            "beat at:" + XxlJobHelper.getJobId() + "  " + XxlJobHelper.getJobId() + "  " + XxlJobHelper.getJobParam());
        System.out.println(
            "beat at:" + XxlJobHelper.getJobId() + "  " + XxlJobHelper.getJobId() + "  " + XxlJobHelper.getJobParam());
        // default success
        xxlJobService.xxlJob();
    }
}
