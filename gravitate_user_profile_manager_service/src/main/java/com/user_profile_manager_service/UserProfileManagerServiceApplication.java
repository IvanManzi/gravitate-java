package com.user_profile_manager_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserProfileManagerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProfileManagerServiceApplication.class,args);
    }
}
