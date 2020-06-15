package com.sunil.skillstracker.services;

import static com.sunil.skillstracker.constants.Messages.SKILLS_NOT_FOUND_MSG;
import static com.sunil.skillstracker.constants.Messages.SKILL_CATEGORIES_NOT_FOUND_MSG;
import static com.sunil.skillstracker.constants.Messages.SKILL_CATEGORY_ID_NOT_PROVIDED_MSG;
import static com.sunil.skillstracker.constants.Messages.SKILL_CATEGORY_WITH_ID_NOT_FOUND_MSG;
import static com.sunil.skillstracker.constants.Messages.SKILL_CATEGORY_WITH_NAME_ALREADY_EXISTS_MSG;
import static com.sunil.skillstracker.constants.Messages.SKILL_ID_NOT_PROVIDED_MSG;
import static com.sunil.skillstracker.constants.Messages.SKILL_WITH_ID_NOT_FOUND_MSG;
import static com.sunil.skillstracker.constants.Messages.SKILL_WITH_NAME_ALREADY_EXISTS_MSG;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sunil.skillstracker.exceptions.SkillCategoryException;
import com.sunil.skillstracker.exceptions.SkillCategoryNotFoundException;
import com.sunil.skillstracker.exceptions.SkillException;
import com.sunil.skillstracker.exceptions.SkillNotFoundException;
import com.sunil.skillstracker.models.Skill;
import com.sunil.skillstracker.models.SkillCategory;
import com.sunil.skillstracker.models.SkillCategoryMap;
import com.sunil.skillstracker.repository.SkillCategoryMapRepository;
import com.sunil.skillstracker.repository.SkillCategoryRepository;
import com.sunil.skillstracker.repository.SkillRepository;

@Service
public class SkillsServiceImpl implements SkillsService {

  private final CommonService commonService;

  private final SkillRepository skillRepository;
  private final SkillCategoryRepository skillCategoryRepository;
  private final SkillCategoryMapRepository skillCategoryMapRepository;

  public SkillsServiceImpl(final CommonService commonService,
      final SkillRepository skillRepository,
      final SkillCategoryRepository skillCategoryRepository,
      final SkillCategoryMapRepository skillCategoryMapRepository) {
    this.commonService = commonService;
    this.skillRepository = skillRepository;
    this.skillCategoryRepository = skillCategoryRepository;
    this.skillCategoryMapRepository = skillCategoryMapRepository;
  }

  @Override
  public List<Skill> getAllSkills() {
    List<Skill> skills = skillRepository.findAll();
    if (skills == null || skills.isEmpty()) {
      throw new SkillNotFoundException(SKILLS_NOT_FOUND_MSG);
    }
    return skills;
  }

  @Override
  public Skill getSkill(final Long skillId) {
    return skillRepository.findById(skillId)
        .orElseThrow(() -> new SkillNotFoundException(String.format(SKILL_WITH_ID_NOT_FOUND_MSG, skillId)));
  }

  @Override
  public Optional<Skill> getSkillByName(final String skillName) {
    return skillRepository.findByNameIgnoreCase(skillName);
  }

  @Override
  public List<SkillCategory> getAllSkillCategories() {
    List<SkillCategory> skillCategories = skillCategoryRepository.findAll();
    if (skillCategories == null || skillCategories.isEmpty()) {
      throw new SkillCategoryNotFoundException(SKILL_CATEGORIES_NOT_FOUND_MSG);
    }
    return skillCategories;
  }

  @Override
  public SkillCategory getSkillCategory(final Long skillCategoryId) {
    return skillCategoryRepository.findById(skillCategoryId).orElseThrow(
        () -> new SkillCategoryNotFoundException(String.format(SKILL_CATEGORY_WITH_ID_NOT_FOUND_MSG, skillCategoryId)));
  }

  @Override
  public Optional<SkillCategory> getSkillCategoryByName(final String skillCategoryName) {
    return skillCategoryRepository.findByNameIgnoreCase(skillCategoryName);
  }

  @Transactional
  @Override
  public SkillCategory createSkillCategory(final SkillCategory skillCategory) {
    if (getSkillCategoryByName(skillCategory.getName()).isPresent()) {
      throw new SkillCategoryException(String.format(SKILL_CATEGORY_WITH_NAME_ALREADY_EXISTS_MSG, skillCategory.getName()));
    }

    if (skillCategory.getSkillCategoryMaps() != null && !skillCategory.getSkillCategoryMaps().isEmpty()) {
      List<SkillCategoryMap> maps = commonService.createSkillCategoryMaps(skillCategory.getSkillCategoryMaps());
      return maps != null && !maps.isEmpty() ? maps.get(0).getSkillCategory() : skillCategory;
    }

    return skillCategoryRepository.save(skillCategory);
  }

  @Transactional
  @Override
  public SkillCategory updateSkillCategory(final SkillCategory skillCategory) {
    if (skillCategory.getId() == null) {
      throw new SkillCategoryException(SKILL_CATEGORY_ID_NOT_PROVIDED_MSG);
    }
    return skillCategoryRepository.save(skillCategory);
  }

  @Transactional
  @Override
  public void deleteSkillCategory(final Long skillCategoryId) {
    skillCategoryRepository.delete(getSkillCategory(skillCategoryId));
  }

  @Override
  public Skill createSkill(final Skill skill) {
    Optional<Skill> skillTemp = getSkillByName(skill.getName());
    if (skillTemp.isPresent()) {
      throw new SkillException(String.format(SKILL_WITH_NAME_ALREADY_EXISTS_MSG, skill.getName()));
    }
    return skillRepository.save(skill);
  }

  @Transactional
  @Override
  public Skill updateSkill(final Skill skill) {
    if (skill.getId() == null) {
      throw new SkillException(SKILL_ID_NOT_PROVIDED_MSG);
    }
    return skillRepository.save(skill);
  }

  @Transactional
  @Override
  public void deleteSkill(final Long skillId) {
    skillRepository.delete(getSkill(skillId));
  }

}
