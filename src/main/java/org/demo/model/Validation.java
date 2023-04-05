package org.demo.model;

public class Validation {

  private final boolean isValid;

  private final RawLoanDetails details;
  private final Price price;

  private Validation(
      boolean isValid,
      RawLoanDetails details,
      Price price) {
    this.isValid = isValid;
    this.details = details;
    this.price = price;
  }

  public static Validation toValid(
      RawLoanDetails details,
      Price price) {
    return new Validation(true, details, price);
  }

  public static Validation toInvalid() {
    return new Validation(false, null, null);
  }

  public boolean isValid() {
    return isValid;
  }

  public RawLoanDetails getDetails() {
    return details;
  }

  public Price getPrice() {
    return price;
  }
}
