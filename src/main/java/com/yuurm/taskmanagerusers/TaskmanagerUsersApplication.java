package com.yuurm.taskmanagerusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
//@ComponentScan(basePackages = {"com.yuurm.taskmanagerusers"})
@EnableJpaRepositories(basePackages = {"com.yuurm.taskmanagerusers"})
@RefreshScope
public class TaskmanagerUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskmanagerUsersApplication.class, args);
    }

}
