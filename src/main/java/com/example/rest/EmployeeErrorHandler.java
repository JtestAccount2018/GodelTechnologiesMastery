package com.example.rest;

import com.example.exceptions.DataNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
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

}
