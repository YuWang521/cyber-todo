/*
 * Copy right © 2022 浙江力石科技股份有限公司 All Rights Reserved.
 * Official Web Site: http://lishiots.com
 */
package com.wy.cybertodoadmin.controller.demo.knife;


import com.wy.cybertodoadmin.core.vo.Res_;
import com.wy.cybertodoadmin.demo.model.FileResp;
import com.wy.cybertodoadmin.demo.model.MapEnumUser;
import com.wy.cybertodoadmin.demo.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @since:knife4j-springdoc-openapi-demo
 * @auth <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/8/6 16:53
 */
@RestController
@RequestMapping("body1")
@Tag(name = "aaa参数")
public class Body1Controller {

    @Operation(summary = "我是表单")
    @PostMapping("/user")
    public ResponseEntity<User> use(User user){
        return ResponseEntity.ok(user);
    }


    @Operation(summary = "枚举可用值")
    @PostMapping("/mo")
    public ResponseEntity<MapEnumUser> mo(@RequestBody MapEnumUser mapEnumUser){
        return ResponseEntity.ok(mapEnumUser);
    }

    @Operation(summary = "枚举可用值1")
    @PostMapping("/mo1")
    public ResponseEntity<MapEnumUser> mo1( MapEnumUser mapEnumUser){
        return ResponseEntity.ok(mapEnumUser);
    }

    @Operation(summary = "普通body请求2")
    @PostMapping("/dd/body1")
    public ResponseEntity<FileResp> body23(@RequestBody FileResp fileResp){
        return ResponseEntity.ok(fileResp);
    }

    @Operation(summary = "普通body请求1")
    @PostMapping("/cc/body1")
    public ResponseEntity<FileResp> body2(@RequestBody FileResp fileResp) {
        return ResponseEntity.ok(fileResp);
    }

    @Operation(summary = "普通body请求3")
    @PostMapping("/ee/body1")
    public ResponseEntity<FileResp> body(@RequestBody FileResp fileResp) {
        return ResponseEntity.ok(fileResp);
    }

    @Operation(summary = "普通body请求4")
    @PostMapping("/ff/body1")
    public Res_<User> body4(@RequestBody FileResp fileResp) {
        return null;
    }

}
