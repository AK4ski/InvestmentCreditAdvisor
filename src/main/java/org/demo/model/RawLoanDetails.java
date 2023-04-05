package org.demo.model;

public class RawLoanDetails {

  private String id;
  private String loanType;
  private String loanOriginator;
  private String scoreClass;
  private String guaranteedPrincipal;
  private String term;
  private String installmentType;
  private String status;
  private String interestRate;
  private String availableForInvestment;
  private String premiumDiscount;
  private String price;

  public RawLoanDetails() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLoanType() {
    return loanType;
  }

  public void setLoanType(String loanType) {
    this.loanType = loanType;
  }

  public String getLoanOriginator() {
    return loanOriginator;
  }

  public void setLoanOriginator(String loanOriginator) {
    this.loanOriginator = loanOriginator;
  }

  public String getScoreClass() {
    return scoreClass;
  }

  public void setScoreClass(String scoreClass) {
    this.scoreClass = scoreClass;
  }

  public String getGuaranteedPrincipal() {
    return guaranteedPrincipal;
  }

  public void setGuaranteedPrincipal(String guaranteedPrincipal) {
    this.guaranteedPrincipal = guaranteedPrincipal;
  }

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public String getInstallmentType() {
    return installmentType;
  }

  public void setInstallmentType(String installmentType) {
    this.installmentType = installmentType;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(String interestRate) {
    this.interestRate = interestRate;
  }

  public String getAvailableForInvestment() {
    return availableForInvestment;
  }

  public void setAvailableForInvestment(String availableForInvestment) {
    this.availableForInvestment = availableForInvestment;
  }

  public String getPremiumDiscount() {
    return premiumDiscount;
  }

  public void setPremiumDiscount(String premiumDiscount) {
    this.premiumDiscount = premiumDiscount;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "RawLoanDetails{" +
        "id='" + id + '\'' +
        ", loanType='" + loanType + '\'' +
        ", loanOriginator='" + loanOriginator + '\'' +
        ", scoreClass='" + scoreClass + '\'' +
        ", guaranteedPrincipal='" + guaranteedPrincipal + '\'' +
        ", term='" + term + '\'' +
        ", installmentType='" + installmentType + '\'' +
        ", status='" + status + '\'' +
        ", interestRate='" + interestRate + '\'' +
        ", availableForInvestment='" + availableForInvestment + '\'' +
        ", premiumDiscount='" + premiumDiscount + '\'' +
        ", price='" + price + '\'' +
        '}';
  }
}
