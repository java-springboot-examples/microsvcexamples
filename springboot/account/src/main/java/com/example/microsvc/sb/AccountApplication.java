package com.example.microsvc.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountApplication extends ServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
