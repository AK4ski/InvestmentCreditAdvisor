package org.demo.model;

public class Result {

  private final LoanDetails loanDetails;
  private final Yield yield;

  public Result(LoanDetails loanDetails, Yield yield) {
    this.loanDetails = loanDetails;
    this.yield = yield;
  }

  public LoanDetails getLoanDetails() {
    return loanDetails;
  }

  public Yield getYield() {
    return yield;
  }

  @Override
  public String toString() {
    return "Result{" + loanDetails +
        ", yield=" + yield +
        '}';
  }
}
