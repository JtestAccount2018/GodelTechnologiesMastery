package com.example.rest;


import com.example.dto.Employee;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.core.JsonParseException;
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
    EmployeeService service;

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String incorrectDataError(){
        log.debug("rest: incorrectDataError");
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }

    @ExceptionHandler({JsonParseException.class, JsonParseException.class})
    @ResponseBody
    public String incorrectJsonData(JsonParseException e){
        log.debug("rest: incorrect input data-"+e.getMessage());
        return "{\"response\" : \"Incorrect Input Data Error\" }";
    }

    // curl -v http://localhost:8080/getAllEmployee
    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee(){
        log.debug("rest: getAllEmployee");
        return service.getAllEmployee();
    }

    // curl -v http://localhost:8080/get/1
    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable("id") long id){
        log.debug("rest: getEmployeeById " + id);
        return service.getEmployeeById(id);
    }

    // curl -H "Content-Type: application/json" -X POST -d "{\"employee_id\":null,\"first_name\":\"TEST\",\"last_name\":\"TEST\",\"department_id\":1,\"job_title\":\"TEST\",\"gender\":\"MALE\",\"date_of_birth\":\"1995-07-11\"}" -v http://localhost:8080/add
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public long addEmployee(@RequestBody Employee employee){
        log.debug("rest: addEmployee - " + employee);
        return service.addEmployee(employee);
    }
    // curl -H "Content-Type: application/json" -X PUT -d "{\"employee_id\":1,\"first_name\":\"TEST\",\"last_name\":\"TEST\",\"department_id\":1,\"job_title\":\"TEST\",\"gender\":\"MALE\",\"date_of_birth\":\"1995-07-11\"}" -v http://localhost:8080/update
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public int editEmployee(@RequestBody Employee employee){
        log.debug("rest: update Employee -" +employee);
        return service.updateEmployee(employee);
    }
    // curl -X DELETE -v http://localhost:8080/delete/1
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public int deleteEmployee(@PathVariable("id") long id){
        log.debug("rest: delete Employee by ID="+id);
        return service.deleteEmployeeById(id);
    }

}
