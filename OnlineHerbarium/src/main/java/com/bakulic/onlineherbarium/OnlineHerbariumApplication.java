package com.bakulic.onlineherbarium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
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
