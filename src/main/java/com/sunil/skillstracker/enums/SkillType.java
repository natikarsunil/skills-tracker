package com.sunil.skillstracker.enums;

import java.util.Arrays;
import java.util.Optional;

public enum SkillType implements ConvertableEnum<Integer>{

  HARD_SKILL(1),
  SOFT_SKILL(2);

  private Integer code;

  SkillType(final Integer code) {
    this.code = code;
  }

  public static Optional<SkillType> getSkillType(final int code) {
    return Arrays.stream(SkillType.values())
        .filter(value -> value.getCode().equals(code))
        .findFirst();
  }

  @Override
  public Integer getCode() {
    return code;
  }
}
