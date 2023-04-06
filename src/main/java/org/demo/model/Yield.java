package org.demo.model;

import static java.lang.Math.round;

import org.apache.poi.ss.formula.eval.NotImplementedException;

public class Yield {

  private double eur;
  private double bgn;

  public double getEur() {
    return eur;
  }

  public double getBgn() {
    return bgn;
  }

  public Yield(double price, Currency currency) {
    switch (currency) {
      case BGN:
        this.eur = getRoundedSum(price / 2);
        this.bgn = getRoundedSum(price);
        return;
      case EUR:
        this.eur = getRoundedSum(price);
        this.bgn = getRoundedSum(price * 2);
        return;
      default:
        throw new NotImplementedException("Could not instantiate Yield for specific currency ");
    }

  }

  private static double getRoundedSum(double result) {
    return (double) round(result * 100) / 100;
  }

  @Override
  public String toString() {
    return "{" +
        "eur=" + eur +
        ", bgn=" + bgn +
        '}';
  }
}
