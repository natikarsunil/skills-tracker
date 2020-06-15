package com.sunil.skillstracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunil.skillstracker.models.SkillCategoryMap;

@Repository
public interface SkillCategoryMapRepository extends JpaRepository<SkillCategoryMap, Long> {

}
