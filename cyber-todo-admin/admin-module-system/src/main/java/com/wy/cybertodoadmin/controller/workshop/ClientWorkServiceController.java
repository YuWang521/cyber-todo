package com.wy.cybertodoadmin.controller.workshop;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*******************************************************************
 * <pre></pre>
 * @文件名称： ClientWorkServiceController.java
 * @包 路  径： com.wy.cybertodoadmin.controller.workshop
 * @Copyright：北京数字医信责任有限公司 (C) 2023 *
 * @Description:
 * @Version: V1.0
 * @Author： wy
 * @Date：2023/10/1 14:41
 * @Modify：
 */
@RestController
@RequestMapping("/workshop/client")
@Tag(name = "客户端模版工坊API")
public class ClientWorkServiceController extends WorkShopController{
    // TODO: 2023/10/1  
    @PostMapping("/list")
    @Operation(summary = "我的模版列表")
    public void list(){

    }
    
    @PostMapping("/add-update")
    @Operation(summary = "新增模版")
    public void add(){

    }
    
    @GetMapping("/detail/{id}")
    @Operation(summary = "模版详情")
    public void detail(){

    }
    
    @PostMapping("/delete")
    @Operation(summary = "删除模版")
    public void delete(){

    }
    


}
