package com.bakulic.onlineherbarium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bakulic.onlineherbarium.repository",
        "com.bakulic.onlineherbarium.controller",
        "com.bakulic.onlineherbarium.service","com.bakulic.onlineherbarium.security"})
public class OnlineHerbariumApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineHerbariumApplication.class, args);
    }

}
