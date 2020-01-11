package com.example.dao;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

@TestConfiguration
public class EmployeeDAOImplTestConfig {

    @Bean
    @DependsOn(value = "employeeJpaDataRepository")
    public EmployeeDAO employeeDAO(){
        return new EmployeeDAOImpl();
    }

}
