package org.demo;

import static java.util.Comparator.comparing;
import static org.demo.model.SortParams.SortBy.*;
import static org.demo.model.SortParams.SortDirection.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.demo.model.InstallmentDetails;
import org.demo.model.Result;
import org.demo.model.SortParams;
import org.demo.model.SortParams.SortBy;
import org.demo.model.SortParams.SortDirection;

public class SortService {

  public SortService() {
  }

  public List<Result> sort(List<Result> results, SortParams params) {
    SortBy sortBy = params.getSortBy();
    SortDirection sortOrder = params.getSortOrder();
    if (sortBy != null && sortOrder != null) {
      if (sortBy == MATURITY) {
        if (sortOrder == ASC) {
          return results
              .stream()
              .sorted(loanMaturityComparator())
              .collect(Collectors.toList());
        }
        if (sortOrder == DESC) {
          return results
              .stream()
              .sorted(loanMaturityComparator().reversed())
              .collect(Collectors.toList());
        }
      }
      if (sortBy == TERM) {

        if (sortOrder == ASC) {
          return results
              .stream()
              .sorted(loanYieldComparator().thenComparing(loanTermComparator()))
              .collect(Collectors.toList());
        }
        if (sortOrder == DESC) {
          return results
              .stream()
              .sorted(loanYieldComparator().thenComparing(loanTermComparator()).reversed())
              .collect(Collectors.toList());
        }
      }
      if (sortBy == INSTALLMENT) {

        if (sortOrder == ASC) {
          return results.stream().sorted(loanYieldComparator()
                  .thenComparing(loanInstallmentDetailsComparator()))
              .collect(Collectors.toList());
        }
        if (sortOrder == DESC) {
          return results.stream()
              .sorted(loanYieldComparator().thenComparing(loanInstallmentDetailsComparator()).reversed())
              .collect(Collectors.toList());
        }
      }
    }

    if (sortOrder != null && sortOrder == ASC) {
      return results.stream()
          .sorted(loanYieldComparator())
          .collect(Collectors.toList());
    }

    return results.stream()
        .sorted(loanYieldComparator().reversed())
        .collect(Collectors.toList());
  }

  public Comparator<Result> loanYieldComparator() {
    return comparing(i -> i.getYield().getBgn());
  }

  public Comparator<Result> loanMaturityComparator() {
    return comparing(this::calculateMaturity);
  }

  private int calculateMaturity(Result result) {
    int remainingInstallments = result.getLoanDetails().getTerm().getRemainingInstallments();
    int installments = result.getLoanDetails().getInstallmentDetails().getInstallment();
    int installmentTypeFactor = result.getLoanDetails()
        .getInstallmentDetails()
        .getInstallmentType()
        .getInstallmentTypeFactor();

    return remainingInstallments * installments * installmentTypeFactor;
  }

  public Comparator<Result> loanTermComparator() {
    return comparing(this::getRemainingInstallments);
  }

  private int getRemainingInstallments(Result i) {
    return i.getLoanDetails().getTerm().getRemainingInstallments();
  }

  private Comparator<Result> loanInstallmentDetailsComparator() {
    return comparing(i -> i.getLoanDetails().getInstallmentDetails(), installmentDetailsComparator());
  }

  private Comparator<InstallmentDetails> installmentDetailsComparator() {
    return comparing(InstallmentDetails::getInstallmentType).thenComparing(InstallmentDetails::getInstallment);
  }
}
