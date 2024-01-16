package com.raj.ms.product.dto.response;

public class Response<T> {

  private final String status;
  private final String message;
  private final T data;
  private final boolean isPaginated;

  private Response(ResponseBuilder<T> builder) {

    this.status = builder.status.getValue();
    this.message = builder.message != null ? builder.message : builder.status.getValue();
    this.data = builder.data;
    this.isPaginated = data instanceof PagedResponseData<?>;
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

  public static class ResponseBuilder<U> {
    private ResponseStatus status;
    private String message;
    private U data;

    public ResponseBuilder<U> setStatus(ResponseStatus status) {
      this.status = status;
      return this;
    }

    public ResponseBuilder<U> setMessage(String message) {
      this.message = message;
      return this;
    }

    public ResponseBuilder<U> setData(U data) {
      this.data = data;
      return this;
    }

    public Response<U> build() {
      return new Response<>(this);
    }
  }
}
