package com.raj.ms.feedback.dto.request;

import java.util.Date;
import java.util.StringJoiner;

public class FeedbackRequestDTO {

  private Integer feedbackId;
  private String isbn;
  private Integer userId;
  private String username;
  private Integer rating;
  private String review;

  public Integer getFeedbackId() {
    return feedbackId;
  }

  public void setFeedbackId(Integer feedbackId) {
    this.feedbackId = feedbackId;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", FeedbackRequestDTO.class.getSimpleName() + "[", "]")
      .add("feedbackId=" + feedbackId)
      .add("isbn='" + isbn + "'")
      .add("userId='" + userId + "'")
      .add("username='" + username + "'")
      .add("rating=" + rating)
      .add("review='" + review + "'")
      .toString();
  }
}
