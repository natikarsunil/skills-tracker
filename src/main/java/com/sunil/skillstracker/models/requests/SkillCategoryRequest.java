package com.sunil.skillstracker.models.requests;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.sunil.skillstracker.enums.SkillType;
import com.sunil.skillstracker.models.Skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillCategoryRequest {
  private Long id;

  @NotNull
  private String name;

  private List<Skill> skills;

  private SkillType skillType;
}
