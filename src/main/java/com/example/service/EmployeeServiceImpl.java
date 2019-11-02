package com.example.service;

import com.example.dao.EmployeeDAO;
import com.example.dto.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeDAO dao;

  /**
   * In case we have many entities of Employee, use this function to get them all.
   *
   * @return List of Employee from database
   */
  @Override
  public List<Employee> getAllEmployee() {
    return dao.getAllEmployee();
  }

  /**
   * In case we know id number Employee entity we need to get.
   *
   * @param id - id number requested Employee
   * @return Employee entity from database
   */
  @Override
  public Employee getEmployeeById(long id) {
    return dao.getEmployeeById(id);
  }

  /**
   * In case we need delete Employee entity from database
   *
   * @param id - id number requested Employee
   * @return Number of deleted records
   */
  @Override
  public int deleteEmployeeById(long id) {
    return dao.deleteEmployeeById(id);
  }

  /**
   * In case we need to add new Employee entity to database
   *
   * @param employee - entity of Employee we need to add
   * @return Number of records added
   */
  @Override
  public long addEmployee(Employee employee) {
    return dao.addEmployee(employee);
  }

  /**
   * In case we need to update existing Employee entity in database
   *
   * @param employee - entity of Employee we need to update
   * @return Number of updated records
   */
  @Override
  public int updateEmployee(Employee employee) {
    return dao.updateEmployee(employee);
  }
}
