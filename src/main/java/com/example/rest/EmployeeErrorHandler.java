package com.example.rest;

import com.example.exceptions.DataNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@ControllerAdvice
public class EmployeeErrorHandler {

  @ExceptionHandler(DataAccessException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public String handleDataAccessException(DataAccessException e) {
    log.error("Handling DataAccessException :" + e);
    return "DataAccessException: " + e.getLocalizedMessage();
  }

  @ExceptionHandler({IllegalArgumentException.class})
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  @ResponseBody
  public String incorrectDataError() {
    log.error("rest: incorrectDataError");
    return "{  \"response\" : \"Incorrect Data Error\" }";
  }

  @ExceptionHandler({JsonParseException.class})
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  @ResponseBody
  public String incorrectJsonData(JsonParseException e) {
    log.error("rest: incorrect input data-" + e.getMessage());
    return "{\"response\" : \"Incorrect Input Data Error\" }";
  }

  @ExceptionHandler({DataNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String dataNotFound(DataNotFoundException e){
      log.error("Data not found exception with message: {}", e.getMessage());
      return e.getLocalizedMessage();
  }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String invalidDataReceived(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder message =  new StringBuilder();
        fieldErrors.forEach(z->message.append(z.getField()+ " - " + z.getDefaultMessage()+"\r\n"));
        log.error("Data received from request is invalid, validator message is: {}", message);
        return message.toString();
    }
}
