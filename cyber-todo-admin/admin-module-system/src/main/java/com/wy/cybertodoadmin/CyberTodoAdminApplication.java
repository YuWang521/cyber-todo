package com.wy.cybertodoadmin;

import com.wy.cybertodoadmin.base.until.ConvertUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j
public class CyberTodoAdminApplication {

    @SneakyThrows
    public static void main(String[] args) throws UnknownHostException {
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(CyberTodoAdminApplication.class, args);
//        ConfigurableEnvironment environment = applicationContext.getEnvironment();
//        startLog(environment);
        SpringApplication app=new SpringApplication(CyberTodoAdminApplication.class);
        ConfigurableApplicationContext application=app.run(args);
        Environment env = application.getEnvironment();
        startLog(env);


    }


    static void startLog(Environment environment) throws UnknownHostException {
        log.info("start success");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:{}\n\t" +
                "External: \thttp://{}:{}\n\t"+
                "Doc: \thttp://{}:{}/doc.html\n"+
                "----------------------------------------------------------",
            environment.getProperty("spring.application.name"),
            environment.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            environment.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            environment.getProperty("server.port"));
//        String ip = InetAddress.getLocalHost().getHostAddress();
//        String port = environment.getProperty("server.port");
//        String path = ConvertUtils.getString(environment.getProperty("server.servlet.context-path"));
//        log.info("\n----------------------------------------------------------\n\t" +
//            "Application Cyber-Todo-Admin is running! Access URLs:\n\t" +
//            "Local: \t\thttp://localhost:" + port + path + "\n\t" +
//            "External: \thttp://" + ip + ":" + port + path + "\n\t" +
//            "Swagger文档: http://" + ip + ":" + port + path + "doc.html\n\t" +
//            "druid监控: \thttp://" + ip + ":" + port + path + "druid/login.html\n\t" +
//            "----------------------------------------------------------");

    }

}
