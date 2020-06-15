package com.sunil.skillstracker.models.requests;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.sunil.skillstracker.models.Skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

  private Long id;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  private String userName;

  private List<EmployeeSkillMapRequest> skillsMap = new ArrayList<>();
}
