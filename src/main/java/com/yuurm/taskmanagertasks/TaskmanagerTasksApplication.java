package com.yuurm.taskmanagertasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
//@ComponentScan(basePackages = {"com.yuurm.taskmanagertasks"})
@EnableJpaRepositories(basePackages = {"com.yuurm.taskmanagertasks"})
@EnableFeignClients
@RefreshScope
public class TaskmanagerTasksApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskmanagerTasksApplication.class, args);
    }

}
