package com.sunil.skillstracker.controllers;

import static com.sunil.skillstracker.constants.Routes.BASE_APP_URI;
import static com.sunil.skillstracker.constants.Routes.EMPLOYEES_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.sunil.skillstracker.enums.SkillLevel;
import com.sunil.skillstracker.enums.SkillType;
import com.sunil.skillstracker.models.Employee;
import com.sunil.skillstracker.models.Skill;
import com.sunil.skillstracker.models.SkillCategory;
import com.sunil.skillstracker.models.SkillCategoryMap;
import com.sunil.skillstracker.models.requests.EmployeeRequest;
import com.sunil.skillstracker.models.requests.EmployeeSkillMapRequest;
import com.sunil.skillstracker.models.requests.RequestMapper;
import com.sunil.skillstracker.services.CommonService;
import com.sunil.skillstracker.services.EmployeeService;

public class EmployeeControllerTest extends AbstractTest {

  @Autowired
  private CommonService commonService;

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private RequestMapper requestMapper;

  @Test
  public void testGetEmployees_ReturnsOk() throws Exception {
    String uri = BASE_APP_URI + EMPLOYEES_URI;

    Skill skill = new Skill("Java");
    SkillCategory skillCategory = new SkillCategory("Programming", SkillType.HARD_SKILL, null);
    SkillCategoryMap skillCategoryMap = new SkillCategoryMap(skill, skillCategory);
    List<SkillCategoryMap> skillCategoryMaps
        = commonService.createSkillCategoryMaps(Arrays.asList(skillCategoryMap));

    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setFirstName("first");
    employeeRequest.setLastName("last");
    employeeRequest.setUserName("firstlast");
    EmployeeSkillMapRequest employeeSkillMapRequest = new EmployeeSkillMapRequest();
    employeeSkillMapRequest.setSkillLevel(SkillLevel.EXPERT);
    employeeSkillMapRequest.setSkillCategoryMapId(skillCategoryMaps.get(0).getId());
    employeeRequest.getSkillsMap().add(employeeSkillMapRequest);

    employeeService.createEmployee(requestMapper.mapToEmployee(employeeRequest));

    getMockMvc().perform(get(uri))
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(buildDocument());
  }

  @Test
  public void testCreateEmployees_ReturnsOk() throws Exception {
    String uri = BASE_APP_URI + EMPLOYEES_URI;

    Skill skill = new Skill("Java");
    SkillCategory skillCategory = new SkillCategory("Programming", SkillType.HARD_SKILL, null);
    SkillCategoryMap skillCategoryMap = new SkillCategoryMap(skill, skillCategory);
    List<SkillCategoryMap> skillCategoryMaps
        = commonService.createSkillCategoryMaps(Arrays.asList(skillCategoryMap));

    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setFirstName("first");
    employeeRequest.setLastName("last");
    employeeRequest.setUserName("firstlast");
    EmployeeSkillMapRequest employeeSkillMapRequest = new EmployeeSkillMapRequest();
    employeeSkillMapRequest.setSkillLevel(SkillLevel.EXPERT);
    employeeSkillMapRequest.setSkillCategoryMapId(skillCategoryMaps.get(0).getId());
    employeeRequest.getSkillsMap().add(employeeSkillMapRequest);

    getMockMvc().perform(post(uri)
        .content(asJsonString(employeeRequest))
        .contentType(MediaType.APPLICATION_JSON)
        .contentType("application/json;charset=UTF-8"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(Matchers.containsString("Java")))
        .andDo(buildDocument());
  }

  @Test
  public void testUpdateEmployees_ReturnsOk() throws Exception {
    String uri = BASE_APP_URI + EMPLOYEES_URI;

    Skill skill = new Skill("Python");
    SkillCategory skillCategory = new SkillCategory("Programming", SkillType.HARD_SKILL, null);
    SkillCategoryMap skillCategoryMap = new SkillCategoryMap(skill, skillCategory);
    List<SkillCategoryMap> skillCategoryMaps
        = commonService.createSkillCategoryMaps(Arrays.asList(skillCategoryMap));

    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setFirstName("first");
    employeeRequest.setLastName("last");
    employeeRequest.setUserName("firstlast");

    Employee employee = employeeService.createEmployee(requestMapper.mapToEmployee(employeeRequest));

    employeeRequest = requestMapper.mapToEmployeeRequest(employee);

    EmployeeSkillMapRequest employeeSkillMapRequest = new EmployeeSkillMapRequest();
    employeeSkillMapRequest.setSkillLevel(SkillLevel.EXPERT);
    employeeSkillMapRequest.setSkillCategoryMapId(skillCategoryMaps.get(0).getId());
    employeeRequest.getSkillsMap().add(employeeSkillMapRequest);

    getMockMvc().perform(put(uri)
        .content(asJsonString(employeeRequest))
        .contentType(MediaType.APPLICATION_JSON)
        .contentType("application/json;charset=UTF-8"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(Matchers.containsString("Python")))
        .andDo(buildDocument());
  }
}
