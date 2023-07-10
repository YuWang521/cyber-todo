package com.wy.cybertodoadmin;

import com.wy.cybertodoadmin.base.until.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j
public class CyberTodoAdminApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CyberTodoAdminApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        startLog(environment);

    }


    static void startLog(ConfigurableEnvironment environment) throws UnknownHostException {
        log.info("start success");
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = environment.getProperty("server.port");
        String path = ConvertUtils.getString(environment.getProperty("server.servlet.context-path"));
        log.info("\n----------------------------------------------------------\n\t" +
            "Application Cyber-Todo-Admin is running! Access URLs:\n\t" +
            "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
            "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
            "Swagger文档: http://" + ip + ":" + port + path + "/doc.html\n\t" +
            "druid监控: \thttp://" + ip + ":" + port + path + "/druid/login.html\n\t" +
            "----------------------------------------------------------");

    }

}
