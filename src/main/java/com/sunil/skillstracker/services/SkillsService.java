package com.sunil.skillstracker.services;

import java.util.List;
import java.util.Optional;

import com.sunil.skillstracker.models.Skill;
import com.sunil.skillstracker.models.SkillCategory;
import com.sunil.skillstracker.models.SkillCategoryMap;

public interface SkillsService {

  List<Skill> getAllSkills();

  Skill getSkill(final Long skillId);

  Optional<Skill> getSkillByName(final String skillName);

  Skill createSkill(final Skill skill);

  Skill updateSkill(final Skill skill);

  void deleteSkill(final Long skillId);

  List<SkillCategory> getAllSkillCategories();

  SkillCategory getSkillCategory(final Long skillCategoryId);

  Optional<SkillCategory> getSkillCategoryByName(final String skillCategoryName);

  SkillCategory createSkillCategory(final SkillCategory skillCategory);

  SkillCategory updateSkillCategory(final SkillCategory skillCategory);

  void deleteSkillCategory(final Long skillCategoryId);
}
