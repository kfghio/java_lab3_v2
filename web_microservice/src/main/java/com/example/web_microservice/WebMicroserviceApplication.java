package com.example.web_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.*")
public class WebMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebMicroserviceApplication.class, args);
    }

}
