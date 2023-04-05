package org.demo.model;

public class ShortResult {

  private final String id;
  private final double yield;
  private final Double interestRate;
  private final Price price;
  private final Term term;
  private final InstallmentDetails installment;

  public ShortResult(final Result result) {
    this.id = result.getLoanDetails().getId();
    this.yield = result.getYield();
    this.interestRate = result.getLoanDetails().getInterestRate();
    this.price = result.getLoanDetails().getPrice();
    this.term = result.getLoanDetails().getTerm();
    this.installment = result.getLoanDetails().getInstallmentDetails();
  }

  @Override
  public String toString() {
    return "ShortResult{" +
        "id='" + id + '\'' +
        ", yield=" + yield +
        ", interestRate=" + interestRate +
        ", price=" + price +
        ", term=" + term +
        ", installment=" + installment +
        '}';
  }
}
