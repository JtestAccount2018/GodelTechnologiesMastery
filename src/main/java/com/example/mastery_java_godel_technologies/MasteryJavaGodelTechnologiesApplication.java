package com.example.mastery_java_godel_technologies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.example")
public class MasteryJavaGodelTechnologiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasteryJavaGodelTechnologiesApplication.class, args);
    }

}
