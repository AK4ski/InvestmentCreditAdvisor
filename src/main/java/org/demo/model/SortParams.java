package org.demo.model;

public class SortParams {

  private final SortBy sortBy;
  private final SortDirection sortOrder;

  public SortParams(SortBy sortBy, SortDirection sortOrder) {
    this.sortBy = sortBy;
    this.sortOrder = sortOrder;
  }

  public SortBy getSortBy() {
    return sortBy;
  }

  public SortDirection getSortOrder() {
    return sortOrder;
  }

  public enum SortBy {
    MATURITY, TERM, INSTALLMENT
  }

  public enum SortDirection {
    ASC, DESC
  }
}
