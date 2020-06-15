package com.sunil.skillstracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunil.skillstracker.models.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

  Optional<Skill> findByNameIgnoreCase(String skillName);
}
