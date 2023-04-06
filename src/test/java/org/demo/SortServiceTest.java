package org.demo;

import static java.util.UUID.randomUUID;
import static org.demo.model.Currency.*;
import static org.demo.model.InstallmentType.*;
import static org.demo.model.SortParams.SortBy.*;
import static org.demo.model.SortParams.SortDirection.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.demo.model.InstallmentDetails;
import org.demo.model.LoanDetails;
import org.demo.model.LoanDetails.LoanDetailsBuilder;
import org.demo.model.Price;
import org.demo.model.RawLoanDetails;
import org.demo.model.Result;
import org.demo.model.SortParams;
import org.demo.model.Term;
import org.junit.jupiter.api.Test;

class SortServiceTest {

  private final SortService service = new SortService();

  @Test
  void defaultSortByYieldDesc() {
    InstallmentDetails installment1 = new InstallmentDetails(7, DAYS);
    InstallmentDetails installment2 = new InstallmentDetails(14, DAYS);
    InstallmentDetails installment3 = new InstallmentDetails(1, MONTHS);
    LoanDetails loanDetails1 = buildLoanDetails(10, installment1);
    LoanDetails loanDetails2 = buildLoanDetails(10, installment2);
    LoanDetails loanDetails3 = buildLoanDetails(10, installment3);
    Result resultWithHighestYield = new Result(loanDetails3, 1.75);
    Result resultWithLowestYield = new Result(loanDetails1, 0.4);
    Result resultWithAverageYield = new Result(loanDetails2, 0.8);
    SortParams emptySortParams = new SortParams(null, null);
    List<Result> resultList = List.of(resultWithHighestYield, resultWithLowestYield, resultWithAverageYield);
    List<Result> expected = List.of(resultWithHighestYield, resultWithAverageYield, resultWithLowestYield);

    List<Result> actual = service.sort(resultList, emptySortParams);

    assertEquals(expected, actual);
  }

  @Test
  void sortByYieldAsc() {
    InstallmentDetails installment = new InstallmentDetails(7, DAYS);
    LoanDetails loanDetails1 = buildLoanDetails(10, installment);
    LoanDetails loanDetails2 = buildLoanDetails(100, installment);
    LoanDetails loanDetails3 = buildLoanDetails(1000, installment);
    Result resultWithHighestYield = new Result(loanDetails3, 1.75);
    Result resultWithLowestYield = new Result(loanDetails1, 0.4);
    Result resultWithAverageYield = new Result(loanDetails2, 0.8);
    SortParams ascendigSortParams = new SortParams(null, ASC);
    List<Result> resultList = List.of(resultWithHighestYield, resultWithLowestYield, resultWithAverageYield);
    List<Result> expected = List.of(resultWithLowestYield, resultWithAverageYield, resultWithHighestYield);

    List<Result> actual = service.sort(resultList, ascendigSortParams);

    assertEquals(expected, actual);
  }

  @Test
  void defaultSortByInstallmentDesc() {
    int remainingInstallments = 10;
    InstallmentDetails installment1 = new InstallmentDetails(7, DAYS);
    InstallmentDetails installment2 = new InstallmentDetails(14, DAYS);
    InstallmentDetails installment3 = new InstallmentDetails(1, MONTHS);
    LoanDetails loanDetails1 = buildLoanDetails(remainingInstallments, installment1);
    LoanDetails loanDetails2 = buildLoanDetails(remainingInstallments, installment2);
    LoanDetails loanDetails3 = buildLoanDetails(remainingInstallments, installment3);
    Result resultWithHighestInstallment = new Result(loanDetails3, 1);
    Result resultWithLowestInstallment = new Result(loanDetails1, 1);
    Result resultWithAverageInstallment = new Result(loanDetails2, 1);
    SortParams sortParams = new SortParams(INSTALLMENT, DESC);
    List<Result> resultList = List.of(resultWithHighestInstallment, resultWithLowestInstallment,
        resultWithAverageInstallment);
    List<Result> expected = List.of(resultWithHighestInstallment, resultWithAverageInstallment,
        resultWithLowestInstallment);

    List<Result> actual = service.sort(resultList, sortParams);

    assertEquals(expected, actual);
  }

  @Test
  void defaultSortByInstallmentAsc() {
    int remainingInstallments = 10;
    InstallmentDetails installment1 = new InstallmentDetails(7, DAYS);
    InstallmentDetails installment2 = new InstallmentDetails(14, DAYS);
    InstallmentDetails installment3 = new InstallmentDetails(1, MONTHS);
    LoanDetails loanDetails1 = buildLoanDetails(remainingInstallments, installment1);
    LoanDetails loanDetails2 = buildLoanDetails(remainingInstallments, installment2);
    LoanDetails loanDetails3 = buildLoanDetails(remainingInstallments, installment3);
    Result resultWithHighestInstallment = new Result(loanDetails3, 1);
    Result resultWithLowestInstallment = new Result(loanDetails1, 1);
    Result resultWithAverageInstallment = new Result(loanDetails2, 1);
    SortParams sortParams = new SortParams(INSTALLMENT, ASC);
    List<Result> resultList = List.of(resultWithHighestInstallment, resultWithLowestInstallment,
        resultWithAverageInstallment);
    List<Result> expected = List.of(resultWithLowestInstallment, resultWithAverageInstallment,
        resultWithHighestInstallment);

    List<Result> actual = service.sort(resultList, sortParams);

    assertEquals(expected, actual);
  }

  @Test
  void defaultSortByTermDesc() {
    InstallmentDetails installment = new InstallmentDetails(7, DAYS);
    LoanDetails loanDetails1 = buildLoanDetails(10, installment);
    LoanDetails loanDetails2 = buildLoanDetails(20, installment);
    LoanDetails loanDetails3 = buildLoanDetails(30, installment);
    Result resultWithHighestTerm = new Result(loanDetails3, 1);
    Result resultWithLowestTerm = new Result(loanDetails1, 1);
    Result resultWithAverageTerm = new Result(loanDetails2, 1);
    SortParams sortParams = new SortParams(TERM, DESC);
    List<Result> resultList = List.of(resultWithHighestTerm, resultWithLowestTerm, resultWithAverageTerm);
    List<Result> expected = List.of(resultWithHighestTerm, resultWithAverageTerm, resultWithLowestTerm);

    List<Result> actual = service.sort(resultList, sortParams);

    assertEquals(expected, actual);
  }

  @Test
  void defaultSortByTermAsc() {
    InstallmentDetails installment = new InstallmentDetails(7, DAYS);
    LoanDetails loanDetails1 = buildLoanDetails(10, installment);
    LoanDetails loanDetails2 = buildLoanDetails(20, installment);
    LoanDetails loanDetails3 = buildLoanDetails(30, installment);
    Result resultWithHighestTerm = new Result(loanDetails3, 1);
    Result resultWithLowestTerm = new Result(loanDetails1, 1);
    Result resultWithAverageTerm = new Result(loanDetails2, 1);
    SortParams sortParams = new SortParams(TERM, ASC);
    List<Result> resultList = List.of(resultWithHighestTerm, resultWithLowestTerm, resultWithAverageTerm);
    List<Result> expected = List.of(resultWithLowestTerm, resultWithAverageTerm, resultWithHighestTerm);

    List<Result> actual = service.sort(resultList, sortParams);

    assertEquals(expected, actual);
  }

  private LoanDetails buildLoanDetails(int remainingTerms, InstallmentDetails installment) {
    return new LoanDetailsBuilder().builder(buildRawDetails()).withPrice(new Price(20, EUR))
        .withInterestRate((double) 20).withTerm(new Term(remainingTerms, 2)).withInstallment(installment).build();
  }

  private RawLoanDetails buildRawDetails() {
    RawLoanDetails rawDetails = new RawLoanDetails();
    rawDetails.setId("id-" + randomUUID());
    rawDetails.setLoanType("type" + randomUUID());
    rawDetails.setLoanOriginator("originator-" + randomUUID());
    rawDetails.setScoreClass("score-" + randomUUID());
    rawDetails.setGuaranteedPrincipal("guarantee-" + randomUUID());
    rawDetails.setStatus("status-" + randomUUID());
    rawDetails.setAvailableForInvestment("available-investment-" + randomUUID());
    rawDetails.setPremiumDiscount("premium-discount" + randomUUID());

    return rawDetails;
  }
}