package org.demo.model;

import java.util.Objects;

public class InstallmentDetails {

  private final int installment;
  private final InstallmentType installmentType;

  public InstallmentDetails(
      final Integer installment,
      final InstallmentType installmentType) {
    this.installment = installment;
    this.installmentType = installmentType;
  }

  public int getInstallment() {
    return installment;
  }

  public InstallmentType getInstallmentType() {
    return installmentType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InstallmentDetails installmentDetails = (InstallmentDetails) o;
    return Objects.equals(installment, installmentDetails.installment) && installmentType == installmentDetails.installmentType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(installment, installmentType);
  }

  @Override
  public String toString() {
    return "InstallmentDetails{" +
        "installment=" + installment +
        ", installmentType=" + installmentType +
        '}';
  }
}
