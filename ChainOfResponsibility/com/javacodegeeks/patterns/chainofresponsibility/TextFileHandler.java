package com.javacodegeeks.patterns.chainofresponsibility;

/**
 * Describe class <code>TextFileHandler</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class TextFileHandler implements Handler {
  private Handler handler;
  private final String handlerName;

  /**
   * Creates a new <code>TextFileHandler</code> instance.
   *
   * @param handlerName a <code>String</code> value
   */
  public TextFileHandler(String handlerName) {
    this.handlerName = handlerName;
  }

  @Override
  public void setHandler(Handler handler) {
    this.handler = handler;
  }

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  @Override
  public void process(File file) {
    if ("text".equals(file.getFileType())) {
      System.out.println("Process and saving text file... by " + handlerName);
      return;
    }
    if (handler != null) {
      System.out.println(handlerName + " fowards request to " + handler.getHandlerName());
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
