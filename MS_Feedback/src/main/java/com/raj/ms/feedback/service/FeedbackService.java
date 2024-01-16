package com.raj.ms.feedback.service;

import com.raj.ms.feedback.dao.FeedbackDAO;
import com.raj.ms.feedback.dto.request.AdvanceSearchRequestDTO;
import com.raj.ms.feedback.dto.request.FeedbackRequestDTO;
import com.raj.ms.feedback.dto.response.FeedbackPagedResponseData;
import com.raj.ms.feedback.dto.response.FeedbackResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

  private final FeedbackDAO feedbackDAO;

  @Autowired
  public FeedbackService(FeedbackDAO feedbackDAO) {
    this.feedbackDAO = feedbackDAO;
  }

  public void getFeedbacksByIsbn(String isbn, int pageNo, int pageSz, AdvanceSearchRequestDTO advanceSearch, FeedbackPagedResponseData responseData) {
    feedbackDAO.getFeedbacksByIsbn(isbn, pageNo, pageSz, advanceSearch, responseData);
  }

  public void getFeedbackByIsbnAndFeedbackId(String isbn, FeedbackResponseData responseData) {
    feedbackDAO.getFeedbackByIsbnAndFeedbackId(isbn, responseData);
  }

  public void addFeedback(FeedbackRequestDTO requestDate, FeedbackResponseData responseData) {
    feedbackDAO.addFeedback(requestDate, responseData);
  }

  public void updateFeedback(FeedbackRequestDTO feedbackRequest) {
    feedbackDAO.updateFeedback(feedbackRequest);
  }
}
