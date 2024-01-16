package com.raj.ms.product.dto.response;

import java.util.StringJoiner;

public class LanguageResponseData {
  private String code;
  private String name;

  public LanguageResponseData() {
  }

  public LanguageResponseData(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", LanguageResponseData.class.getSimpleName() + "[", "]")
      .add("code='" + code + "'")
      .add("name='" + name + "'")
      .toString();
  }
}
