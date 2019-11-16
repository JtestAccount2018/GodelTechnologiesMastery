package com.example.rest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.ApplicationRunner;
import com.example.dto.Employee;
import com.example.dto.Gender;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Date;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
@ContextConfiguration(classes = ApplicationRunner.class)
class EmployeeControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  EmployeeService service;

  private static Employee employee;

  @BeforeAll
  private static void setEmployee() {
    employee =
        Employee.builder()
            .employee_id(1)
            .first_name("Test")
            .last_name("test")
            .department_id(0)
            .job_title("do test")
            .gender(Gender.FEMALE)
            .date_of_birth(new Date(new GregorianCalendar(1999, 10, 11).getTime().getTime()))
            .build();
  }

  @Test
  void getAllEmployeeTest() throws Exception {
    List<Employee> allEmployees = Arrays.asList(employee);
    given(service.getAllEmployee()).willReturn(allEmployees);

    mvc.perform(get("/main/employees").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].first_name", is(employee.getFirst_name())));
  }

  @Test
  void getEmployeeByIdTest() throws Exception {
    given(service.getEmployeeById(1)).willReturn(employee);
    mvc.perform(get("/main/employees/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("employee_id", is(1)))
        .andExpect(jsonPath("first_name", is(employee.getFirst_name())));
  }

  @Test
  void addEmployeeTest() throws Exception {

    given(service.addEmployee(employee)).willReturn(5l);
    mvc.perform(
            post("/main/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(employee)))
        .andExpect(status().isCreated())
        .andExpect(content().string(containsString("5")));
  }

  @Test
  void editEmployeeTest() throws Exception {
    given(service.updateEmployee(employee)).willReturn(1);
    mvc.perform(
            put("/main/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(employee)))
        .andExpect(status().isAccepted())
        .andExpect(content().string(containsString("1")));
  }

  @Test
  void deleteEmployeeTest() throws Exception {
    given(service.deleteEmployeeById(1)).willReturn(1);
    mvc.perform(delete("/main/employees/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("1")));
  }
}
