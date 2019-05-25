package com.javacodegeeks.patterns.chainofresponsibility;

/**
 * Describe class <code>File</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class File {

  private final String fileName;
  private final String fileType;
  private final String filePath;

  /**
   * Creates a new <code>File</code> instance.
   *
   * @param fileName a <code>String</code> value
   * @param fileType a <code>String</code> value
   * @param filePath a <code>String</code> value
   */
  public File(String fileName, String fileType, String filePath) {
    this.fileName = fileName;
    this.fileType = fileType;
    this.filePath = filePath;
  }

  /**
   * Describe <code>getFileName</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Describe <code>getFileType</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getFileType() {
    return fileType;
  }

  /**
   * Describe <code>getFilePath</code> method here.
   *
   * @return a <code>String</code> value
   */
  public String getFilePath() {
    return filePath;
  }
}
