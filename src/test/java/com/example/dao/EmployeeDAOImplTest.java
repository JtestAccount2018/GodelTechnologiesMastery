package com.example.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.ApplicationRunner;
import com.example.dto.Employee;
import com.example.dto.Gender;
import com.example.exceptions.DataNotFoundException;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO tests run with different DB defined in application.properties file located in TEST RESOURCES
 * directory
 */
@Sql({"/schema.sql", "/data.sql"})
@Log4j2
@SpringBootTest(classes = ApplicationRunner.class)
@Transactional
class EmployeeDAOImplTest {

  @Autowired
  private EmployeeDAO dao;

  @Test
  void getAllEmployee_AssertGetThreeEmployeesFromTestDb_Test() throws DataNotFoundException {
    List<Employee> list = dao.getAllEmployee();
    assertTrue(list.size() == 3);
    list.forEach(employee -> log.info(employee));
  }

  @Test
  void getEmployeeById_AssertGetEmployeeWithId1FromTestDb_Test() throws DataNotFoundException {
    Employee employee = dao.getEmployeeById(1);
    assertNotNull(employee);
  }

  @Test
  @Rollback
  void deleteEmployeeById_AssertMessageDeletedWhenDeleteEmployeeFromTestDb_Test() {
    assertTrue(dao.deleteEmployeeById(1).equals("deleted"));
  }

  @Test
  @Rollback
  void addEmployee_AssertReceiveIdGeneratedByDb_Test() {
    Employee employee =
        Employee.builder()
            .first_name("test")
            .last_name("test")
            .department_id(1)
            .job_title(null)
            .gender(Gender.FEMALE)
            .date_of_birth(new Date(new GregorianCalendar(1962, 05, 02).getTime().getTime()))
            .build();
    long id = dao.addEmployee(employee);
    assertTrue(id > 1);
    log.debug("id is {}", id);

  }

  @Test
  @Rollback
  void updateEmployee_AssertReceiveNumberOfUpdatedRecordsInDb_Test() throws DataNotFoundException {
    Employee employee =
        Employee.builder()
            .first_name("test")
            .last_name("test")
            .department_id(1)
            .job_title(null)
            .gender(Gender.FEMALE)
            .date_of_birth(new Date(new GregorianCalendar(1962, 05, 02).getTime().getTime()))
            .build();
        employee.setEmployee_id(1);
    assertTrue(dao.updateEmployee(employee) > 0);
  }
}
