package com.godel.mastery.functionaltest.helper;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;

import com.godel.mastery.functionaltest.AbstractSteps;
import com.godel.mastery.functionaltest.EmployeeServiceKeys;
import com.godel.mastery.functionaltest.employee.Employee;
import com.godel.mastery.functionaltest.support.TestContext;
import org.springframework.web.client.HttpClientErrorException;

import static com.godel.mastery.functionaltest.EmployeeServiceKeys.*;

public class EmployeeRestHelper extends AbstractSteps {

    public EmployeeRestHelper(TestContext<EmployeeServiceKeys> context) {
        super(context);
    }

    private final MediaType mediaType = MediaType.APPLICATION_JSON;
    private final HttpHeaders headers = getHttpHeaders(mediaType);
    private final HttpEntity<String> entity =  new HttpEntity<>(null, headers);

    public void invokeEmployeeServiceFindEmployeeById(final Long id){
        
        try{
            final ResponseEntity<Employee> responseEntity =
                REST_TEMPLATE.exchange(BASE_URL+"/"+id, HttpMethod.GET, entity, Employee.class);
            context.add(RESPONSE_BODY, responseEntity.getBody());
            context.add(STATUS_CODE, responseEntity.getStatusCode());
            context.add(EMPLOYEE_REGISTRATION_ID, responseEntity.getBody().getEmployee_id());
        } catch (HttpClientErrorException e){
            context.add(STATUS_CODE, e.getStatusCode());
        }
    }

    public void invokeEmployeeServiceGetAllEmployee(){
        try{
            final ResponseEntity<List> responseEntity =
                REST_TEMPLATE.exchange(BASE_URL, HttpMethod.GET, entity, List.class);
            context.add(RESPONSE_BODY, responseEntity.getBody());
            context.add(STATUS_CODE, responseEntity.getStatusCode());
        } catch (HttpClientErrorException e){
            context.add(STATUS_CODE, e.getStatusCode());
        }
    }

    public void invokeEmployeeServiceAddNewEmployee(final Employee employee) throws JsonProcessingException {
        try{
            ObjectMapper mapper = new ObjectMapper();
            final HttpEntity<String> postEntity = new HttpEntity<>(mapper.writeValueAsString(employee), headers);
            final ResponseEntity<String> responseEntity =
                REST_TEMPLATE.exchange(BASE_URL, HttpMethod.POST, postEntity, String.class);
            context.add(STATUS_CODE, responseEntity.getStatusCode());
            context.add(RESPONSE_BODY, responseEntity.getBody());
        } catch (HttpClientErrorException e){
            context.add(STATUS_CODE, e.getStatusCode());
            context.add(RESPONSE_BODY, e.getResponseBodyAsString());
        }
    }



    private HttpHeaders getHttpHeaders(MediaType mediaType) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(mediaType));
        headers.setContentType(mediaType);
        return headers;
    }
}
