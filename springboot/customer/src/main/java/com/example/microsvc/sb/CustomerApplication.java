package com.example.microsvc.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerApplication extends ServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
