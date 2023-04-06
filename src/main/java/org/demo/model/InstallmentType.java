package org.demo.model;

public enum InstallmentType {
  DAYS(1),
  WEEKS(7),
  MONTHS(30);

  private final int installmentTypeFactor;

  public int getInstallmentTypeFactor() {
    return installmentTypeFactor;
  }

  InstallmentType(int installmentTypeFactor) {
    this.installmentTypeFactor = installmentTypeFactor;
  }
}
