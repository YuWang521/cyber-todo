package com.wy.cybertodoadmin.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

/**
 * @author WangYu
 * @project cyber-todo
 * @description mp 代码生成
 * @date 2023/7/11 13:40:37
 */
public class GeneratorApplication {

        public static void main(String[] args) {
            String encode = new BCryptPasswordEncoder().encode("123456");
            System.out.println(encode);
            FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/study", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("pearl") // 设置作者
                        .fileOverride() // 覆盖已生成文件
                        .outputDir("D://"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.pearl.security") // 设置父包名
                        .moduleName("auth") // 设置父包模块名
                        .pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                        .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
        }


}
