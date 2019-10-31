DROP TABLE IF EXISTS employee;
CREATE TABLE employee (employee_id SERIAL, first_name VARCHAR(60) NOT NULL, last_name VARCHAR(60), department_id INTEGER not null ,
                       job_title text, gender VARCHAR(6) NOT NULL , date_of_birth date);
