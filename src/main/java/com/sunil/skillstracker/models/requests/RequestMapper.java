package com.sunil.skillstracker.models.requests;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sunil.skillstracker.models.Employee;
import com.sunil.skillstracker.models.EmployeeSkillMap;
import com.sunil.skillstracker.models.Skill;
import com.sunil.skillstracker.models.SkillCategory;
import com.sunil.skillstracker.models.SkillCategoryMap;
import com.sunil.skillstracker.services.CommonService;

@Component
public class RequestMapper {

  private final CommonService commonService;

  public RequestMapper(final CommonService commonService) {
    this.commonService = commonService;
  }


  public SkillCategoryRequest mapToSkillCategoryRequest(final SkillCategory skillCategory){
    SkillCategoryRequest request = new SkillCategoryRequest();
    request.setId(skillCategory.getId());
    request.setSkillType(skillCategory.getSkillType());
    request.setName(skillCategory.getName());
    List<Skill> skills = new ArrayList<>();
    if(skillCategory.getSkillCategoryMaps()!=null){
      skillCategory.getSkillCategoryMaps()
          .stream().filter(map -> map.getSkill()!=null)
          .forEach(map -> {
            skills.add(map.getSkill());
          });
      request.setSkills(skills);
    }
    return request;
  }

  public SkillCategory mapToSkillCategory(final SkillCategoryRequest skillCategoryRequest){
    SkillCategory skillCategory = new SkillCategory();
    skillCategory.setId(skillCategoryRequest.getId());
    skillCategory.setSkillType(skillCategoryRequest.getSkillType());
    skillCategory.setName(skillCategoryRequest.getName());
    if(skillCategoryRequest.getSkills()!=null){
      skillCategoryRequest.getSkills()
          .forEach(skill -> skillCategory.getSkillCategoryMaps().add(new SkillCategoryMap(skill, skillCategory)));
    }
    return skillCategory;
  }

  public Employee mapToEmployee(final EmployeeRequest employeeRequest){
    Employee employee = new Employee();
    employee.setId(employeeRequest.getId());
    employee.setUserName(employeeRequest.getUserName());
    employee.setFirstName(employeeRequest.getFirstName());
    employee.setLastName(employeeRequest.getLastName());
    if(employeeRequest.getSkillsMap()!=null
        && !employeeRequest.getSkillsMap().isEmpty()){
      employeeRequest.getSkillsMap().forEach(skillMap -> {
        SkillCategoryMap skillCategoryMap = commonService.getSkillCategoryMap(skillMap.getSkillCategoryMapId());
        EmployeeSkillMap employeeSkillMap = new EmployeeSkillMap(skillCategoryMap, skillMap.getSkillLevel());
        employeeSkillMap.setId(skillMap.getId());
        employee.getEmployeeSkillMaps().add(employeeSkillMap);
      });
    }
    return employee;
  }

  public EmployeeRequest mapToEmployeeRequest(final Employee employee){
    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setId(employee.getId());
    employeeRequest.setUserName(employee.getUserName());
    employeeRequest.setFirstName(employee.getFirstName());
    employeeRequest.setLastName(employee.getLastName());
    List<EmployeeSkillMapRequest> esMapRequest = new ArrayList<>();
    employee.getEmployeeSkillMaps().forEach(esMap -> {
      esMapRequest.add(mapToEmployeeSkillMapRequest(esMap));
    });
    employeeRequest.setSkillsMap(esMapRequest);
    return employeeRequest;
  }

  public EmployeeSkillMapRequest mapToEmployeeSkillMapRequest(EmployeeSkillMap employeeSkillMap){
    EmployeeSkillMapRequest request = new EmployeeSkillMapRequest();
    request.setId(employeeSkillMap.getId());
    request.setSkillLevel(employeeSkillMap.getSkillLevel());
    request.setSkillCategoryMapId(employeeSkillMap.getSkillCategoryMap().getId());
    return request;
  }
}
