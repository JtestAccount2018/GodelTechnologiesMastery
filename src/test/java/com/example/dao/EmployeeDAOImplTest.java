package com.example.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.ApplicationRunner;
import com.example.dto.Employee;
import com.example.dto.Gender;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.jdbc.Sql;

/**
 * DAO tests run with different DB defined in application.properties file located in TEST RESOURCES
 * directory
 */
@Sql({"/schema.sql", "/data.sql"})
@Log4j2
@SpringBootTest(classes = ApplicationRunner.class)
class EmployeeDAOImplTest {

  @Autowired
  private EmployeeDAO dao;

  @Test
  void getAllEmployeeTest() {
    List<Employee> list = dao.getAllEmployee();
    assertTrue(list.size() == 3);
    list.forEach(employee -> log.info(employee));
  }

  @Test
  void getEmployeeByIdTest() {
    Employee employee = dao.getEmployeeById(1);
    assertNotNull(employee);
    employee = dao.getEmployeeById(100);
    assertNull(employee);
  }

  @Test
  void deleteEmployeeByIdTest() {
    assertTrue(dao.deleteEmployeeById(1) == 1);
    assertTrue(dao.deleteEmployeeById(1) == 0);
  }

  @Test
  void addEmployeeTest() {
    Employee employee =
        Employee.builder()
            .employee_id(0)
            .first_name("test")
            .last_name("test")
            .department_id(1)
            .job_title(null)
            .gender(Gender.FEMALE)
            .date_of_birth(new Date(new GregorianCalendar(1962, 05, 02).getTime().getTime()))
            .build();
    assertTrue(dao.addEmployee(employee) > 1);
    employee.setFirst_name(null);
    try {
      dao.addEmployee(employee);
    } catch (DataAccessException e) {
      assertTrue(e.getLocalizedMessage().length() > 0);
      log.info(e.getLocalizedMessage());
    }
  }

  @Test
  void updateEmployeeTest() {
    Employee employee =
        Employee.builder()
            .employee_id(2)
            .first_name("test")
            .last_name("test")
            .department_id(1)
            .job_title(null)
            .gender(Gender.FEMALE)
            .date_of_birth(new Date(new GregorianCalendar(1962, 05, 02).getTime().getTime()))
            .build();
    assertTrue(dao.updateEmployee(employee) == 1);
  }
}
