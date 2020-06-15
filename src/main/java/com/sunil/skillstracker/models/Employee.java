package com.sunil.skillstracker.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employees")
public class Employee extends Audit implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  private String userName;

  @OneToMany(mappedBy = "employee")
  private List<EmployeeSkillMap> employeeSkillMaps = new ArrayList<>();

  public Employee() {
  }

  public Employee(@NotBlank String firstName,
      @NotBlank String lastName,
      @NotBlank String userName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public List<EmployeeSkillMap> getEmployeeSkillMaps() {
    return employeeSkillMaps;
  }

  public void setEmployeeSkillMaps(final List<EmployeeSkillMap> employeeSkillMaps) {
    this.employeeSkillMaps = employeeSkillMaps;
  }
}
