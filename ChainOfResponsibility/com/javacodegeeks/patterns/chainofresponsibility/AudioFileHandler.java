package com.javacodegeeks.patterns.chainofresponsibility;

/**
 * Describe class <code>AudioFileHandler</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class AudioFileHandler implements Handler {
  private Handler handler;
  private String handlerName;

  /**
   * Creates a new <code>AudioFileHandler</code> instance.
   *
   * @param handlerName a <code>String</code> value
   */
  public AudioFileHandler(String handlerName) {
    this.handlerName = handlerName;
  }

  @Override
  public void setHandler(Handler handler) {
    this.handler = handler;
  }

  @Override
  public void process(File file) {
    if (file.getFileType().equals("audio")) {
      System.out.println("Process and saving audio file... by " + handlerName);
      return;
    }
    if (handler != null) {
      System.out.println(
        handlerName + " forwards request to " + handler.getHandlerName()
      );
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
