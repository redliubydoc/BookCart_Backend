package com.raj.ms.feedback.dto.response;

import java.util.List;

public class FeedbackPagedResponseData implements PagedResponseData<FeedbackResponseData> {

  private List<FeedbackResponseData> items;
  private int pageNo;
  private int pageSz;
  private int itemsCount;
  private double averageRating;
  private int feedbacksCount;
  private int reviewsCount;
  private int oneStarRatingsCount;
  private int twoStarRatingsCount;
  private int threeStarRatingsCount;
  private int fourStarRatingsCount;
  private int fiveStarRatingsCount;

  @Override
  public List<FeedbackResponseData> getItems() {
    return items;
  }

  public void setItems(List<FeedbackResponseData> items) {
    this.items = items;
  }

  @Override
  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  @Override
  public int getPageSz() {
    return pageSz;
  }

  public void setPageSz(int pageSz) {
    this.pageSz = pageSz;
  }

  @Override
  public int getItemsCount() {
    return itemsCount;
  }

  public void setItemsCount(int itemsCount) {
    this.itemsCount = itemsCount;
  }

  public double getAverageRating() {
    return averageRating;
  }

  public void setAverageRating(double averageRating) {
    this.averageRating = averageRating;
  }

  public int getFeedbacksCount() {
    return feedbacksCount;
  }

  public void setFeedbacksCount(int feedbacksCount) {
    this.feedbacksCount = feedbacksCount;
  }

  public int getOneStarRatingsCount() {
    return oneStarRatingsCount;
  }

  public void setOneStarRatingsCount(int oneStarRatingsCount) {
    this.oneStarRatingsCount = oneStarRatingsCount;
  }

  public int getTwoStarRatingsCount() {
    return twoStarRatingsCount;
  }

  public void setTwoStarRatingsCount(int twoStarRatingsCount) {
    this.twoStarRatingsCount = twoStarRatingsCount;
  }

  public int getThreeStarRatingsCount() {
    return threeStarRatingsCount;
  }

  public void setThreeStarRatingsCount(int threeStarRatingsCount) {
    this.threeStarRatingsCount = threeStarRatingsCount;
  }

  public int getFourStarRatingsCount() {
    return fourStarRatingsCount;
  }

  public void setFourStarRatingsCount(int fourStarRatingsCount) {
    this.fourStarRatingsCount = fourStarRatingsCount;
  }

  public int getFiveStarRatingsCount() {
    return fiveStarRatingsCount;
  }

  public void setFiveStarRatingsCount(int fiveStarRatingsCount) {
    this.fiveStarRatingsCount = fiveStarRatingsCount;
  }

  public int getReviewsCount() {
    return reviewsCount;
  }

  public void setReviewsCount(int reviewsCount) {
    this.reviewsCount = reviewsCount;
  }
}
