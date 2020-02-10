package com.godel.mastery.functionaltest.employee;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private long employee_id;

    private String first_name;

    private String last_name;

    private long department_id;

    private String job_title;

    private Gender gender;

    private Date date_of_birth;
}
