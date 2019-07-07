package com.javacodegeeks.patterns.chainofresponsibility;

/**
 * Describe class <code>ExcelFileHandler</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ExcelFileHandler implements Handler {
  private Handler handler;
  private String handlerName;

  /**
   * Creates a new <code>ExcelFileHandler</code> instance.
   *
   * @param handlerName a <code>String</code> value
   */
  public ExcelFileHandler(String handlerName) {
    this.handlerName = handlerName;
  }

  @Override
  public void setHandler(Handler handler) {
    this.handler = handler;
  }

  @Override
  public void process(File file) {
    if (file.getFileType().equals("excel")) {
      System.out.println("Process and saving excel file... by " + handlerName);
      return;
    }
    if (handler != null) {
      System.out.println(handlerName + " forwards request to "
                         + handler.getHandlerName());
      handler.process(file);
      return;
    }
    System.out.println("File not supported");
  }

  @Override
  public String getHandlerName() {
    return handlerName;
  }
}
