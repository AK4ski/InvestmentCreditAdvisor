package org.demo.file_factory;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;
import static org.demo.model.Validation.toInvalid;
import static org.demo.model.Validation.toValid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import org.demo.CompoundInterestCalculationService;
import org.demo.FileInput;
import org.demo.RawDataParser;
import org.demo.model.InstallmentDetails;
import org.demo.model.InstallmentType;
import org.demo.model.LoanDetails;
import org.demo.model.LoanDetails.LoanDetailsBuilder;
import org.demo.model.RawLoanDetails;
import org.demo.model.Result;
import org.demo.model.Term;
import org.demo.model.Validation;

public class HandlerImpl implements Handler {

  private final FileOutputGenerator fileOutputGenerator;
  private final RawDataParser dataParser;
  private final CompoundInterestCalculationService calculationService;
  private final Map<InstallmentType, Integer> numberOfSlotsPerYear;

  public HandlerImpl(
      final FileOutputGenerator fileOutputGenerator,
      final RawDataParser dataParser,
      final CompoundInterestCalculationService calculationService,
      final Map<InstallmentType, Integer> installmentTypeFrequencyPerYear) {
    this.fileOutputGenerator = fileOutputGenerator;
    this.dataParser = dataParser;
    this.calculationService = calculationService;
    this.numberOfSlotsPerYear = installmentTypeFrequencyPerYear;
  }

  @Override
  public List<Result> handle(final FileInput fileInput) {
    final FileOutputData data = fileOutputGenerator.getData(fileInput);

    return data
        .getDetails()
        .stream()
        .map(this::validate)
        .filter(this::filter)
        .map(this::parse)
        .filter(Optional::isPresent)
        .map(Optional::orElseThrow)
        .map(this::calculate)
        .collect(toList());
  }

  private Validation validate(final RawLoanDetails detail) {
    boolean isCurrentStatus = detail.getStatus().equalsIgnoreCase("CURRENT");
    if (!isCurrentStatus) {
      return toInvalid();
    }

    boolean allRequiredFieldsPresent = not(
        detail.getTerm().isBlank(),
        detail.getPrice().isBlank(),
        detail.getInterestRate().isBlank(),
        detail.getInstallmentType().isBlank());
    if (!allRequiredFieldsPresent) {
      return toInvalid();
    }

    return dataParser
        .parsePrice(detail.getPrice())
        .filter(price -> price.getValue() >= 10.00)
        .map(price -> toValid(detail, price))
        .orElseGet(Validation::toInvalid);
  }

  private boolean filter(final Validation validation) {
    return validation.isValid();
  }

  private Optional<LoanDetails> parse(final Validation validation) {
    final RawLoanDetails details = validation.getDetails();
    final LoanDetailsBuilder builder = new LoanDetailsBuilder()
        .builder(details)
        .withPrice(validation.getPrice());
    final AtomicBoolean allExpectedValuesParsed = new AtomicBoolean(true);

    dataParser
        .parseInterestRate(details.getInterestRate())
        .ifPresentOrElse(
            builder::withInterestRate,
            () -> allExpectedValuesParsed.set(false));

    if (!allExpectedValuesParsed.get()) {
      return empty();
    }

    dataParser
        .parseMaturity(details.getInstallmentType())
        .ifPresentOrElse(
            builder::withInstallment,
            () -> allExpectedValuesParsed.set(false));

    if (!allExpectedValuesParsed.get()) {
      return empty();
    }

    dataParser
        .parseTerm(details.getTerm())
        .ifPresentOrElse(
            builder::withTerm,
            () -> allExpectedValuesParsed.set(false));

    return allExpectedValuesParsed.get() ? of(builder.build()) : empty();
  }

  private Result calculate(LoanDetails loanDetails) {
    double price = loanDetails.getPrice().getValue();
    double interestRate = loanDetails.getInterestRate() / 100;

    InstallmentDetails installmentDetails = loanDetails.getInstallmentDetails();
    int slotsInYear = numberOfSlotsPerYear.get(installmentDetails.getInstallmentType());
    int numberOfTimesInterestReceivements = slotsInYear / installmentDetails.getInstallment();

    Term term = loanDetails.getTerm();
    int remainingInstallments = term.getRemainingInstallments();
    int totalYears = remainingInstallments / numberOfTimesInterestReceivements;

    double futureValue = calculationService
        .calculateFutureValue(
            price,
            interestRate,
            numberOfTimesInterestReceivements,
            totalYears);

    return new Result(loanDetails, price - futureValue);
  }

  private boolean not(boolean... checks) {
    for (boolean check : checks) {
      if (check) {
        return false;
      }
    }

    return true;
  }

}
