package org.demo.file_factory.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.demo.FileInput;
import org.demo.file_factory.FileOutputData;
import org.demo.file_factory.FileOutputGenerator;
import org.demo.model.RawLoanDetails;

public class CsvFileOutputGenerator implements FileOutputGenerator {

  @Override
  public FileOutputData getData(FileInput fileInput) {
    final List<RawLoanDetails> outputData = new ArrayList<>();

    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileInput.getFile()));

      String line;
      while ((line = reader.readLine()) != null) {
        String[] fields = line.split(",");
        RawLoanDetails details = new RawLoanDetails();
        for (int i = 0; i < fields.length; i++) {
          String value = fields[i];
          setValue(value.trim(), i, details);
        }
        outputData.add(details);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return new FileOutputData(outputData);
  }

  private void setValue(String value, int index, RawLoanDetails details) {
    switch (index) {
      case 0:
        details.setId(value);
        break;
      case 1:
        details.setLoanType(value);
        break;
      case 2:
        details.setLoanOriginator(value);
        break;
      case 3:
        details.setScoreClass(value);
        break;
      case 4:
        details.setGuaranteedPrincipal(value);
        break;
      case 5:
        details.setTerm(value);
        break;
      case 6:
        details.setInstallmentType(value);
        break;
      case 7:
        details.setStatus(value);
        break;
      case 8:
        details.setInterestRate(value);
        break;
      case 9:
        details.setAvailableForInvestment(value);
        break;
      case 10:
        details.setPremiumDiscount(value);
        break;
      case 11:
        details.setPrice(value);
        break;
      default:
        throw new NotImplementedException("Row Data Mapping Failed");
    }
  }
}

