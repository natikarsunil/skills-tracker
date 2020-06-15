package com.sunil.skillstracker.services;

import static com.sunil.skillstracker.constants.Messages.EMPLOYEES_NOT_FOUND_MSG;
import static com.sunil.skillstracker.constants.Messages.EMPLOYEE_ID_NOT_PROVIDED_MSG;
import static com.sunil.skillstracker.constants.Messages.EMPLOYEE_WITH_ID_NOT_FOUND_MSG;
import static com.sunil.skillstracker.constants.Messages.EMPLOYEE_WITH_USERNAME_ALREADY_EXISTS_MSG;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sunil.skillstracker.exceptions.EmployeeException;
import com.sunil.skillstracker.exceptions.EmployeeNotFoundException;
import com.sunil.skillstracker.models.Employee;
import com.sunil.skillstracker.models.EmployeeSkillMap;
import com.sunil.skillstracker.repository.EmployeeRepository;
import com.sunil.skillstracker.repository.EmployeeSkillsMapRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final SkillsService skillsService;
  private final EmployeeRepository employeeRepository;
  private final EmployeeSkillsMapRepository employeeSkillsMapRepository;

  public EmployeeServiceImpl(final SkillsService skillsService,
      final EmployeeRepository employeeRepository,
      final EmployeeSkillsMapRepository employeeSkillsMapRepository) {
    this.skillsService = skillsService;
    this.employeeRepository = employeeRepository;
    this.employeeSkillsMapRepository = employeeSkillsMapRepository;
  }

  @Override
  public List<Employee> getAllEmployees() {
    List<Employee> employees = employeeRepository.findAll();
    if (employees == null || employees.isEmpty()) {
      throw new EmployeeNotFoundException(EMPLOYEES_NOT_FOUND_MSG);
    }
    return employees;
  }

  @Override
  public Boolean existsByUserName(final String userName) {
    return employeeRepository.existsByUserName(userName);
  }

  @Override
  public Employee getEmployee(final Long employeeId) {
    return employeeRepository.findById(employeeId)
        .orElseThrow(() -> new EmployeeNotFoundException(String.format(EMPLOYEE_WITH_ID_NOT_FOUND_MSG, employeeId)));
  }

  @Override
  public Employee createEmployee(final Employee employee) {
    if (employeeRepository.existsByUserName(employee.getUserName())) {
      throw new EmployeeException(String.format(EMPLOYEE_WITH_USERNAME_ALREADY_EXISTS_MSG, employee.getUserName()));
    }

    Employee employeeFromDb = employeeRepository.save(employee);

    List<EmployeeSkillMap> employeeSkillMaps = employee.getEmployeeSkillMaps();

    if (employeeSkillMaps != null
        && !employeeSkillMaps.isEmpty()) {
      employeeSkillMaps.forEach(esMap -> {
        esMap.setEmployee(employeeFromDb);
      });
      employeeSkillMaps = employeeSkillsMapRepository.saveAll(employeeSkillMaps);
      employeeFromDb.setEmployeeSkillMaps(employeeSkillMaps);
    }

    return getEmployee(employeeFromDb.getId());
  }

  @Transactional
  @Override
  public Employee updateEmployee(final Employee employee) {
    if (employee.getId() == null) {
      throw new EmployeeException(EMPLOYEE_ID_NOT_PROVIDED_MSG);
    }

    Optional<Employee> employeeTemp = employeeRepository.findById(employee.getId());
    if (!employeeTemp.isPresent()) {
      throw new EmployeeNotFoundException(String.format(EMPLOYEE_WITH_ID_NOT_FOUND_MSG, employee.getId()));
    }

    if (employee.getEmployeeSkillMaps() != null
        && !employee.getEmployeeSkillMaps().isEmpty()) {
      employeeSkillsMapRepository.saveAll(employee.getEmployeeSkillMaps());
    }

    return employeeRepository.save(employee);
  }

  @Transactional
  @Override
  public void deleteEmployee(final Long employeeId) {
    employeeRepository.delete(getEmployee(employeeId));
  }
}
