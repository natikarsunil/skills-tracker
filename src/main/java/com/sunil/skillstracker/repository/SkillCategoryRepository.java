package com.sunil.skillstracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunil.skillstracker.models.SkillCategory;

@Repository
public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {

  Optional<SkillCategory> findByNameIgnoreCase(String skillCategoryName);
}
