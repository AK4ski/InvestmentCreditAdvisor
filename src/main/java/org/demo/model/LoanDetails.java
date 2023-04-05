package org.demo.model;

import java.util.Objects;

public class LoanDetails {

  private String id;
  private String loanType;
  private String loanOriginator;
  private String scoreClass;
  private String guaranteedPrincipal;

  private LoanDetails(final LoanDetailsBuilder builder) {
    this.id = builder.rawDetails.getId();
    this.loanType = builder.rawDetails.getLoanType();
    this.loanOriginator = builder.rawDetails.getLoanOriginator();
    this.scoreClass = builder.rawDetails.getScoreClass();
    this.guaranteedPrincipal = builder.rawDetails.getGuaranteedPrincipal();
    this.status = builder.rawDetails.getStatus();
    this.availableForInvestment = builder.rawDetails.getAvailableForInvestment();
    this.premiumDiscount = builder.rawDetails.getPremiumDiscount();
    this.term = builder.term;
    this.installmentDetails = builder.installmentDetails;
    this.interestRate = builder.interestRate;
    this.price = builder.price;
  }

  private Term term;

  private InstallmentDetails installmentDetails;

  private String status;

  private double interestRate;

  private String availableForInvestment;

  private String premiumDiscount;

  private Price price;

  public String getId() {
    return id;
  }

  public String getLoanType() {
    return loanType;
  }

  public String getLoanOriginator() {
    return loanOriginator;
  }

  public String getScoreClass() {
    return scoreClass;
  }

  public String getGuaranteedPrincipal() {
    return guaranteedPrincipal;
  }

  public Term getTerm() {
    return term;
  }

  public InstallmentDetails getInstallmentDetails() {
    return installmentDetails;
  }

  public String getStatus() {
    return status;
  }

  public Double getInterestRate() {
    return interestRate;
  }

  public String getAvailableForInvestment() {
    return availableForInvestment;
  }

  public String getPremiumDiscount() {
    return premiumDiscount;
  }

  public Price getPrice() {
    return price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoanDetails that = (LoanDetails) o;
    return Objects.equals(id, that.id) && Objects.equals(loanType, that.loanType)
        && Objects.equals(loanOriginator, that.loanOriginator) && Objects.equals(scoreClass,
        that.scoreClass) && Objects.equals(guaranteedPrincipal, that.guaranteedPrincipal)
        && Objects.equals(term, that.term) && Objects.equals(installmentDetails, that.installmentDetails)
        && Objects.equals(status, that.status) && Objects.equals(interestRate, that.interestRate)
        && Objects.equals(availableForInvestment, that.availableForInvestment) && Objects.equals(
        premiumDiscount, that.premiumDiscount) && Objects.equals(price, that.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, loanType, loanOriginator, scoreClass, guaranteedPrincipal, term, installmentDetails, status,
        interestRate, availableForInvestment, premiumDiscount, price);
  }

  @Override
  public String toString() {
    return "LoanDetails{" +
        "id='" + id + '\'' +
        ", loanType='" + loanType + '\'' +
        ", loanOriginator='" + loanOriginator + '\'' +
        ", scoreClass='" + scoreClass + '\'' +
        ", guaranteedPrincipal='" + guaranteedPrincipal + '\'' +
        ", term=" + term +
        ", installmentDetails=" + installmentDetails +
        ", status='" + status + '\'' +
        ", interestRate=" + interestRate +
        ", availableForInvestment='" + availableForInvestment + '\'' +
        ", premiumDiscount='" + premiumDiscount + '\'' +
        ", price=" + price +
        '}';
  }

  public static class LoanDetailsBuilder {

    private RawLoanDetails rawDetails;
    private Price price;
    private Double interestRate;
    private InstallmentDetails installmentDetails;
    private Term term;

    public LoanDetailsBuilder() {
    }

    public LoanDetailsBuilder builder(RawLoanDetails rawDetails) {
      this.rawDetails = rawDetails;
      return this;
    }

    public LoanDetailsBuilder withPrice(Price price) {
      this.price = price;
      return this;
    }

    public LoanDetailsBuilder withInterestRate(Double interestRate) {
      this.interestRate = interestRate;
      return this;
    }

    public LoanDetailsBuilder withMaturity(InstallmentDetails installmentDetails) {
      this.installmentDetails = installmentDetails;
      return this;
    }

    public LoanDetailsBuilder withTerm(Term term) {
      this.term = term;
      return this;
    }

    public LoanDetails build() {
      return new LoanDetails(this);
    }
  }
}
