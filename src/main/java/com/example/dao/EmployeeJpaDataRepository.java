package com.example.dao;

import com.example.dto.Employee;
import com.example.dto.Gender;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJpaDataRepository extends JpaRepository<Employee, Long> {

  @Modifying
  @Query(
      "UPDATE Employee e SET e.first_name= :firstName, e.last_name = :lastName, e.date_of_birth = :birthDate"
          + ", e.department_id = :dId, e.gender = :gender , e.job_title = :jobT WHERE e.employee_id = :id")
  int updateEmployeeById(
      @Param("firstName") String firstName,
      @Param("lastName") String lastName,
      @Param("birthDate") Date birthDate,
      @Param("dId") long dId,
      @Param("gender") Gender gender,
      @Param("jobT") String jobT,
      @Param("id") long id);
}
