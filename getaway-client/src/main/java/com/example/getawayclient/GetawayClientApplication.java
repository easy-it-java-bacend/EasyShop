package com.example.getawayclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GetawayClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetawayClientApplication.class, args);
    }

}
