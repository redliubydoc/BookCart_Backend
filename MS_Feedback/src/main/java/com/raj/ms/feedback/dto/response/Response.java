package com.raj.ms.feedback.dto.response;

import java.util.StringJoiner;

public class Response<T> {

  private String status;
  private String message;
  private T data;
  private Boolean isPaginated;

  public Response(String status, T data) {
    this.status = status;
    this.message = "";
    this.data = data;
    this.isPaginated = data instanceof PagedResponseData<?>;
  }

  public Response(String status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
    this.isPaginated = data instanceof PagedResponseData<?>;
  }

  public Response(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }

  public Boolean getPaginated() {
    return isPaginated;
  }
}
