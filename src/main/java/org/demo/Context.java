package org.demo;

import static java.lang.Boolean.*;
import static java.util.Optional.ofNullable;

import org.demo.model.SortParams;
import org.demo.model.SortParams.SortBy;
import org.demo.model.SortParams.SortDirection;

public class Context {

  private String filePath;
  private boolean displayShort;
  private int installmentTypeDays;
  private int installmentTypeWeeks;
  private int installmentTypeMonths;
  private SortParams sortParams;

  public Context() {
    this.filePath = System.getenv(EnvironmentVariables.FILE_PATH);

    this.displayShort = parseBoolean(System.getenv(EnvironmentVariables.DISPLAY_SHORT_RESULT));

    this.installmentTypeDays = ofNullable(System.getenv(EnvironmentVariables.INSTALLMENT_TYPE_DAYS)).map(
        Integer::valueOf).orElse(365);

    this.installmentTypeWeeks = ofNullable(System.getenv(EnvironmentVariables.INSTALLMENT_TYPE_WEEKS))
        .map(Integer::valueOf).orElse(53);

    this.installmentTypeMonths = ofNullable(System.getenv(EnvironmentVariables.INSTALLMENT_TYPE_MONTHS))
        .map(Integer::valueOf).orElse(12);

    final SortBy sortBy = ofNullable(System.getenv(EnvironmentVariables.SORT_BY))
        .map(SortBy::valueOf).orElse(null);
    final SortDirection sortOrder = ofNullable(System.getenv(EnvironmentVariables.SORT_ORDER))
        .map(SortDirection::valueOf).orElse(null);
    this.sortParams = new SortParams(sortBy, sortOrder);
  }


  public String getFilePath() {
    return filePath;
  }

  public boolean isDisplayShort() {
    return displayShort;
  }

  public int getInstallmentTypeDays() {
    return installmentTypeDays;
  }

  public int getInstallmentTypeWeeks() {
    return installmentTypeWeeks;
  }

  public int getInstallmentTypeMonths() {
    return installmentTypeMonths;
  }

  public SortParams getSortParams() {
    return sortParams;
  }

  private static class EnvironmentVariables {

    private static final String FILE_PATH = "FILE_PATH";
    private static final String DISPLAY_SHORT_RESULT = "DISPLAY_SHORT_RESULT";
    private static final String INSTALLMENT_TYPE_DAYS = "INSTALLMENT_TYPE_DAYS";
    private static final String INSTALLMENT_TYPE_WEEKS = "INSTALLMENT_TYPE_WEEKS";
    private static final String INSTALLMENT_TYPE_MONTHS = "INSTALLMENT_TYPE_MONTHS";
    private static final String SORT_BY = "SORT_BY";
    private static final String SORT_ORDER = "SORT_ORDER";
  }

}