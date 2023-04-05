package org.demo.file_factory;

import org.demo.FileInput;

public interface FileOutputGenerator {

  FileOutputData getData(final FileInput fileInput);
}
