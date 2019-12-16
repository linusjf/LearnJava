package com.javacodegeeks.patterns.chainofresponsibility;

/**
 * Describe class <code>DocFileHandler</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class DocFileHandler implements Handler {
  private Handler handler;
  private final String handlerName;

  /**
   * Creates a new <code>DocFileHandler</code> instance.
   *
   * @param handlerName a <code>String</code> value
   */
  public DocFileHandler(String handlerName) {
    this.handlerName = handlerName;
  }

  @Override
  public void setHandler(Handler handler) {
    this.handler = handler;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void process(File file) {
    if (file.getFileType().equals("doc")) {
      System.out.println("Process and saving doc file... by " + handlerName);
      return;
    }
    if (handler != null) {
      System.out.println(handlerName + " forwards request to " + handler.getHandlerName());
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
