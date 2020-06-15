package com.sunil.skillstracker.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "skills")
public class Skill extends Audit implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String name;

  @OneToMany(mappedBy = "skill")
  private List<SkillCategoryMap> skillCategoryMaps;

  public Skill(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Skill(String name) {
    this.name = name;
  }

  public Skill() {
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
}
