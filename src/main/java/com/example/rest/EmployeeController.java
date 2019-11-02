package com.example.rest;

import com.example.dto.Employee;
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
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  EmployeeService service;

  // curl -v http://localhost:8080/employees/getAllEmployee
  @GetMapping("/getAllEmployee")
  public List<Employee> getAllEmployee() {
    log.debug("rest: getAllEmployee");
    return service.getAllEmployee();
  }

  // curl -v http://localhost:8080/employees/get/1
  @GetMapping("/get/{id}")
  public Employee getEmployeeById(@PathVariable("id") long id) {
    log.debug("rest: getEmployeeById " + id);
    return service.getEmployeeById(id);
  }

  // curl -H "Content-Type: application/json" -X POST -d "{\"employee_id\":null,\"first_name\":\"TEST\",\"last_name\":\"TEST\",\"department_id\":1,\"job_title\":\"TEST\",\"gender\":\"MALE\",\"date_of_birth\":\"1995-07-11\"}" -v http://localhost:8080/employees/add
  @PostMapping("/add")
  @ResponseStatus(HttpStatus.CREATED)
  public long addEmployee(@RequestBody Employee employee) {
    log.debug("rest: addEmployee - " + employee);
    return service.addEmployee(employee);
  }
  // curl -H "Content-Type: application/json" -X PUT -d "{\"employee_id\":1,\"first_name\":\"TEST\",\"last_name\":\"TEST\",\"department_id\":1,\"job_title\":\"TEST\",\"gender\":\"MALE\",\"date_of_birth\":\"1995-07-11\"}" -v http://localhost:8080/employees/update
  @PutMapping("/update")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public int editEmployee(@RequestBody Employee employee) {
    log.debug("rest: update Employee -" + employee);
    return service.updateEmployee(employee);
  }
  // curl -X DELETE -v http://localhost:8080/employees/delete/1
  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  public int deleteEmployee(@PathVariable("id") long id) {
    log.debug("rest: delete Employee by ID=" + id);
    return service.deleteEmployeeById(id);
  }
}
