package com.javacodegeeks.niotutorial.file;

public final class NIORuntimeException extends RuntimeException {

  public static final long serialVersionUID = 1;

  public NIORuntimeException(String message, Exception e) {
    super(message, e);
  }

  public NIORuntimeException(String message) {
    super(message);
  }
}
