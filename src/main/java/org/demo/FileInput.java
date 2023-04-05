package org.demo;

public class FileInput {
  private final String file;
  private final ExtensionType type;

  public FileInput(String file, ExtensionType type) {
    this.file = file;
    this.type = type;
  }

  public String getFile() {
    return file;
  }

  public ExtensionType getType() {
    return type;
  }
}
