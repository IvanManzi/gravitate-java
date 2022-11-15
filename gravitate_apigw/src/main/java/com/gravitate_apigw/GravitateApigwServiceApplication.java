package com.gravitate_apigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GravitateApigwServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GravitateApigwServiceApplication.class,args);
    }
}
