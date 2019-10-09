package com.howtodoinjava.prototypedemo.model;

import com.howtodoinjava.prototypedemo.contract.PrototypeCapable;

@SuppressWarnings("checkstyle:noclone")
public class Movie implements PrototypeCapable {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Movie clone() throws CloneNotSupportedException {
    System.out.println("Cloning Movie object..");
    return (Movie) super.clone();
  }

  @Override
  public String toString() {
    return "Movie";
  }
}
