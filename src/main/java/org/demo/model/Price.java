package org.demo.model;

import java.util.Objects;

public class Price {
  private final double value;
  private final Currency currency;

  public Price(double value, Currency currency) {
    this.value = value;
    this.currency = currency;
  }

  public double getValue() {
    return value;
  }

  public Currency getCurrency() {
    return currency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Price price = (Price) o;
    return Objects.equals(value, price.value) && currency == price.currency;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, currency);
  }

  @Override
  public String toString() {
    return "Price{" +
        "value=" + value +
        ", currency=" + currency +
        '}';
  }
}
