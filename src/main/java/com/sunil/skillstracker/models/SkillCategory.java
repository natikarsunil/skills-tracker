package com.sunil.skillstracker.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunil.skillstracker.enums.SkillType;

@Entity
@Table(name = "skill_categories")
public class SkillCategory extends Audit implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String name;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private SkillType skillType;

  private SkillCategory parentCategory;

  @OneToMany(mappedBy = "skillCategory")
  @JsonIgnore
  private List<SkillCategoryMap> skillCategoryMaps = new ArrayList<>();

  public SkillCategory(final String name, final SkillType skillType,
      final SkillCategory parentCategory) {
    this.name = name;
    this.skillType = skillType;
    this.parentCategory = parentCategory;
  }

  public SkillCategory(final String name, final SkillType skillType,
      final SkillCategory parentCategory, final List<SkillCategoryMap> skillCategoryMaps) {
    this.name = name;
    this.skillType = skillType;
    this.parentCategory = parentCategory;
    this.skillCategoryMaps = skillCategoryMaps;
  }


  public SkillCategory() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public SkillType getSkillType() {
    return skillType;
  }

  public void setSkillType(final SkillType skillType) {
    this.skillType = skillType;
  }

  public SkillCategory getParentCategory() {
    return parentCategory;
  }

  public void setParentCategory(final SkillCategory parentCategory) {
    this.parentCategory = parentCategory;
  }

  public List<SkillCategoryMap> getSkillCategoryMaps() {
    return skillCategoryMaps;
  }

  public void setSkillCategoryMaps(final List<SkillCategoryMap> skillCategoryMaps) {
    this.skillCategoryMaps = skillCategoryMaps;
  }

  private void addSkillCategoryMap(Skill skill){
    this.skillCategoryMaps.add(new SkillCategoryMap(skill, this));
  }
}
