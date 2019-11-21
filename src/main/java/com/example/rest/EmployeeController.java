package com.example.rest;

import com.example.dto.Employee;
import com.example.exceptions.DataNotFoundException;
import com.example.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @ApiOperation(value = "View a list of available employees if it's not empty", response = List.class)
    @GetMapping("/employees")
    public List<Employee> getAllEmployee() throws DataNotFoundException {
        log.debug("rest: GET employees");
        return service.getAllEmployee();
    }


    @ApiOperation(value = "View employee with defined id if it exists", response = Employee.class)
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") long id) throws DataNotFoundException {
        log.debug("rest: GET id= {} ", id);
        return service.getEmployeeById(id);
    }

    @ApiOperation(value = "View status of adding employee in queue in JMS", response = String.class)
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public String addEmployee(@RequestBody Employee employee) {
        log.debug("rest: ADD - {}", employee);
        return service.addEmployee(employee);
    }


    @ApiOperation(value = "View number 1 if updating employee exists and updated successfully")
    @PutMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public long editEmployee(@PathVariable("id") long id, @RequestBody Employee employee) throws DataNotFoundException {
        employee.setEmployee_id(id);
        log.debug("rest: UPDATE  - {}", employee);
        return service.updateEmployee(employee);
    }

    @ApiOperation(value = "View status of delete operation", response = String.class)
    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEmployee(@PathVariable("id") long id) {
        log.debug("rest:  DELETE id = {}", id);
        return service.deleteEmployeeById(id);
    }
}
