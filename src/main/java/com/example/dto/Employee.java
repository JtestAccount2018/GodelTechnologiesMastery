package com.example.dto;

import com.example.Validation.MinYearsAgo;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
public class Employee {

  @Builder
  public Employee(
      String first_name,
      String last_name,
      long department_id,
      String job_title,
      Gender gender,
      Date date_of_birth) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.department_id = department_id;
    this.job_title = job_title;
    this.gender = gender;
    this.date_of_birth = date_of_birth;
  }

  public Employee() {};

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employee_id")
  @Min(0)
  private long employee_id;

  @Column(name = "first_name")
  @Size(min = 2, max = 60)
  @NotBlank
  private String first_name;

  @Column(name = "last_name")
  @Size(min = 2, max = 60)
  @NotBlank
  private String last_name;

  @Column(name = "department_id")
  @Min(value = 1, message = "Should be equals to 1 or greater")
  private long department_id;

  @Column(name = "job_title")
  private String job_title;

  @Column(name = "gender")
  @Enumerated(EnumType.STRING)
  @NotNull
  private Gender gender;

  @Temporal(value = TemporalType.DATE)
  @Column(name = "date_of_birth")
  @MinYearsAgo(years = 16)
  @Past
  private Date date_of_birth;
}
