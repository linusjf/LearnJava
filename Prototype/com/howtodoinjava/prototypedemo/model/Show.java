package com.howtodoinjava.prototypedemo.model;

import com.howtodoinjava.prototypedemo.contract.PrototypeCapable;

@SuppressWarnings("PMD.ShortClassName")
public class Show implements PrototypeCapable {
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
    System.out.println("Cloning Show object..");
    return (Show) super.clone();
  }

  @Override
  public String toString() {
    return "Show";
  }
}
