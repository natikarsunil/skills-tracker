package com.sunil.skillstracker.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunil.skillstracker.enums.SkillLevel;

@Entity
@Table(name = "employee_skills_map")
public class EmployeeSkillMap extends Audit implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "skill_category_map_id")
  private SkillCategoryMap skillCategoryMap;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  @JsonIgnore
  private Employee employee;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private SkillLevel skillLevel;

  public EmployeeSkillMap(final SkillCategoryMap skillCategoryMap,
      @NotNull final SkillLevel skillLevel) {
    this.skillCategoryMap = skillCategoryMap;
    this.skillLevel = skillLevel;
  }

  public EmployeeSkillMap(){}

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public SkillCategoryMap getSkillCategoryMap() {
    return skillCategoryMap;
  }

  public void setSkillCategoryMap(final SkillCategoryMap skillCategoryMap) {
    this.skillCategoryMap = skillCategoryMap;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(final Employee employee) {
    this.employee = employee;
  }

  public SkillLevel getSkillLevel() {
    return skillLevel;
  }

  public void setSkillLevel(final SkillLevel skillLevel) {
    this.skillLevel = skillLevel;
  }
}
