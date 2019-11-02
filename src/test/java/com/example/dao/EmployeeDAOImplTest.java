package com.example.dao;

import com.example.config.MasteryJavaGodelTechnologiesApplication;
import com.example.dto.Employee;
import com.example.dto.Gender;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Sql({"/schema.sql", "/data.sql"})
@Log4j2
@SpringBootTest(classes = MasteryJavaGodelTechnologiesApplication.class)
class EmployeeDAOImplTest {

    @Autowired
    private EmployeeDAO dao;

    @Test
    void getAllEmployee() {
        List<Employee> list = dao.getAllEmployee();
        assertTrue(list.size()==3);
        list.forEach(employee -> log.info(employee));
    }

    @Test
    void getEmployeeById() {
        Employee employee =  dao.getEmployeeById(1);
        assertNotNull(employee);
        employee = dao.getEmployeeById(100);
        assertNull(employee);
    }

    @Test
    void deleteEmployeeById() {
        assertTrue(dao.deleteEmployeeById(1)==1);
        assertTrue(dao.deleteEmployeeById(1)==0);
    }

    @Test
    void addEmployee() {
        Employee employee = Employee.builder().
                employee_id(0).
                first_name("test").
                last_name("test").
                department_id(1).
                job_title(null).
                gender(Gender.FEMALE).
                date_of_birth(new Date(new GregorianCalendar(1962, 05, 02).getTime().getTime())).build();
        assertTrue(dao.addEmployee(employee)>1);
        employee.setFirst_name(null);
        try{
            dao.addEmployee(employee);
        }
        catch (DataAccessException e){
            assertTrue(e.getLocalizedMessage().length()>0);
            log.info(e.getLocalizedMessage());
        }

    }

    @Test
    void updateEmployee() {
        Employee employee = Employee.builder().
                employee_id(2).
                first_name("test").
                last_name("test").
                department_id(1).
                job_title(null).
                gender(Gender.FEMALE).
                date_of_birth(new Date(new GregorianCalendar(1962, 05, 02).getTime().getTime())).build();
        assertTrue(dao.updateEmployee(employee)==1);
    }
}