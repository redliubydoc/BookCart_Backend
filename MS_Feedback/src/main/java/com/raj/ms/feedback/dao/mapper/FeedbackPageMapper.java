package com.raj.ms.feedback.dao.mapper;

import com.raj.ms.feedback.dto.response.FeedbackPagedResponseData;
import com.raj.ms.feedback.dto.response.FeedbackResponseData;
import com.raj.ms.feedback.db.utils.ResultSetMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FeedbackPageMapper implements ResultSetMapper<FeedbackPagedResponseData> {

  @Override
  public void map(ResultSet resultSet, FeedbackPagedResponseData feedbackPagedResponseData) {

    boolean flag = true;

    try {
      while (resultSet.next()) {

        if (flag) {
          feedbackPagedResponseData.setAverageRating(resultSet.getDouble("average_rating"));
          feedbackPagedResponseData.setFeedbacksCount(resultSet.getInt("feedback_count"));
          feedbackPagedResponseData.setReviewsCount(resultSet.getInt("review_count"));
          feedbackPagedResponseData.setOneStarRatingsCount(resultSet.getInt("1_star_count"));
          feedbackPagedResponseData.setTwoStarRatingsCount(resultSet.getInt("2_star_count"));
          feedbackPagedResponseData.setThreeStarRatingsCount(resultSet.getInt("3_star_count"));
          feedbackPagedResponseData.setFourStarRatingsCount(resultSet.getInt("4_star_count"));
          feedbackPagedResponseData.setFiveStarRatingsCount(resultSet.getInt("5_star_count"));
          feedbackPagedResponseData.setItemsCount(resultSet.getInt("item_count"));

          flag = false;
        }

        FeedbackResponseData feedbackItem = new FeedbackResponseData();
        feedbackItem.setFeedbackId(resultSet.getInt("feedback_id"));
        feedbackItem.setUsername(resultSet.getString("username"));
        feedbackItem.setRating(resultSet.getInt("rating"));
        feedbackItem.setDateOfFeedback(resultSet.getDate("date_of_feedback"));
        feedbackItem.setReview(resultSet.getString("review"));

        feedbackPagedResponseData.getItems().add(feedbackItem);
      }
    }
    catch (SQLException e) {
      // TODO: robust exception handling
      e.printStackTrace();
    }
  }
}
