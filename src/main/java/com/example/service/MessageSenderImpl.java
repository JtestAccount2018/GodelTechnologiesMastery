package com.example.service;

import com.example.dto.Employee;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class MessageSenderImpl implements MessageSender {

    @Autowired
    JmsTemplate template;

    @Override
    public void sendMessage(String queue, Employee employee) {
        log.info("Message sends in queue {}, value is: {}", queue, employee);
        template.convertAndSend(queue, employee);
    }
}
