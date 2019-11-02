package com.example.service;

import com.example.config.MasteryJavaGodelTechnologiesApplication;
import com.example.dao.EmployeeDAO;
import com.example.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = MasteryJavaGodelTechnologiesApplication.class)
@ExtendWith(SpringExtension.class)
class EmployeeServiceImplTest {

    @Autowired
    EmployeeService service;

    @MockBean
    EmployeeDAO dao;
    @MockBean
    Employee employee;

    @Test
    void getAllEmployee() {
    List<Employee> list;
    Employee [] employees = new Employee[] {employee};
        Mockito.doReturn(Arrays.asList(employees)).
                when(dao).
                getAllEmployee();
    list = service.getAllEmployee();
    assertTrue(list.size()==1);

    Mockito.verify(dao, Mockito.times(1)).getAllEmployee();
    }

    @Test
    void getEmployeeById() {
        Employee result;
        Mockito.doReturn(employee).
                when(dao).
                getEmployeeById(1);
        result = service.getEmployeeById(1);
        assertNotNull(result);
    }

    @Test
    void deleteEmployeeById() {
        int result;
        Mockito.doReturn(1).
                when(dao).
                deleteEmployeeById(1);
        result = service.deleteEmployeeById(1);
        assertTrue(result==1);
    }

    @Test
    void addEmployee() {
        long result;
        Mockito.doReturn(22l).
                when(dao).
                addEmployee(employee);
        result = service.addEmployee(employee);
        assertTrue(result==22);
    }

    @Test
    void updateEmployee() {
        int result;
        Mockito.doReturn(1).
                when(dao).
                updateEmployee(employee);
        result = service.updateEmployee(employee);
        assertTrue(result==1);
    }
}