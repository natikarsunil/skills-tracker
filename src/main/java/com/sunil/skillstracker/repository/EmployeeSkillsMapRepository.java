package com.sunil.skillstracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunil.skillstracker.models.EmployeeSkillMap;
import com.sunil.skillstracker.models.SkillCategoryMap;

@Repository
public interface EmployeeSkillsMapRepository extends JpaRepository<EmployeeSkillMap, Long> {

}
