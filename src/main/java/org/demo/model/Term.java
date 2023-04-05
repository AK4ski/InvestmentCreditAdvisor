package org.demo.model;

import java.util.Objects;

public class Term {

  private final int remainingInstallments;
  private final int repaidInstallments;

  public Term(Integer remainingInstallments, Integer repaidInstallments) {
    this.remainingInstallments = remainingInstallments;
    this.repaidInstallments = repaidInstallments;
  }

  public int getRemainingInstallments() {
    return remainingInstallments;
  }

  public int getRepaidInstallments() {
    return repaidInstallments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Term term = (Term) o;
    return Objects.equals(remainingInstallments, term.remainingInstallments) && Objects.equals(
        repaidInstallments, term.repaidInstallments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(remainingInstallments, repaidInstallments);
  }

  @Override
  public String toString() {
    return "Term{" +
        "remainingInstallments=" + remainingInstallments +
        ", repaidInstallments=" + repaidInstallments +
        '}';
  }
}
