package com.sunil.skillstracker.controllers;

import static com.sunil.skillstracker.constants.Routes.BASE_APP_URI;
import static com.sunil.skillstracker.constants.Routes.SKILLS_URI;
import static com.sunil.skillstracker.constants.Routes.SKILL_CATEGORIES_MAP_URI;
import static com.sunil.skillstracker.constants.Routes.SKILL_CATEGORIES_URI;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.skillstracker.models.MessageResponse;
import com.sunil.skillstracker.models.Skill;
import com.sunil.skillstracker.models.SkillCategory;
import com.sunil.skillstracker.models.SkillCategoryMap;
import com.sunil.skillstracker.models.requests.RequestMapper;
import com.sunil.skillstracker.models.requests.SkillCategoryRequest;
import com.sunil.skillstracker.services.CommonService;
import com.sunil.skillstracker.services.SkillsService;

@RestController
@RequestMapping(BASE_APP_URI)
public class SkillsController {

  private final SkillsService skillsService;

  private final CommonService commonService;

  private final RequestMapper requestMapper;

  public SkillsController(SkillsService skillsService,
      final CommonService commonService, final RequestMapper requestMapper) {
    this.skillsService = skillsService;
    this.commonService = commonService;
    this.requestMapper = requestMapper;
  }

  @GetMapping(SKILLS_URI)
  public ResponseEntity<?> getAllSkills() {
    return ResponseEntity.ok(skillsService.getAllSkills());
  }

  @GetMapping(SKILL_CATEGORIES_URI)
  public ResponseEntity<?> getAllSkillCategories() {
    List<SkillCategoryRequest> skillCategoryRequests = skillsService.getAllSkillCategories().parallelStream()
        .map(category -> requestMapper.mapToSkillCategoryRequest(category)).collect(
        Collectors.toList());
    return ResponseEntity.ok(skillCategoryRequests);
  }

  @GetMapping(SKILL_CATEGORIES_URI + "/{categoryId}")
  public ResponseEntity<?> getSkillCategory(@PathVariable("categoryId") Long categoryId) {
    return ResponseEntity.ok(skillsService.getSkillCategory(categoryId));
  }

  @GetMapping(SKILLS_URI + "/{skillId}")
  public ResponseEntity<?> getSkill(@PathVariable("skillId") Long skillId) {
    return ResponseEntity.ok(skillsService.getSkill(skillId));
  }

  @PostMapping(SKILLS_URI)
  public ResponseEntity<?> createSkill(@Valid @RequestBody Skill skill) {
    skill = skillsService.createSkill(skill);
    return ResponseEntity.ok(skill);
  }

  @PostMapping(SKILL_CATEGORIES_URI)
  public ResponseEntity<?> createSkillCategory(@Valid @RequestBody SkillCategoryRequest skillCategoryRequest) {
    SkillCategory skillCategory = skillsService.createSkillCategory(requestMapper.mapToSkillCategory(skillCategoryRequest));
    return ResponseEntity.ok(requestMapper.mapToSkillCategoryRequest(skillCategory));
  }

  @PutMapping(SKILLS_URI)
  public ResponseEntity<?> updateSkill(@Valid @RequestBody Skill skill) {
    skill = skillsService.updateSkill(skill);
    return ResponseEntity.ok(skill);
  }

  @PutMapping(SKILL_CATEGORIES_URI)
  public ResponseEntity<?> updateSkillsCategory(@Valid @RequestBody SkillCategoryRequest skillCategoryRequest) {
    SkillCategory skillCategory = skillsService.updateSkillCategory(requestMapper.mapToSkillCategory(skillCategoryRequest));
    return ResponseEntity.ok(requestMapper.mapToSkillCategoryRequest(skillCategory));
  }

  @DeleteMapping(SKILLS_URI + "/{skillId}")
  public ResponseEntity<?> deleteSkill(@PathVariable("skillId") Long skillId) {
    skillsService.deleteSkill(skillId);
    MessageResponse messageResponse = new MessageResponse("Skill deleted");
    return ResponseEntity.ok(messageResponse);
  }

  @DeleteMapping(SKILL_CATEGORIES_URI + "/{skillCategoryId}")
  public ResponseEntity<?> deleteSkillCategory(@PathVariable("skillCategoryId") Long skillCategoryId) {
    skillsService.deleteSkillCategory(skillCategoryId);
    MessageResponse messageResponse = new MessageResponse("Skill category deleted");
    return ResponseEntity.ok(messageResponse);
  }

  @PostMapping(SKILL_CATEGORIES_MAP_URI)
  public ResponseEntity<?> mapSkillCategory(@Valid @RequestBody List<SkillCategoryMap> skillCategoryMaps) {
    skillCategoryMaps = commonService.createSkillCategoryMaps(skillCategoryMaps);
    return ResponseEntity.ok(skillCategoryMaps);
  }

  @GetMapping(SKILL_CATEGORIES_MAP_URI)
  public ResponseEntity<?> getSkillCategoryMap() {
    return ResponseEntity.ok(commonService.getSkillCategoryMaps());
  }

  @GetMapping(SKILL_CATEGORIES_MAP_URI + "/{skillCategoryMapId}")
  public ResponseEntity<?> getSkillCategoryMap(@PathVariable("skillCategoryMapId") Long skillCategoryMapId) {
    return ResponseEntity.ok(commonService.getSkillCategoryMap(skillCategoryMapId));
  }

}
