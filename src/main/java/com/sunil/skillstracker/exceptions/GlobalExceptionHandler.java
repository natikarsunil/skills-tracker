package com.sunil.skillstracker.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.sunil.skillstracker")
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Server Error", details);
    return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(EmployeeException.class)
  public final ResponseEntity<Object> handleEmployeeException(EmployeeException ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Employee exception", details);
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EmployeeNotFoundException.class)
  public final ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Employee exception", details);
    return new ResponseEntity(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(SkillCategoryException.class)
  public final ResponseEntity<Object> handleSkillCategoryException(SkillCategoryException ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Skill category exception", details);
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SkillCategoryNotFoundException.class)
  public final ResponseEntity<Object> handleSkillCategoryNotFoundException(SkillCategoryNotFoundException ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Skill category exception", details);
    return new ResponseEntity(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(SkillException.class)
  public final ResponseEntity<Object> handleSkillException(
      SkillException ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Skills exception", details);
    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SkillNotFoundException.class)
  public final ResponseEntity<Object> handleSkillNotFoundException(
      SkillNotFoundException ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    ErrorResponse error = new ErrorResponse("Skills exception", details);
    return new ResponseEntity(error, HttpStatus.NOT_FOUND);
  }
}
