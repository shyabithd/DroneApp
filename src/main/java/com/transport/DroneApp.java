package com.transport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class DroneApp {
    public static void main(String[] args) {
        SpringApplication.run(DroneApp.class, args);
    }
}
