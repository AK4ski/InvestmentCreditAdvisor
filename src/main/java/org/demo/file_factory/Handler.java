package org.demo.file_factory;

import java.util.List;
import org.demo.FileInput;
import org.demo.model.Result;

public interface Handler {

  List<Result> handle(final FileInput fileInput);

}
