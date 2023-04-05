package org.demo.model;

public class Result {

  private final LoanDetails loanDetails;
  private final double yield;

  public Result(LoanDetails loanDetails, double yield) {
    this.loanDetails = loanDetails;
    this.yield = yield;
  }

  public LoanDetails getLoanDetails() {
    return loanDetails;
  }

  public double getYield() {
    return yield;
  }

  @Override
  public String toString() {
    return "Result{" + loanDetails +
        ", yield=" + yield +
        '}';
  }
}
