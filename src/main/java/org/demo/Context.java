package org.demo;

import static java.lang.Boolean.*;
import static java.util.Optional.ofNullable;
import static org.demo.model.InstallmentType.DAYS;
import static org.demo.model.InstallmentType.MONTHS;
import static org.demo.model.InstallmentType.WEEKS;

import java.util.EnumMap;
import java.util.Map;
import org.demo.model.InstallmentType;
import org.demo.model.SortParams;
import org.demo.model.SortParams.SortBy;
import org.demo.model.SortParams.SortDirection;

public class Context {

  private final String filePath;
  private boolean displayShort;
  private final SortParams sortParams;
  private final Map<InstallmentType, Integer> map;

  public Context() {
    this.filePath = System.getenv(EnvironmentVariables.FILE_PATH);

    ofNullable(System.getenv(EnvironmentVariables.DISPLAY_SHORT_RESULT))
        .ifPresentOrElse(display -> this.displayShort = parseBoolean(display),
            () -> this.displayShort = true);

    final SortBy sortBy = ofNullable(System.getenv(EnvironmentVariables.SORT_BY))
        .map(SortBy::valueOf).orElse(null);
    final SortDirection sortOrder = ofNullable(System.getenv(EnvironmentVariables.SORT_DIRECTION))
        .map(SortDirection::valueOf).orElse(null);
    this.sortParams = new SortParams(sortBy, sortOrder);

    this.map = initializeInstallmentMap();
  }

  public String getFilePath() {
    return filePath;
  }

  public boolean isDisplayShort() {
    return displayShort;
  }

  public SortParams getSortParams() {
    return sortParams;
  }

  public Map<InstallmentType, Integer> getMap() {
    return map;
  }

  private Map<InstallmentType, Integer> initializeInstallmentMap() {
    final Map<InstallmentType, Integer> map = new EnumMap<>(InstallmentType.class);
    int installmentTypeDays = ofNullable(System.getenv(EnvironmentVariables.INSTALLMENT_TYPE_DAYS)).map(
        Integer::valueOf).orElse(365);
    int installmentTypeWeeks = ofNullable(System.getenv(EnvironmentVariables.INSTALLMENT_TYPE_WEEKS))
        .map(Integer::valueOf).orElse(53);

    map.put(DAYS, installmentTypeDays);
    map.put(WEEKS, installmentTypeWeeks);
    map.put(MONTHS, 12);

    return map;
  }

  private static class EnvironmentVariables {

    private static final String FILE_PATH = "FILE_PATH";
    private static final String DISPLAY_SHORT_RESULT = "DISPLAY_SHORT_RESULT";

    private static final String SORT_BY = "SORT_BY";
    private static final String SORT_DIRECTION = "SORT_DIRECTION";

    private static final String INSTALLMENT_TYPE_DAYS = "INSTALLMENT_TYPE_DAYS";
    private static final String INSTALLMENT_TYPE_WEEKS = "INSTALLMENT_TYPE_WEEKS";
  }

}