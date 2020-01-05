package com.howtodoinjava.prototypedemo.model;

import com.howtodoinjava.prototypedemo.contract.PrototypeCapable;
import java.util.logging.Logger;

@SuppressWarnings("PMD.ShortClassName")
public class Show implements PrototypeCapable {
  private static final Logger LOGGER = Logger.getLogger(Show.class.getName());

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @SuppressWarnings("checkstyle:noclone")
  @Override
  public Show clone() throws CloneNotSupportedException {
    LOGGER.info("Cloning Show object..");
    return (Show)super.clone();
  }

  @Override
  public String toString() {
    return "Show";
  }
}
