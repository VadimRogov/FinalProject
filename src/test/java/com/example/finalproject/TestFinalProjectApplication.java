package com.example.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.from(FinalProjectApplication::main).with(TestFinalProjectApplication.class).run(args);
    }

}
