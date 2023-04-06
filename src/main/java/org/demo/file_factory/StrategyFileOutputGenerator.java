package org.demo.file_factory;

import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.demo.ExtensionType;
import org.demo.file_factory.csv.CsvFileOutputGenerator;
import org.demo.file_factory.xlsx.XlsxFileOutputGenerator;

public class StrategyFileOutputGenerator {

  public static FileOutputGenerator initializeFileOutputGenerator(final ExtensionType fileExtension) {
    switch (fileExtension) {
      case CSV:
        return new CsvFileOutputGenerator();
      case XLSX:
        return new XlsxFileOutputGenerator();
      default:
        throw new NotImplementedException("File Output Generator not implemented");
    }
  }
}
