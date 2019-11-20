package com.example.dao;

import com.example.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJpaDataRepository extends JpaRepository<Employee, Long> {

}
