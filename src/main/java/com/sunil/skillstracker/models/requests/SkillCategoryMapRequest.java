package com.sunil.skillstracker.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillCategoryMapRequest {
  private Long id;

  private SkillCategoryRequest skillCategoryRequest;

}
