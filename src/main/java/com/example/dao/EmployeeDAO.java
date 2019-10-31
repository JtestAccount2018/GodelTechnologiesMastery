package com.example.dao;

import com.example.dto.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> getAllEmployee();
    public Employee getEmployeeById(long id);
    public int deleteEmployeeById(long id);
    public long addEmployee(Employee employee);
    public int updateEmployee(Employee employee);

}
