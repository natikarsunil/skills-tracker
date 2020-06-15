package com.sunil.skillstracker.models.requests;

import javax.validation.constraints.NotNull;

import com.sunil.skillstracker.enums.SkillLevel;

import lombok.Data;

@Data
public class EmployeeSkillMapRequest {

  private Long id;

  private Long skillCategoryMapId;

  //private SkillCategoryMapRequest skillCategoryMapRequest;

  @NotNull
  private SkillLevel skillLevel;
}
