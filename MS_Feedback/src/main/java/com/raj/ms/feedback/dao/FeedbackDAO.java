package com.raj.ms.feedback.dao;


import com.raj.ms.feedback.constant.QueryConstant;
import com.raj.ms.feedback.dao.mapper.FeedbackMapper;
import com.raj.ms.feedback.dao.mapper.FeedbackPageMapper;
import com.raj.ms.feedback.db.utils.QueryBuilder;
import com.raj.ms.feedback.dto.request.AdvanceSearchRequestDTO;
import com.raj.ms.feedback.dto.request.FeedbackRequestDTO;
import com.raj.ms.feedback.dto.response.FeedbackPagedResponseData;
import com.raj.ms.feedback.db.utils.PreparedStatementRunner;
import com.raj.ms.feedback.dto.response.FeedbackResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Types;

@Component
public class FeedbackDAO {

  private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackDAO.class);
  private final DataSource dataSource;
  private final PreparedStatementRunner preparedStatementRunner;
  private final FeedbackPageMapper feedbackPageMapper;
  private final FeedbackMapper feedbackMapper;

  public FeedbackDAO(DataSource dataSource, PreparedStatementRunner preparedStatementRunner, FeedbackPageMapper feedbackPageMapper, FeedbackMapper feedbackMapper) {
    this.dataSource = dataSource;
    this.preparedStatementRunner = preparedStatementRunner;
    this.feedbackPageMapper = feedbackPageMapper;
    this.feedbackMapper = feedbackMapper;
  }

  public void getFeedbacksByIsbn(String isbn, int pageNo, int pageSz, AdvanceSearchRequestDTO advanceSearch, FeedbackPagedResponseData responseData) {

    String whereConditions = "";
    String orderByConditions = "";

    switch (advanceSearch.getFilterBy()) {
      case RATING_01 -> whereConditions = "rating = 1";
      case RATING_02 -> whereConditions = "rating = 2";
      case RATING_03 -> whereConditions = "rating = 3";
      case RATING_04 -> whereConditions = "rating = 4";
      case RATING_05 -> whereConditions = "rating = 5";
      default -> whereConditions = "";
    }

    switch (advanceSearch.getSortBy()) {
      case RATING -> orderByConditions = "rating";
      default -> orderByConditions = "date_of_feedback";
    }

    switch (advanceSearch.getOrderBy()) {
      case DESC -> orderByConditions += " desc";
      default -> orderByConditions += " asc";
    }

    String query = QueryConstant.GET_FEEDBACKS_BY_ISBN;
    query = QueryBuilder.filterBy(query, whereConditions);
    query = QueryBuilder.orderBy(query, orderByConditions);
    query = QueryBuilder.paginateBy(query, pageNo, pageSz);

    Object[] params = new Object[2];
    params[0] = isbn;
    params[1] = isbn;

    preparedStatementRunner.executeQuery(query, params, dataSource, feedbackPageMapper, responseData);
  }

  public void getFeedbackByIsbnAndFeedbackId(String isbn, FeedbackResponseData responseData) {

    // TODO: null check
    String query = QueryConstant.GET_FEEDBACKS_BY_ISBN_AND_FEEDBACK_ID;

    Object[] params = new Object[2];
    params[0] = isbn;
    params[1] = responseData.getFeedbackId();

    preparedStatementRunner.executeQuery(query, params, dataSource, feedbackMapper, responseData);
  }

  public void addFeedback(FeedbackRequestDTO requestData, FeedbackResponseData responseData) {

    // TODO: null check
    String query = QueryConstant.PR_INSERT_FEEDBACK;

    Object[] inParams = new Object[5];
    inParams[0] = requestData.getIsbn();
    inParams[1] = requestData.getUserId();
    inParams[2] = requestData.getUsername();
    inParams[3] = requestData.getRating();
    inParams[4] = requestData.getReview();

    int[] outParamTypes = new int[2];
    outParamTypes[0] = Types.REF_CURSOR;
    outParamTypes[1] = Types.VARCHAR;

    preparedStatementRunner.executeProcedure(query, inParams, outParamTypes, dataSource, feedbackMapper, responseData);
  }

  public void updateFeedback(FeedbackRequestDTO requestData) {

  }
}

