package com.javacodegeeks.patterns.chainofresponsibility;

public interface Handler {

  void setHandler(Handler handler);

  void process(File file);

  String getHandlerName();
}
