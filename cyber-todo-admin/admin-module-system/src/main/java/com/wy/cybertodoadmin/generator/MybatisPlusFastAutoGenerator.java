package com.wy.cybertodoadmin.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*******************************************************************
 * <pre></pre>
 * @文件名称： MybatisPlusFastAutoGenerator.java
 * @包 路  径： com.wy.cybertodoadmin.generator
 * @Copyright：北京数字医信责任有限公司 (C) 2023 *
 * @Description:
 * @Version: V1.0
 * @Author： wy
 * @Date：2023/10/1 15:32
 * @Modify：
 */
public class MybatisPlusFastAutoGenerator {

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/cyber-todo-admin-db?serverTimezone=Asia/Shanghai&allowMultiQueries=true", "root", "wy12345^");


    private static final String OUT_PUT_DIR = "/Users/wangyu/repo/cyber-todo/cyber-todo-admin/admin-module-system";

    private static final String PARENT_PACKAGE_NAME = "com.wy.cybertodoadmin";

    private static List<String> tableList = new ArrayList<>(Arrays.asList("client_user", "template_user", "template_show"));

    public static void main(String[] args) {
        FastAutoGenerator
                .create(DATA_SOURCE_CONFIG)
                .globalConfig(
                        builder -> {
                            builder.author("wy")
                                    .commentDate("yyyy-MM-dd hh:mm:ss")
                                    .outputDir(System.getProperty(OUT_PUT_DIR) + "/src/main/java") // 指定输出目录
                                    .disableOpenDir(); //禁止打开输出目录，默认打开

                        }
                )
                .packageConfig(
                        builder -> {
                            builder.parent(PARENT_PACKAGE_NAME)
                                    .moduleName("demo")
                                    .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty(OUT_PUT_DIR) + "/src/main/resources/mappers"));
                        }
                )
                .strategyConfig(
                        builder -> {
                            builder.addInclude(tableList)
                                    .entityBuilder()
                                    .enableLombok() //开启 Lombok
                                    .enableFileOverride() // 覆盖已生成文件
                                    .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                                    .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命
                                    // Mapper 策略配置
                                    .mapperBuilder()
                                    .enableFileOverride() // 覆盖已生成文件
                                    // Service 策略配置
                                    .serviceBuilder()
                                    .enableFileOverride() // 覆盖已生成文件
                                    .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                                    .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl
                                    // Controller 策略配置
                                    .controllerBuilder()
                                    .enableFileOverride(); // 覆盖已生成文件
                        }
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
