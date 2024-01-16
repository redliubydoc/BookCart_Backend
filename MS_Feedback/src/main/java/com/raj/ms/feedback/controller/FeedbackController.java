package com.raj.ms.feedback.controller;

import com.raj.ms.feedback.dto.request.AdvanceSearchRequestDTO;
import com.raj.ms.feedback.dto.request.FeedbackRequestDTO;
import com.raj.ms.feedback.dto.response.FeedbackPagedResponseData;
import com.raj.ms.feedback.dto.response.FeedbackResponseData;
import com.raj.ms.feedback.dto.response.PagedResponseData;
import com.raj.ms.feedback.dto.response.Response;
import com.raj.ms.feedback.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/fdbms")
public class FeedbackController {

  private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);
  private final FeedbackService feedbackService;

  @Autowired
  public FeedbackController(FeedbackService feedbackService) {
    this.feedbackService = feedbackService;
  }

  @RequestMapping(method = RequestMethod.POST, path = "/{isbn}/feedback")
  public ResponseEntity<Response<FeedbackPagedResponseData>> getFeedbacksByIsbn(
    @PathVariable String isbn,
    @RequestParam(required = false, defaultValue = "1") Integer pageNo,
    @RequestParam(required = false, defaultValue = "0x7fffffff") Integer pageSz,
    @RequestBody(required = false) AdvanceSearchRequestDTO advanceSearch
  ) {

    if (advanceSearch == null) advanceSearch = new AdvanceSearchRequestDTO();

    LOGGER.trace("pageNo :: " + pageNo);
    LOGGER.trace("pageSz :: " + pageSz);

    if (advanceSearch != null) LOGGER.trace(advanceSearch.toString());

    FeedbackPagedResponseData responseData = new FeedbackPagedResponseData();
    responseData.setItems(new ArrayList<>());
    responseData.setPageNo(pageNo);
    responseData.setPageSz(pageSz);

    feedbackService.getFeedbacksByIsbn(isbn, pageNo, pageSz, advanceSearch, responseData);

    Response<FeedbackPagedResponseData> responseBody = new Response<>("success", responseData);
    return ResponseEntity.status(200).body(responseBody);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{isbn}/feedback/{feedbackId}")
  public ResponseEntity<Response<FeedbackResponseData>> getFeedbackByIsbnAndFeedbackId(
    @PathVariable String isbn,
    @PathVariable int feedbackId
  ) {

    LOGGER.trace("isbn :: " + isbn);
    LOGGER.trace("feedbackId :: " + feedbackId);

    FeedbackResponseData responseData = new FeedbackResponseData();
    responseData.setFeedbackId(feedbackId);

    feedbackService.getFeedbackByIsbnAndFeedbackId(isbn, responseData);

    LOGGER.trace("responseData :: " + responseData);

    Response<FeedbackResponseData> responseBody;

    if (responseData.getFeedbackId() == null) responseBody = new Response<>("error", "not found");
    else responseBody = new Response<>("success", responseData);

    return ResponseEntity.status(200).body(responseBody);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{isbn}/feedback")
  public ResponseEntity<Response<FeedbackResponseData>> addOrModifyFeedback(
          @PathVariable String isbn,
          @RequestBody FeedbackRequestDTO requestData
  ) {

    LOGGER.debug("requestData :: " + requestData);

    FeedbackResponseData responseData = new FeedbackResponseData();
    feedbackService.addFeedback(requestData, responseData);

    LOGGER.debug("responseData :: " + responseData);

    Response<FeedbackResponseData> responseBody;

    if (responseData.getFeedbackId() == null) responseBody = new Response<>("error", "not found");
    else responseBody = new Response<>("success", responseData);

    return ResponseEntity.status(200).body(responseBody);
  }
}
