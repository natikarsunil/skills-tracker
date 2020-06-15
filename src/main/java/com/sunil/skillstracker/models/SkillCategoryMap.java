package com.sunil.skillstracker.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "skills_category_map")
public class SkillCategoryMap extends Audit implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "skill_id")
  private Skill skill;

  @ManyToOne
  @JoinColumn(name = "skill_category_id")
  private SkillCategory skillCategory;

  @OneToMany(mappedBy = "skillCategoryMap")
  @JsonIgnore
  private List<EmployeeSkillMap> employeeSkillMaps = new ArrayList<>();

  public SkillCategoryMap(final Skill skill, final SkillCategory skillCategory) {
    this.skill = skill;
    this.skillCategory = skillCategory;
  }

  public SkillCategoryMap(){}

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public Skill getSkill() {
    return skill;
  }

  public void setSkill(final Skill skill) {
    this.skill = skill;
  }

  public SkillCategory getSkillCategory() {
    return skillCategory;
  }

  public void setSkillCategory(final SkillCategory skillCategory) {
    this.skillCategory = skillCategory;
  }

  public List<EmployeeSkillMap> getEmployeeSkillMaps() {
    return employeeSkillMaps;
  }

  public void setEmployeeSkillMaps(final List<EmployeeSkillMap> employeeSkillMaps) {
    this.employeeSkillMaps = employeeSkillMaps;
  }
}
