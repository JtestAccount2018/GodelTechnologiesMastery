package com.example.dao;

import com.example.dto.Employee;
import com.example.exceptions.DataNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

  @Autowired private EmployeeJpaDataRepository repository;

  @Override
  public List<Employee> getAllEmployee() throws DataNotFoundException {
    List<Employee> employees = repository.findAll();
    if (employees.size() > 0) return employees;
    throw new DataNotFoundException("No employees stored in DB");
  }

  @Override
  public Employee getEmployeeById(long id) throws DataNotFoundException {
    Optional<Employee> employee = repository.findById(id);
    return employee.orElseThrow(
        () -> new DataNotFoundException("No employee found with id =" + id));
  }

  @Override
  public String deleteEmployeeById(long id) {
    repository.deleteById(id);
    return "deleted";
  }

  @Override
  public long addEmployee(Employee employee) {
    return repository.save(employee).getEmployee_id();
  }

  @Override
  public long updateEmployee(Employee employee) {

    return repository.updateEmployeeById(
        employee.getFirst_name(),
        employee.getLast_name(),
        employee.getDate_of_birth(),
        employee.getDepartment_id(),
        employee.getGender(),
        employee.getJob_title(),
        employee.getEmployee_id());
  }
}
