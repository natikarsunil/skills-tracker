package com.sunil.skillstracker.controllers;

import static com.sunil.skillstracker.constants.Routes.BASE_APP_URI;
import static com.sunil.skillstracker.constants.Routes.EMPLOYEES_URI;
import static com.sunil.skillstracker.constants.Routes.SKILLS_URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.skillstracker.constants.Messages;
import com.sunil.skillstracker.models.Employee;
import com.sunil.skillstracker.models.MessageResponse;
import com.sunil.skillstracker.models.requests.EmployeeRequest;
import com.sunil.skillstracker.models.requests.RequestMapper;
import com.sunil.skillstracker.services.EmployeeService;
import com.sunil.skillstracker.services.SkillsService;

@RestController
@RequestMapping(BASE_APP_URI)
public class EmployeeController {

  private final EmployeeService employeeService;

  private final SkillsService skillsService;

  private final RequestMapper requestMapper;

  public EmployeeController(final EmployeeService employeeService,
      final SkillsService skillsService, final RequestMapper requestMapper) {
    this.employeeService = employeeService;
    this.skillsService = skillsService;
    this.requestMapper = requestMapper;
  }

  @GetMapping(EMPLOYEES_URI)
  public ResponseEntity<?> getAllEmployees() {
    return ResponseEntity.ok(employeeService.getAllEmployees());
  }

  @GetMapping(EMPLOYEES_URI + "/{employeeId}" + SKILLS_URI)
  public ResponseEntity<?> getEmployee(@PathVariable("employeeId") Long employeeId) {
    return ResponseEntity.ok(employeeService.getEmployee(employeeId));
  }

  @PostMapping(EMPLOYEES_URI)
  public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
    Employee employee = requestMapper.mapToEmployee(employeeRequest);
    employee = employeeService.createEmployee(employee);
    return ResponseEntity.ok(employee);
  }

  @PutMapping(EMPLOYEES_URI)
  public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
    Employee employee = requestMapper.mapToEmployee(employeeRequest);
    employee = employeeService.updateEmployee(employee);
    return ResponseEntity.ok(employee);
  }

  @DeleteMapping(EMPLOYEES_URI + "/{employeeId}")
  public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
    employeeService.deleteEmployee(employeeId);
    return ResponseEntity.ok(new MessageResponse(Messages.EMPLOYEE_DELETED_MSG));
  }
}
