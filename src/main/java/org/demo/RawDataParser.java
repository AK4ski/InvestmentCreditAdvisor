package org.demo;

import static java.lang.Double.*;
import static java.util.Optional.*;
import static org.demo.model.Currency.*;
import static org.demo.model.InstallmentType.*;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.demo.model.Currency;
import org.demo.model.InstallmentDetails;
import org.demo.model.InstallmentType;
import org.demo.model.Price;
import org.demo.model.Term;

public class RawDataParser {

  private static final String STARTING_INTEGER_PATTERN = "^\\d+";
  private static final String STARTING_DECIMAL_PATTERN = "^\\d+(.\\d{1,2})";
  private static final String INTEGER_BETWEEN_PARENTHESES_PATTERN = "\\((\\d+)\\)";

  public Optional<InstallmentDetails> parseMaturity(final String installmentTypeTxt) {
    return match(installmentTypeTxt, STARTING_INTEGER_PATTERN)
        .map(Integer::valueOf)
        .map(installment -> new InstallmentDetails(installment, getInstallmentType(installmentTypeTxt)));
  }

  public Optional<Double> parseInterestRate(final String interestRateTxt) {
    return match(interestRateTxt, STARTING_DECIMAL_PATTERN).map(Double::valueOf);
  }

  public Optional<Price> parsePrice(final String priceTxt) {
    return match(priceTxt, STARTING_DECIMAL_PATTERN).map(
        decimalMatch -> new Price(parseDouble(decimalMatch), getCurrency(priceTxt)));
  }

  public Optional<Term> parseTerm(final String term) {
    return match(term, STARTING_INTEGER_PATTERN)
        .map(Integer::valueOf)
        .map(remaining -> new Term(remaining, getRepaid(term)));
  }

  private Integer getRepaid(final String term) {
    return match(term, INTEGER_BETWEEN_PARENTHESES_PATTERN)
        .map(str -> str.substring(1, str.length() - 1))
        .map(Integer::valueOf)
        .orElseThrow();
  }

  private Optional<String> match(
      final String textMatcher,
      final String regexPattern) {
    final Pattern pattern = Pattern.compile(regexPattern);
    final Matcher matcher = pattern.matcher(textMatcher);

    if (matcher.find()) {
      return Optional.of(matcher.group());
    } else {
      return empty();
    }
  }

  private Currency getCurrency(final String price) {
    final String loweredCasePrice = price.toLowerCase();
    if (loweredCasePrice.contains("eur")) {
      return EUR;
    } else if (loweredCasePrice.contains("bgn")) {
      return BGN;
    }
    throw new NotImplementedException("No such currency");
  }

  private InstallmentType getInstallmentType(final String installmentType) {
    final String loweredCaseInstallmentType = installmentType.toLowerCase();
    if (loweredCaseInstallmentType.contains("day")) {
      return DAYS;
    } else if (loweredCaseInstallmentType.contains("week")) {
      return WEEKS;
    } else if (loweredCaseInstallmentType.contains("month")) {
      return MONTHS;
    } else {
      throw new NotImplementedException("No such installment type.");
    }
  }
}
