package com.raj.ms.feedback.dto.response;

import java.util.Date;
import java.util.StringJoiner;

public class FeedbackResponseData {

  private Integer feedbackId;
  private String username;
  private Integer rating;
  private Date dateOfFeedback;
  private String review;

  public Integer getFeedbackId() {
    return feedbackId;
  }

  public void setFeedbackId(Integer feedbackId) {
    this.feedbackId = feedbackId;
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

  public Date getDateOfFeedback() {
    return dateOfFeedback;
  }

  public void setDateOfFeedback(Date dateOfFeedback) {
    this.dateOfFeedback = dateOfFeedback;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", FeedbackResponseData.class.getSimpleName() + "[", "]")
      .add("feedbackId=" + feedbackId)
      .add("username='" + username + "'")
      .add("rating=" + rating)
      .add("dateOfFeedback=" + dateOfFeedback)
      .add("review='" + review + "'")
      .toString();
  }
}
