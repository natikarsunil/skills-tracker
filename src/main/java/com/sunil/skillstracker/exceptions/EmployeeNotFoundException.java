package com.sunil.skillstracker.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException(String exception) {
    super(exception);
  }
}
