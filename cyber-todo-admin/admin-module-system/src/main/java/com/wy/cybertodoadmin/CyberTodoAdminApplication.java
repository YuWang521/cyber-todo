package com.wy.cybertodoadmin;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j
public class CyberTodoAdminApplication {

    @SneakyThrows
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(CyberTodoAdminApplication.class);
        ConfigurableApplicationContext application = app.run(args);
        Environment env = application.getEnvironment();
        startLog(env);
    }

    static void startLog(Environment environment) throws UnknownHostException {
        log.info("start success");
        String localHost = InetAddress.getLocalHost().getHostAddress();
        String contextPath = environment.getProperty("server.servlet.context-path");
        log.info(
            "\n----------------------------------------------------------\n\t"
                + "Application '{}' is running! Access URLs: \n\t" + "Local: 	http://localhost:{}{} \n\t"
                + "External: 	http://{}:{}{} \n\t" + "Doc: 	http://{}:{}{}/doc.html \n\t"
                + "Druid: 	http://{}:{}{}/druid/index.html \n"
                + "----------------------------------------------------------",
            environment.getProperty("spring.application.name"), environment.getProperty("server.port"), contextPath,
            localHost, environment.getProperty("server.port"), contextPath, localHost,
            environment.getProperty("server.port"), contextPath, localHost, environment.getProperty("server.port"),
            contextPath);
    }

}
