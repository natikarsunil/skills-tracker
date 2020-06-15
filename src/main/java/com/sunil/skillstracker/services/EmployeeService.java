package com.sunil.skillstracker.services;

import java.util.List;

import com.sunil.skillstracker.models.Employee;

public interface EmployeeService {

  List<Employee> getAllEmployees();

  Boolean existsByUserName(final String userName);

  Employee getEmployee(final Long employeeId);

  Employee createEmployee(final Employee employee);

  Employee updateEmployee(final Employee employee);

  void deleteEmployee(final Long employeeId);
}
