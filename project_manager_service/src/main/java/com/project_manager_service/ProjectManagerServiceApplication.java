package com.project_manager_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProjectManagerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectManagerServiceApplication.class,args);
    }
}
