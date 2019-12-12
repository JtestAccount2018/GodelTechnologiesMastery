package com.example.service;

import com.example.dao.EmployeeDAO;
import com.example.dto.Employee;
import com.example.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO dao;

    @Autowired
    private MessageSender sender;

    /**
     * In case we have many entities of Employee, use this function to get them all.
     *
     * @return List of Employee from database
     */
    @Override
    public List<Employee> getAllEmployee() throws DataNotFoundException {
        return dao.getAllEmployee();
    }

    /**
     * In case we know id number Employee entity we need to get.
     *
     * @param id - id number requested Employee
     * @return Employee entity from database
     */
    @Override
    public Employee getEmployeeById(long id) throws DataNotFoundException {
        return dao.getEmployeeById(id);
    }

    /**
     * In case we need delete Employee entity from database
     *
     * @param id - id number requested Employee
     * @return Number of deleted records
     */
    @Override
    public String deleteEmployeeById(long id) {
        return dao.deleteEmployeeById(id);
    }

    /**
     * In case we need to add new Employee entity to JMS queue
     *
     * @param employee - entity of Employee we need to add
     * @return Number of records added
     */
    @Override
    public String addEmployee(Employee employee) {
        employee.setEmployee_id(0);
        sender.sendMessage("OrderQueue", employee);
        return "Entity was send to queue";
    }

    /**
     * In case we need to update existing Employee entity in database. Using query because we dont
     * need to add entity if it doesn't contains in DB
     *
     * @param employee - entity of Employee we need to update
     * @return Number of updated records
     */
    @Transactional
    @Override
    public long updateEmployee(Employee employee) throws DataNotFoundException {
        return dao.updateEmployee(employee);
    }
}
