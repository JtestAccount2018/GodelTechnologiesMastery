package com.example.dto;


import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class Employee {
    private long employee_id;
    private String first_name;
    private String last_name;
    private long department_id;
    private String job_title;
    private Gender gender;
    private Date date_of_birth;

}
