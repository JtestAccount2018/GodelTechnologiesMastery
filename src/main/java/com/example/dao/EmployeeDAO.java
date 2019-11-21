package com.example.dao;

import com.example.dto.Employee;
import com.example.exceptions.DataNotFoundException;
import java.util.List;

public interface EmployeeDAO {

  public List<Employee> getAllEmployee() throws DataNotFoundException;

  public Employee getEmployeeById(long id) throws DataNotFoundException;

  public String deleteEmployeeById(long id);

  public long addEmployee(Employee employee);

  public long updateEmployee(Employee employee) throws DataNotFoundException;
}
