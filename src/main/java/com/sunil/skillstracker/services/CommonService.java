package com.sunil.skillstracker.services;

import java.util.List;

import com.sunil.skillstracker.models.SkillCategoryMap;

public interface CommonService {

  List<SkillCategoryMap> updateSkillCategoryMaps(final List<SkillCategoryMap> skillCategoryMaps);

  List<SkillCategoryMap> createSkillCategoryMaps(final List<SkillCategoryMap> skillCategoryMaps);

  List<SkillCategoryMap> getSkillCategoryMaps();

  SkillCategoryMap getSkillCategoryMap(final Long skillCategoryMapId);

  void deleteSkillCategoryMap(final Long skillCategoryMapId);
}
