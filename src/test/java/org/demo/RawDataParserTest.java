package org.demo;

import static java.lang.Double.parseDouble;
import static org.demo.model.Currency.BGN;
import static org.demo.model.Currency.EUR;
import static org.demo.model.InstallmentType.DAYS;
import static org.demo.model.InstallmentType.MONTHS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.demo.model.InstallmentDetails;
import org.demo.model.Price;
import org.demo.model.Term;
import org.junit.jupiter.api.Test;

class RawDataParserTest {

  private final RawDataParser parser = new RawDataParser();

  @Test
  void parseMaturityInDaysSuccess() {
    //ARRANGE
    String maturity = "7 days";
    InstallmentDetails expected = new InstallmentDetails(7, DAYS);

    //ACT
    InstallmentDetails actual = parser.parseMaturity(maturity).orElseThrow();

    //ASSERT
    assertEquals(expected, actual);
  }

  @Test
  void parseMaturityInMonthsSuccess() {
    //ARRANGE
    String maturity = "14 months";
    InstallmentDetails expected = new InstallmentDetails(14, MONTHS);

    //ACT
    InstallmentDetails actual = parser.parseMaturity(maturity).orElseThrow();

    //ASSERT
    assertEquals(expected, actual);
  }

  @Test
  void parseInterestRateSuccess() {
    //ARRANGE
    String interestRate = "13.72%";
    double expected = 13.72;

    //ACT
    Double actual = parser.parseInterestRate(interestRate).orElseThrow();

    //ASSERT
    assertEquals(expected, actual);
  }

  @Test
  void parsePriceInBgnSuccess() {
    //ARRANGE
    String price = "287.55 BGN";
    Price expected = new Price(parseDouble("287.55"), BGN);

    //ACT
    Price actual = parser.parsePrice(price).orElseThrow();

    //ASSERT
    assertEquals(expected, actual);
  }

  @Test
  void parsePriceInEurSuccess() {
    //ARRANGE
    String price = "13.20 EUR";
    Price expected = new Price(parseDouble("13.20"), EUR);

    //ACT
    Price actual = parser.parsePrice(price).orElseThrow();

    //ASSERT
    assertEquals(expected, actual);
  }

  @Test
  void parseTermSuccess() {
    //ARRANGE
    String term = "10 (2)";
    Term expected = new Term(10, 2);

    //ACT
    Term actual = parser.parseTerm(term).orElseThrow();

    //ASSERT
    assertEquals(expected, actual);
  }

  @Test
  void parsePriceFailedInvalidCurrency() {
    //ARRANGE
    String price = "13.20 USD";

    //ACT && ASSERT
    assertThrows(NotImplementedException.class,
        () -> parser.parsePrice(price));
  }
}