package com.howtodoinjava.prototypedemo.model;

import com.howtodoinjava.prototypedemo.contract.PrototypeCapable;

@SuppressWarnings("checkstyle:noclone")
public class Album implements PrototypeCapable {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Album clone() throws CloneNotSupportedException {
    System.out.println("Cloning Album object..");
    return (Album)super.clone();
  }

  @Override
  public String toString() {
    return "Album";
  }
}
