package com.javacodegeeks.patterns.chainofresponsibility;
/**
 * Describe class <code>File</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
@SuppressWarnings("PMD.ShortClassName")
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

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "File(fileName=" + this.getFileName() + ", fileType=" + this.getFileType() + ", filePath=" + this.getFilePath() + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof File)) return false;
    File other = (File) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$fileName = this.getFileName();
    Object other$fileName = other.getFileName();
    if (this$fileName == null ? other$fileName != null : !this$fileName.equals(other$fileName)) return false;
    Object this$fileType = this.getFileType();
    Object other$fileType = other.getFileType();
    if (this$fileType == null ? other$fileType != null : !this$fileType.equals(other$fileType)) return false;
    Object this$filePath = this.getFilePath();
    Object other$filePath = other.getFilePath();
    if (this$filePath == null ? other$filePath != null : !this$filePath.equals(other$filePath)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof File;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $fileName = this.getFileName();
    result = result * PRIME + ($fileName == null ? 43 : $fileName.hashCode());
    Object $fileType = this.getFileType();
    result = result * PRIME + ($fileType == null ? 43 : $fileType.hashCode());
    Object $filePath = this.getFilePath();
    result = result * PRIME + ($filePath == null ? 43 : $filePath.hashCode());
    return result;
  }
}
