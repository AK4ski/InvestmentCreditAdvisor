package org.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CompoundInterestCalculationServiceTest {

  private final CompoundInterestCalculationService service = new CompoundInterestCalculationService();

  @Test
  void calculateInterestOnceInYear() {
    int price = 100;
    double interestRate = 0.08;
    int timesInYear = 2;
    int years = 3;

    double result = service.calculateFutureValue(price, interestRate, timesInYear, years);

    assertEquals(126.53, result);
  }

  @Test
  void calculateInterestTwiceInYear() {
    int price = 1000;
    double interestRate = 0.04;
    int timesInYear = 2;
    int years = 5;

    double result = service.calculateFutureValue(price, interestRate, timesInYear, years);

    assertEquals(1218.99, result);
  }
}