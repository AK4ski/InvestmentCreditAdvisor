package org.demo;

import static java.lang.Math.*;

public class CompoundInterestCalculationService {

  /*
   * p  - is the principal, or the invested amount;
   * r  - is the annual interest rate;
   * n  - is how many times the interest is received in a given year;
   * t   - the time in years; */
  public double calculateFutureValue(double P, double r, double n, double t) {
    double result = P * pow(1 + (r / n), n * t);
    return (double) round(result * 100) / 100;
  }
}
