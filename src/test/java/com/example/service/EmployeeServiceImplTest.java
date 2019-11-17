package com.example.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.ApplicationRunner;
import com.example.dao.EmployeeDAO;
import com.example.dto.Employee;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = ApplicationRunner.class)
class EmployeeServiceImplTest {

  @Autowired
  EmployeeService service;

  @MockBean
  EmployeeDAO dao;

  @MockBean
  Employee employee;

  @Test
  void getAllEmployeeTest() {
    List<Employee> list;
    Mockito.doReturn(Arrays.asList(employee)).when(dao).getAllEmployee();
    list = service.getAllEmployee();
    assertTrue(list.size() == 1);

    Mockito.verify(dao, Mockito.times(1)).getAllEmployee();
  }

  @Test
  void getEmployeeByIdTest() {
    Employee result;
    Mockito.doReturn(employee).when(dao).getEmployeeById(1);
    result = service.getEmployeeById(1);
    assertTrue(result.equals(employee));
  }

  @Test
  void deleteEmployeeByIdTest() {
    int result;
    Mockito.doReturn(1).when(dao).deleteEmployeeById(1);
    result = service.deleteEmployeeById(1);
    assertTrue(result == 1);
  }

  @Test
  void addEmployeeTest() {
    long result;
    Mockito.doReturn(22l).when(dao).addEmployee(employee);
    result = service.addEmployee(employee);
    assertTrue(result == 22);
  }

  @Test
  void updateEmployeeTest() {
    int result;
    Mockito.doReturn(1).when(dao).updateEmployee(employee);
    result = service.updateEmployee(employee);
    assertTrue(result == 1);
  }
}
