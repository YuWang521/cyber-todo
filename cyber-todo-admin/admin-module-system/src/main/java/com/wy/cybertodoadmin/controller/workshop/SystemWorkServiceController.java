package com.wy.cybertodoadmin.controller.workshop;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*******************************************************************
 * <pre></pre>
 * @文件名称： SystemWorkServiceController.java
 * @包 路  径： com.wy.cybertodoadmin.controller.workshop
 * @Copyright：北京数字医信责任有限公司 (C) 2023 *
 * @Description:
 * @Version: V1.0
 * @Author： wy
 * @Date：2023/10/1 14:41
 * @Modify：
 */
@RestController
@RequestMapping("/workshop/system")
@Tag(name = "系统模版工坊API")
public class SystemWorkServiceController extends WorkShopController{
    // TODO: 2023/10/1

    @PostMapping("/list")
    @Tag(name = "系统模版工坊API-工坊模版管理列表")
    public void list(){

    }

    @PostMapping("/client-list")
    @Tag(name = "系统模版工坊API-客户检索列表")
    public void clientList(){

    }

    @PostMapping("/client-add-list")
    @Tag(name = "系统模版工坊API-客户模版待发布列表")
    public void clientAddList(){

    }

    @PostMapping("/template-add")
    @Tag(name = "系统模版工坊API-模版工坊发布")
    public void templateAdd(){

    }

    @PostMapping("/template-delete")
    @Tag(name = "系统模版工坊API-模版工坊移除")
    public void templateDelete(){

    }


}
