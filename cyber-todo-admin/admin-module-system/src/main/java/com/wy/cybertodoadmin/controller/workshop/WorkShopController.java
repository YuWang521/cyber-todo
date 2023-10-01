package com.wy.cybertodoadmin.controller.workshop;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*******************************************************************
 * <pre></pre>
 * @文件名称： WorkShopController.java
 * @包 路  径： com.wy.cybertodoadmin.controller.workshop
 * @Copyright：北京数字医信责任有限公司 (C) 2023 *
 * @Description:
 * @Version: V1.0
 * @Author： wy
 * @Date：2023/10/1 14:40
 * @Modify：
 */
@RestController
@RequestMapping("/workshop")
@Tag(name = "模版工坊API")
public class WorkShopController {
    // TODO: 2023/10/1

    @PostMapping("/service-status")
    @Tag(name = "模版工坊API-服务状态变更")
    public void serviceStatus(){

    }

    @PostMapping("/template-sort")
    @Tag(name = "模版工坊API-模版排序")
    public void templateSort(){

    }

}
