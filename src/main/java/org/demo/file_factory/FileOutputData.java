package org.demo.file_factory;

import java.util.List;
import org.demo.model.RawLoanDetails;

public class FileOutputData {

  private final List<RawLoanDetails> details;

  public FileOutputData(final List<RawLoanDetails> details) {
    this.details = details;
  }

  public List<RawLoanDetails> getDetails() {
    return details;
  }
}
