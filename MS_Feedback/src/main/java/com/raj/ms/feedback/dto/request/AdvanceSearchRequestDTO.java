package com.raj.ms.feedback.dto.request;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class AdvanceSearchRequestDTO {
  private Filter filterBy;
  private Sort sortBy;
  private Order orderBy;
  public enum Order {ASC, DESC}
  public enum Filter {NO_OPP, RATING_01, RATING_02, RATING_03, RATING_04, RATING_05}
  public enum Sort {NO_OPP, RATING, FEEDBACK_DT}

  public AdvanceSearchRequestDTO() {
    this.filterBy = Filter.NO_OPP;
    this.sortBy = Sort.NO_OPP;
    this.orderBy = Order.DESC;
  }

  public Filter getFilterBy() {
    return filterBy;
  }

  public void setFilterBy(Filter filterBy) {
    this.filterBy = filterBy == null ? Filter.NO_OPP : filterBy;
  }

  public Sort getSortBy() {
    return sortBy;
  }

  public void setSortBy(Sort sortBy) {
    this.sortBy = sortBy == null ? Sort.NO_OPP : sortBy;
  }

  public Order getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(Order orderBy) {
    this.orderBy = orderBy == null ? Order.DESC : orderBy;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", AdvanceSearchRequestDTO.class.getSimpleName() + "[", "]")
      .add("filterBy=" + filterBy)
      .add("sortBy=" + sortBy)
      .add("orderBy=" + orderBy)
      .toString();
  }
}
