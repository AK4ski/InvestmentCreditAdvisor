package org.demo;

import static org.demo.model.InstallmentType.DAYS;
import static org.demo.model.InstallmentType.MONTHS;
import static org.demo.model.InstallmentType.WEEKS;

import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.demo.file_factory.Handler;
import org.demo.file_factory.HandlerImpl;
import org.demo.file_factory.xlsx.XlsxFileOutputGenerator;
import org.demo.model.InstallmentType;
import org.demo.model.Result;
import org.demo.model.ShortResult;

public class Program {

  public static void main(String[] args) {
    final Context context = new Context();
    final ExtensionType fileExtension = getFileExtension(context.getFilePath());

    final XlsxFileOutputGenerator xlsxFileOutputGenerator = new XlsxFileOutputGenerator();
    final RawDataParser rawDataParser = new RawDataParser();
    final CompoundInterestCalculationService calculationService = new CompoundInterestCalculationService();
    final EnumMap<InstallmentType, Integer> map = new EnumMap<>(InstallmentType.class);
    fillDefaultMapValues(map, context);
    final SortService sortService = new SortService();

    final Handler handler = new HandlerImpl(xlsxFileOutputGenerator, rawDataParser, calculationService, map);

    final FileInput fileInput = new FileInput(context.getFilePath(), fileExtension);
    List<Result> results = sortService.sort(handler.handle(fileInput), context.getSortParams());

    if (context.isDisplayShort()) {
      List<ShortResult> collect = results.stream().map(ShortResult::new).collect(Collectors.toList());
      System.out.println(collect);
    } else {
      System.out.println(results);
    }
  }

  private static void fillDefaultMapValues(EnumMap<InstallmentType, Integer> map, Context context) {
    map.put(DAYS, context.getInstallmentTypeDays());
    map.put(WEEKS, context.getInstallmentTypeWeeks());
    map.put(MONTHS, context.getInstallmentTypeMonths());
  }

  private static ExtensionType getFileExtension(String fileLocation) {
    return ExtensionType.valueOf(fileLocation.split("\\.")[1].toUpperCase(Locale.ROOT));
  }

}