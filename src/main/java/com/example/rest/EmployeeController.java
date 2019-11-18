package com.example.rest;

import com.example.dto.Employee;
import com.example.exceptions.DataNotFoundException;
import com.example.service.EmployeeService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/main")
public class EmployeeController {

  @Autowired EmployeeService service;

  @GetMapping("/employees")
  public List<Employee> getAllEmployee() throws DataNotFoundException {
    log.debug("rest: GET employees");
    return service.getAllEmployee();
  }

  @GetMapping("/employees/{id}")
  public Employee getEmployeeById(@PathVariable("id") long id) throws DataNotFoundException {
    log.debug("rest: GET id= {} ", id);
    return service.getEmployeeById(id);
  }

  @PostMapping("/employees")
  @ResponseStatus(HttpStatus.CREATED)
  public long addEmployee(@RequestBody Employee employee) {
    log.debug("rest: ADD - {}", employee);
    return service.addEmployee(employee);
  }

  @PutMapping("/employees/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public long editEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
    employee.setEmployee_id(id);
    log.debug("rest: UPDATE  - {}", employee);
    return service.updateEmployee(employee);
  }

  @DeleteMapping("/employees/{id}")
  @ResponseStatus(HttpStatus.OK)
  public String deleteEmployee(@PathVariable("id") long id) {
    log.debug("rest:  DELETE id = {}",  id);
    return service.deleteEmployeeById(id);
  }
}
