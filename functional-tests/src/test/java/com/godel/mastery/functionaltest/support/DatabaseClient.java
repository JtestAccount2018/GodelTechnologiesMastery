package com.godel.mastery.functionaltest.support;

import com.godel.mastery.functionaltest.employee.Employee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Map;

import static java.lang.System.getenv;
import static java.util.Optional.ofNullable;

public class DatabaseClient {

    private static final String DB_DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_HOST = ofNullable(getenv("DATABASE_HOST")).orElse("localhost");
    private static final String DB_URL = "jdbc:postgresql://" + DB_HOST + ":5432/employeedb";
    private static final JdbcTemplate JDBC_TEMPLATE;

    static {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER_CLASS_NAME);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        JDBC_TEMPLATE = new JdbcTemplate(dataSource);
    }

    public static void dropAllData() {
    JDBC_TEMPLATE.execute("TRUNCATE employee CASCADE");
    }

    public static Long createEmployee(final Employee employee){
        return JDBC_TEMPLATE.queryForObject("INSERT INTO employee (first_name, last_name, depatment_id," +
            "job_title, gender, date_of_birth) RETURNING employee_id", Long.class, employee.getFirst_name(),
            employee.getLast_name(), employee.getDepartment_id(), employee.getJob_title(), employee.getGender(),
            employee.getDate_of_birth());
    }

    public static Map<String, Object> getEmployeeByDepartmentId(final Long departmentId){
        try {
            return JDBC_TEMPLATE.queryForMap("SELECT * FROM employee WHERE department_id = ?", departmentId);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public static Map<String, Object> getLastAddedEmployee(){
        try{
            return JDBC_TEMPLATE.queryForMap("SELECT * FROM employee ORDER BY employee_id DESC LIMIT 1");
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }


}
