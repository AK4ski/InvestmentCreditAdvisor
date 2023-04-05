package org.demo.file_factory.xlsx;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.demo.FileInput;
import org.demo.file_factory.FileOutputData;
import org.demo.file_factory.FileOutputGenerator;
import org.demo.model.RawLoanDetails;

public class XlsxFileOutputGenerator implements FileOutputGenerator {

  @Override
  public FileOutputData getData(final FileInput fileInput) {
    List<RawLoanDetails> outputData = new ArrayList<>();

    try {
      FileInputStream file = new FileInputStream(fileInput.getFile());
      Workbook workbook = new XSSFWorkbook(file);

      Sheet sheet = workbook.getSheetAt(0);

      Map<Integer, List<String>> data = new HashMap<>();

      int index = 0;
      for (Row row : sheet) {
        if (index != 0) {
          data.put(index, new ArrayList<>());
          for (Cell cell : row) {
            switch (cell.getCellType()) {
              case STRING:
                extractStringCell(index, cell, data);
                break;
              case NUMERIC:
                extractNumberCell(index, cell, data);
                break;
              default:
                data.get(index).add(" ");
            }
          }
        }
        index++;
      }

      for (List<String> object : data.values()) {
        RawLoanDetails details = new RawLoanDetails();
        for (int i = 0; i < object.size(); i++) {
          String value = object.get(i);
          setValue(value, i, details);
        }
        outputData.add(details);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return new FileOutputData(outputData);
  }

  private static void setValue(String value, int index, RawLoanDetails details) {
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

  private static void extractStringCell(
      int index,
      Cell cell,
      Map<Integer, List<String>> data) {
    cell.getStringCellValue();
    List<String> cellValues = data.get(index);
    cellValues.add(cell.getStringCellValue());
  }


  private static void extractNumberCell(
      int index,
      Cell cell,
      Map<Integer, List<String>> data) {
    cell.getNumericCellValue();
    List<String> cellValues = data.get(index);
    cellValues.add(String.valueOf(Math.round(cell.getNumericCellValue())));
  }
}
