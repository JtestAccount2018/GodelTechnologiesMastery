package com.example.mastery_java_godel_technologies;
import com.example.dao.EmployeeDAOImpl;
import com.example.dto.Employee;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Sql({"/schema.sql", "/data.sql"})
@Log4j2
@SpringBootTest
class MasteryJavaGodelTechnologiesApplicationTests {

@Autowired
    EmployeeDAOImpl dao;


    @Test
    void contextLoads() {
        List<Employee> list = dao.getAllEmployee();
        assertTrue(list.size()>0);
        list.forEach(l-> log.info(l.toString()));
    }



}
