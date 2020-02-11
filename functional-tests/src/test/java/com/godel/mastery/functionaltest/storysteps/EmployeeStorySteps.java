package com.godel.mastery.functionaltest.storysteps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.godel.mastery.functionaltest.AbstractSteps;
import com.godel.mastery.functionaltest.EmployeeServiceKeys;
import com.godel.mastery.functionaltest.employee.Employee;
import com.godel.mastery.functionaltest.employee.Gender;
import com.godel.mastery.functionaltest.helper.EmployeeRestHelper;
import com.godel.mastery.functionaltest.support.DatabaseClient;
import com.godel.mastery.functionaltest.support.TestContext;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.http.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import static com.godel.mastery.functionaltest.EmployeeServiceKeys.*;
import static org.junit.Assert.*;

public class EmployeeStorySteps extends AbstractSteps {

    private final EmployeeRestHelper restHelper;

    private static final String FIRST_NAME = "Ulik";
    private static final String LAST_NAME = "Anezka";
    private static final String JOB_TITLE = "do some work";
    private static final Date DATE_OF_BIRTH = new Date(new GregorianCalendar(2000, 10, 10, 10, 00).getTime().getTime());
    private static final Long DEPARTMENT_ID = 10l;
    private static final Gender GENDER = Gender.MALE;

    private Employee employee;

    public EmployeeStorySteps(TestContext<EmployeeServiceKeys> context) {
        super(context);
        restHelper = new EmployeeRestHelper(context);
    }

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
            .gender(GENDER)
            .build();
    }

    @When("an employee transmitted to rest")
    public void restRequest() throws JsonProcessingException {
        restHelper.invokeEmployeeServiceAddNewEmployee(employee);
    }

    @Then("status received $status")
    public void checkResponseStatus(final String status)
    {
        HttpStatus statusCode = HttpStatus.valueOf(status);
        assertTrue(statusCode==HttpStatus.CREATED);
        assertEquals(statusCode, (HttpStatus) context.get(STATUS_CODE));
    }

    @Then("body received $body")
    public void checkBody(final String body){
        assertEquals(body, context.getAsString(RESPONSE_BODY));
    }

    @Then("employee saved in database")
    public void employeeSavedInDatabaseCheck(){
        Map<String, Object> lastEmployeeAddedMap = DatabaseClient.getLastAddedEmployee();
        assertEmployeeFields(lastEmployeeAddedMap, employee);
    }

    private void assertEmployeeFields(final Map<String, Object> lastEmployeeAddedMap, Employee employee) {
        assertEquals(employee.getFirst_name(), lastEmployeeAddedMap.get("first_name"));
        assertEquals(employee.getLast_name(), lastEmployeeAddedMap.get("last_name"));
        assertEquals(employee.getDate_of_birth(), lastEmployeeAddedMap.get("date_of_birth"));
        assertEquals(employee.getDepartment_id(), lastEmployeeAddedMap.get("department_id"));
        assertEquals(employee.getGender(), lastEmployeeAddedMap.get("gender"));
        assertTrue((Long)lastEmployeeAddedMap.get("id")>0);
    }

}
