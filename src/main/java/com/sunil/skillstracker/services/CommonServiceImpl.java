package com.sunil.skillstracker.services;

import static com.sunil.skillstracker.constants.Messages.SKILL_CAT_MAP_ID_NOT_FOUND_MSG;
import static com.sunil.skillstracker.constants.Messages.SKILL_CAT_MAP_ID_NOT_PROVIDED_MSG;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sunil.skillstracker.exceptions.SkillCategoryException;
import com.sunil.skillstracker.exceptions.SkillCategoryNotFoundException;
import com.sunil.skillstracker.models.Skill;
import com.sunil.skillstracker.models.SkillCategory;
import com.sunil.skillstracker.models.SkillCategoryMap;
import com.sunil.skillstracker.repository.SkillCategoryMapRepository;
import com.sunil.skillstracker.repository.SkillCategoryRepository;
import com.sunil.skillstracker.repository.SkillRepository;

@Service
public class CommonServiceImpl implements CommonService{
  private final SkillRepository skillRepository;
  private final SkillCategoryRepository skillCategoryRepository;
  private final SkillCategoryMapRepository skillCategoryMapRepository;

  public CommonServiceImpl(final SkillRepository skillRepository,
      final SkillCategoryRepository skillCategoryRepository,
      final SkillCategoryMapRepository skillCategoryMapRepository) {
    this.skillRepository = skillRepository;
    this.skillCategoryRepository = skillCategoryRepository;
    this.skillCategoryMapRepository = skillCategoryMapRepository;
  }

  @Override
  public List<SkillCategoryMap> createSkillCategoryMaps(final List<SkillCategoryMap> skillCategoryMaps) {
    skillCategoryMaps.forEach(map -> saveSkillCategory(map));
    return skillCategoryMapRepository.saveAll(skillCategoryMaps);
  }

  @Override
  public List<SkillCategoryMap> updateSkillCategoryMaps(final List<SkillCategoryMap> skillCategoryMaps) {
    skillCategoryMaps.forEach(map -> {
      if(map.getId()==null){
        throw new SkillCategoryException(SKILL_CAT_MAP_ID_NOT_PROVIDED_MSG);
      }
      saveSkillCategory(map);
    });
    return skillCategoryMapRepository.saveAll(skillCategoryMaps);
  }

  private void saveSkillCategory(SkillCategoryMap map){
    Skill skill = map.getSkill();
    Optional<Skill> skillFromDb = skillRepository.findByNameIgnoreCase(skill.getName());
    if(skillFromDb.isPresent()){
      map.setSkill(skillFromDb.get());
    }else{
      skill = skillRepository.save(map.getSkill());
      map.setSkill(skill);
    }

    SkillCategory skillCategory = map.getSkillCategory();
    Optional<SkillCategory> skillCategoryFromDb = skillCategoryRepository.findByNameIgnoreCase(skillCategory.getName());
    if(skillCategoryFromDb.isPresent()){
      map.setSkillCategory(skillCategoryFromDb.get());
    }else{
      skillCategory = skillCategoryRepository.save(skillCategory);
      map.setSkillCategory(skillCategory);
    }
  }

  @Override
  public List<SkillCategoryMap> getSkillCategoryMaps() {
    return skillCategoryMapRepository.findAll();
  }

  @Override
  public SkillCategoryMap getSkillCategoryMap(final Long skillCategoryMapId) {
    return skillCategoryMapRepository.findById(skillCategoryMapId)
        .orElseThrow(() -> new SkillCategoryNotFoundException(String.format(SKILL_CAT_MAP_ID_NOT_FOUND_MSG, skillCategoryMapId)));
  }

  @Override
  public void deleteSkillCategoryMap(final Long skillCategoryMapId) {
    skillCategoryMapRepository.delete(getSkillCategoryMap(skillCategoryMapId));
  }
}
