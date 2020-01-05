package com.howtodoinjava.prototypedemo.model;

import com.howtodoinjava.prototypedemo.contract.PrototypeCapable;
import java.util.logging.Logger;

@SuppressWarnings("checkstyle:noclone")
public class Album implements PrototypeCapable {
  private static final Logger LOGGER = Logger.getLogger(Album.class.getName());

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Album clone() throws CloneNotSupportedException {
    LOGGER.info("Cloning Album object..");
    return (Album)super.clone();
  }

  @Override
  public String toString() {
    return "Album";
  }
}
