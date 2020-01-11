package com.example.service;


import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class EmployeeServiceImplTestConfig {

    @Bean
    public EmployeeService employeeService(){
        return new EmployeeServiceImpl();
    }

}
