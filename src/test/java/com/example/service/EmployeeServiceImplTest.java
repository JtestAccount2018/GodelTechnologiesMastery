package com.example.service;

import com.example.ApplicationRunner;
import com.example.dao.EmployeeDAO;
import com.example.dto.Employee;
import com.example.exceptions.DataNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ApplicationRunner.class)
class EmployeeServiceImplTest {

    @Autowired
    EmployeeService service;

    @MockBean
    EmployeeDAO dao;

    @MockBean
    private JmsTemplate jmsTemplate;

    @MockBean
    Employee employee;

    @Test
    void getAllEmployeeTest() throws DataNotFoundException {
        List<Employee> list;
        Mockito.doReturn(Arrays.asList(employee)).when(dao).getAllEmployee();
        list = service.getAllEmployee();
        assertTrue(list.size() == 1);

        Mockito.verify(dao, Mockito.times(1)).getAllEmployee();
    }

    @Test
    void getEmployeeByIdTest() throws DataNotFoundException {
        Employee result;
        Mockito.doReturn(employee).when(dao).getEmployeeById(1);
        result = service.getEmployeeById(1);
        assertTrue(result.equals(employee));
    }

    @Test
    void deleteEmployeeByIdTest() {
        String result;
        Mockito.doReturn("deleted").when(dao).deleteEmployeeById(1);
        result = service.deleteEmployeeById(1);
        assertTrue(result.equals("deleted"));
    }

    @Test
    void addEmployeeTest() {
        String result;
        result = service.addEmployee(employee);
        assertTrue(result.equals("Entity was send to queue"));
    }

    @Test
    void updateEmployeeTest() throws DataNotFoundException {
        long result;
        Mockito.doReturn(1l).when(dao).updateEmployee(employee);
        result = service.updateEmployee(employee);
        assertTrue(result == 1);
    }
}
