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
          return results.stream().sorted(loanYieldComparator().thenComparing(loanInstallmentDetailsComparator()))
              .collect(Collectors.toList());
        }
        if (sortOrder == DESC) {
          return results.stream()
              .sorted(loanYieldComparator().thenComparing(loanInstallmentDetailsComparator()).reversed())
              .collect(Collectors.toList());
        }
      }
    }

    return results.stream()
        .sorted(loanYieldComparator())
        .collect(Collectors.toList());
  }

  public Comparator<Result> loanYieldComparator() {
    return comparing(Result::getYield);
  }

  public Comparator<Result> loanTermComparator() {
    return comparing(this::getRemainingInstallments);
  }

  private int getRemainingInstallments(Result i) {
    return i.getLoanDetails().getTerm().getRemainingInstallments();
  }

  public Comparator<Result> loanInstallmentDetailsComparator() {
    return comparing(i -> i.getLoanDetails().getInstallmentDetails(), installmentDetailsComparator());
  }

  public Comparator<InstallmentDetails> installmentDetailsComparator() {
    return comparing(InstallmentDetails::getInstallmentType).thenComparing(InstallmentDetails::getInstallment);
  }
}
