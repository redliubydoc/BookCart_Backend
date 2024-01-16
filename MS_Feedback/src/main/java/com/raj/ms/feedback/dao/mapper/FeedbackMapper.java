package com.raj.ms.feedback.dao.mapper;

import com.raj.ms.feedback.dto.response.FeedbackResponseData;
import com.raj.ms.feedback.db.utils.ResultSetMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FeedbackMapper implements ResultSetMapper<FeedbackResponseData> {

  @Override
  public void map(ResultSet resultSet, FeedbackResponseData feedbackResponseData) {

    try {
      resultSet.next();
      feedbackResponseData.setFeedbackId(resultSet.getInt("feedback_id"));
      feedbackResponseData.setUsername(resultSet.getString("username"));
      feedbackResponseData.setRating(resultSet.getInt("rating"));
      feedbackResponseData.setDateOfFeedback(resultSet.getDate("date_of_feedback"));
      feedbackResponseData.setReview(resultSet.getString("review"));
    }
    catch (SQLException e) {

      feedbackResponseData.setFeedbackId(null);
      // TODO: robust exception handling
      e.printStackTrace();
    }
  }
}
