package com.wy.cybertodoadmin.controller.demo.xxljob;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wy.cybertodoadmin.base.aspect.annotation.SystemLog;
import com.wy.cybertodoadmin.core.vo.Res_;
import com.wy.cybertodoadmin.jobhandler.DemoXXlJob;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;

/*******************************************************************
 * <pre></pre>
 * 
 * @文件名称： TestJobController.java
 * 
 * @包 路 径： com.wy.cybertodoadmin.controller.demo.xxljob
 * @Copyright：北京数字医信责任有限公司 (C) 2023 *
 * @Description:
 * @Version: V1.0 @Author： WY @Date：2023/9/5 17:41 @Modify：
 */
@RestController
@RequestMapping("/xxl-job")
@Tag(name = "xxl-job模块-测试 ")
public class TestJobController {

    @Resource
    private DemoXXlJob demoXXlJob;

    @GetMapping("/test")
    @Operation(summary = "测试 xxl-job 单次执行 - 保存")
    @SystemLog(logType = 0, value = "xxl-job test api")
    public Res_<String> save(String name) {

        try {
            demoXXlJob.demoJobHandler();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Res_.ok("name: " + name + " save success");
    }

}
