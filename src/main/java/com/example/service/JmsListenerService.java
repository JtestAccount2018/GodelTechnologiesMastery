package com.example.service;


import com.example.dao.EmployeeDAO;
import com.example.dto.Employee;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class JmsListenerService {


    @Autowired
    private EmployeeDAO dao;

    @org.springframework.jms.annotation.JmsListener(destination = "OrderQueue", containerFactory = "myFactory")
    public void receiveMessage(Employee employee) {
        log.info("Received employee message {}", employee);
        dao.addEmployee(employee);
    }


}
