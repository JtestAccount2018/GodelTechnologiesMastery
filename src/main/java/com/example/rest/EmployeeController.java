package com.example.rest;

import com.example.dto.Employee;
import com.example.exceptions.DataNotFoundException;
import com.example.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/main")
public class EmployeeController {

    @Autowired
    private EmployeeService service;


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
    public String addEmployee(@RequestBody Employee employee) {
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
        log.debug("rest:  DELETE id = {}", id);
        return service.deleteEmployeeById(id);
    }
}
