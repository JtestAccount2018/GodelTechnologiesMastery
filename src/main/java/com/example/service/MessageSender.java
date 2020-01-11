package com.example.service;

import com.example.dto.Employee;

public interface MessageSender {

    void sendMessage(String queue, Employee employee);
}
