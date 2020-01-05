package com.howtodoinjava.prototypedemo.model;

import com.howtodoinjava.prototypedemo.contract.PrototypeCapable;
import java.util.logging.Logger;

@SuppressWarnings("checkstyle:noclone")
public class Movie implements PrototypeCapable {
  private static final Logger LOGGER = Logger.getLogger(Movie.class.getName());
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Movie clone() throws CloneNotSupportedException {
    LOGGER.info("Cloning Movie object..");
    return (Movie)super.clone();
  }

  @Override
  public String toString() {
    return "Movie";
  }
}
