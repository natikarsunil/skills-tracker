package com.sunil.skillstracker.enums;

import java.util.Arrays;
import java.util.Optional;

public enum SkillLevel implements ConvertableEnum<Integer> {

  AWARENESS(1),
  WORKING(2),
  PRACTITIONER(3),
  EXPERT(4);

  private Integer code;

  SkillLevel(final Integer code) {
    this.code = code;
  }

  public static Optional<SkillLevel> getSkillLevel(final int code) {
    return Arrays.stream(SkillLevel.values())
        .filter(value -> value.getCode().equals(code))
        .findFirst();
  }

  @Override
  public Integer getCode() {
    return code;
  }
}
