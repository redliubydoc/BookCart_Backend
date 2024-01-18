package com.raj.ms.product.dto.response;

import java.util.StringJoiner;

public class GenreResponseData {
  private String Code;
  private String Name;

  public GenreResponseData() {
  }

  public GenreResponseData(String code, String name) {
    Code = code;
    Name = name;
  }

  public String getCode() {
    return Code;
  }

  public void setCode(String code) {
    Code = code;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", GenreResponseData.class.getSimpleName() + "[", "]")
      .add("Code=" + Code)
      .add("Name='" + Name + "'")
      .toString();
  }
}
