package com.javacodegeeks.patterns.chainofresponsibility;

/**
 * Describe class <code>ImageFileHandler</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class ImageFileHandler implements Handler {
  private Handler handler;
  private final String handlerName;

  /**
   * Creates a new <code>ImageFileHandler</code> instance.
   *
   * @param handlerName a <code>String</code> value
   */
  public ImageFileHandler(String handlerName) {
    this.handlerName = handlerName;
  }

  @Override
  public void setHandler(Handler handler) {
    this.handler = handler;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public void process(File file) {
    if (file.getFileType().equals("image")) {
      System.out.println("Process and saving image file... by " + handlerName);
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
