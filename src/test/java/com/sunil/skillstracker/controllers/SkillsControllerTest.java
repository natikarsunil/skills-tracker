package com.sunil.skillstracker.controllers;

import static com.sunil.skillstracker.constants.Routes.BASE_APP_URI;
import static com.sunil.skillstracker.constants.Routes.SKILLS_URI;
import static com.sunil.skillstracker.constants.Routes.SKILL_CATEGORIES_MAP_URI;
import static com.sunil.skillstracker.constants.Routes.SKILL_CATEGORIES_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.sunil.skillstracker.enums.SkillType;
import com.sunil.skillstracker.models.Skill;
import com.sunil.skillstracker.models.SkillCategory;
import com.sunil.skillstracker.models.SkillCategoryMap;
import com.sunil.skillstracker.models.requests.RequestMapper;
import com.sunil.skillstracker.models.requests.SkillCategoryRequest;
import com.sunil.skillstracker.services.CommonService;
import com.sunil.skillstracker.services.SkillsService;

public class SkillsControllerTest extends AbstractTest {

  @Autowired
  private SkillsService skillsService;

  @Autowired
  private RequestMapper requestMapper;

  @Autowired
  private CommonService commonService;

  @Test
  public void testGetSkills_ReturnsOk() throws Exception {
    String uri = BASE_APP_URI + SKILLS_URI;
    Skill skill = new Skill(null, "Java");
    skillsService.createSkill(skill);
    getMockMvc().perform(get(uri))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(buildDocument());
  }

  @Test
  public void testCreateSkills_ReturnsOk() throws Exception {
    String uri = BASE_APP_URI + SKILLS_URI;
    Skill skill = new Skill(null, "Java");
    getMockMvc().perform(post(uri)
        .content(asJsonString(skill))
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(Matchers.containsString("Java")))
        .andDo(buildDocument());
  }

  @Test
  public void testCreateSkillCategories_ReturnsOk() throws Exception {
    String uri = BASE_APP_URI + SKILL_CATEGORIES_URI;
    SkillCategoryRequest request = buildSkillCategoryRequest("DevOps", "AWS");

    getMockMvc().perform(post(uri)
        .content(asJsonString(request))
        .contentType("application/json;charset=UTF-8"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(Matchers.containsString("DevOps")))
        .andExpect(content().string(Matchers.containsString("AWS")))
        .andDo(buildDocument());
  }

  @Test
  public void testGetSkillCategories_ReturnsOk() throws Exception {
    String uri = BASE_APP_URI + SKILL_CATEGORIES_URI;
    skillsService.createSkillCategory(requestMapper.mapToSkillCategory(buildSkillCategoryRequest("Programming", "Java")));
    getMockMvc().perform(get(uri))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(buildDocument());
  }

  @Test
  public void testGetSkillCategoryMap_ReturnsOk() throws Exception {
    SkillCategoryRequest request = buildSkillCategoryRequest("Communication", "English");
    skillsService.createSkillCategory(requestMapper.mapToSkillCategory(request));

    String uri = BASE_APP_URI + SKILL_CATEGORIES_MAP_URI + "/4";
    getMockMvc().perform(get(uri))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(Matchers.containsString("Communication")))
        .andExpect(content().string(Matchers.containsString("English")))
        .andDo(buildDocument());
  }

  private SkillCategoryRequest buildSkillCategoryRequest(String categoryName, String skillName){
    SkillCategoryRequest request = new SkillCategoryRequest();
    request.setName(categoryName);
    request.setSkillType(SkillType.HARD_SKILL);
    List<Skill> skills = new ArrayList<>();
    skills.add(new Skill(skillName));
    request.setSkills(skills);
    return request;
  }

  @Test
  public void testCreateSkillCategoryMaps_ReturnsOk() throws Exception {
    String uri = BASE_APP_URI + SKILL_CATEGORIES_MAP_URI;
    Skill skill = new Skill("Java");
    SkillCategory skillCategory = new SkillCategory("Programming", SkillType.HARD_SKILL, null);

    List<SkillCategoryMap> skillCategoryMaps = new ArrayList<>();
    SkillCategoryMap skillCategoryMap = new SkillCategoryMap(skill, skillCategory);
    skillCategoryMaps.add(skillCategoryMap);

    getMockMvc().perform(post(uri)
        .content(asJsonString(skillCategoryMaps))
        .contentType("application/json;charset=UTF-8"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(Matchers.containsString("Programming")))
        .andExpect(content().string(Matchers.containsString("Java")))
        .andDo(buildDocument());
  }
}
