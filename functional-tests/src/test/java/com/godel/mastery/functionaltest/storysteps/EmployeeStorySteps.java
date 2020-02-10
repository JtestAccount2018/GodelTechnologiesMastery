package com.godel.mastery.functionaltest.storysteps;

import com.godel.mastery.functionaltest.AbstractSteps;
import com.godel.mastery.functionaltest.EmployeeServiceKeys;
import com.godel.mastery.functionaltest.employee.Employee;
import com.godel.mastery.functionaltest.support.DatabaseClient;
import com.godel.mastery.functionaltest.support.TestContext;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.http.*;

import java.util.Date;
import java.util.Map;

import static com.godel.mastery.functionaltest.EmployeeServiceKeys.*;
import static org.junit.Assert.*;

public class EmployeeStorySteps extends AbstractSteps {

    public EmployeeStorySteps(TestContext<EmployeeServiceKeys> context) {
        super(context);
    }

    private Employee employee;

    private static final String FIRST_NAME = "Ulik";
    private static final String LAST_NAME = "Anezka";
    private static final String JOB_TITLE = "do some work";
    private static final Date DATE_OF_BIRTH = new Date(10, 10, 2000);
    private static final Long DEPARTMENT_ID = 10l;


    @BeforeScenario
    public void setUp(){
        employee = null;
        context.clear();
    }

    @Given("a valid employee")
    public void aValidEmployee(){
        employee = Employee.builder()
            .first_name(FIRST_NAME)
            .last_name(LAST_NAME)
            .date_of_birth(DATE_OF_BIRTH)
            .department_id(DEPARTMENT_ID)
            .job_title(JOB_TITLE)
            .build();
    }

    @When("an employee transmitted to rest")
    public void restRequest(){
        HttpEntity<Employee> entity = new HttpEntity<>(employee);
       final ResponseEntity response = REST_TEMPLATE.exchange(BASE_URL, HttpMethod.POST, entity, String.class);
       context.add(RESPONSE_BODY, response.getBody());
       context.add(STATUS_CODE, response.getStatusCode());
    }

    @Then("status received $status")
    public void checkResponseStatus(final HttpStatus status){
        assertTrue(status==HttpStatus.CREATED);
    }

    @Then("body received $body")
    public void checkBody(final String body){
        assertEquals(body, context.getAsString(RESPONSE_BODY));
    }

    @Then("employee saved in database")
    public void employeeSavedInDatabaseCheck(){
        Map<String, Object> lastEmployeeAdded = DatabaseClient.getLastAddedEmployee();


    }

}
