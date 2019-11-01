package com.example.service;

import com.example.dto.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployee();
    public Employee getEmployeeById(long id);
    public int deleteEmployeeById(long id);
    public long addEmployee(Employee employee);
    public int updateEmployee(Employee employee);


}
