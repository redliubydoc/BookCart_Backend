package com.raj.ms.product.dto.request;

import java.util.List;
import java.util.StringJoiner;

public class ProductAdvanceSearchRequestDTO {

  public enum SearchType {ISBN, TITLE, AUTHOR, GENRE}
  public enum FilterType {LANGUAGE, GENRE, PRICE}
  public enum SortType {PRICE, RATING, DATE_OF_RELEASE}
  public enum SortOrder {ASC, DESC}
  public static class Search {

    private final SearchType type;
    private final String query;

    public Search(SearchType type, String query) {
      this.type = type;
      this.query = query;
    }

    public SearchType getType() {
      return type;
    }

    public String getQuery() {
      return query;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Search.class.getSimpleName() + "[", "]")
        .add("type=" + type)
        .add("query=" + query)
        .toString();
    }
  }

  public static class Filter {
    private final FilterType type;
    private final String query;

    public Filter(FilterType type, String query) {
      this.type = type;
      this.query = query;
    }

    public FilterType getType() {
      return type;
    }

    public String getQuery() {
      return query;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Filter.class.getSimpleName() + "[", "]")
        .add("type=" + type)
        .add("query='" + query + "'")
        .toString();
    }
  }

  public static class Sort {
    private final SortType type;
    private final SortOrder order;

    public Sort(SortType type, SortOrder order) {
      this.type = type;
      this.order = order;
    }

    public SortType getType() {
      return type;
    }

    public SortOrder getOrder() {
      return order;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Sort.class.getSimpleName() + "[", "]")
        .add("type=" + type)
        .add("order=" + order)
        .toString();
    }
  }

  private final Search searchBy;
  private final List<Filter> filterBy;
  private final Sort sortBy;

  public ProductAdvanceSearchRequestDTO() {
    this(null, null, null);
  }

  public ProductAdvanceSearchRequestDTO(Search searchBy, List<Filter> filterBy, Sort sortBy) {
    this.searchBy = searchBy;
    this.filterBy = filterBy;
    this.sortBy = sortBy;
  }

  public Search getSearchBy() {
    return searchBy;
  }

  public List<Filter> getFilterBy() {
    return filterBy;
  }

  public Sort getSortBy() {
    return sortBy;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ProductAdvanceSearchRequestDTO.class.getSimpleName() + "[", "]")
      .add("searchBy=" + searchBy)
      .add("filterBy=" + filterBy)
      .add("sortBy=" + sortBy)
      .toString();
  }
}
