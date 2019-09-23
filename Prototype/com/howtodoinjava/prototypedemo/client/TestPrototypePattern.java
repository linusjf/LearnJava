package com.howtodoinjava.prototypedemo.client;

import com.howtodoinjava.prototypedemo.factory.PrototypeFactory;
import com.howtodoinjava.prototypedemo.factory.PrototypeFactory.ModelType;

public enum TestPrototypePattern {
  ;

  public static void main(String[] args) {
    try {
      String moviePrototype =
          PrototypeFactory.getInstance(ModelType.MOVIE).toString();
      System.out.println(moviePrototype);

      String albumPrototype =
          PrototypeFactory.getInstance(ModelType.ALBUM).toString();
      System.out.println(albumPrototype);

      String showPrototype =
          PrototypeFactory.getInstance(ModelType.SHOW).toString();
      System.out.println(showPrototype);

    } catch (CloneNotSupportedException e) {
      System.err.println(e.getMessage());
    }
  }
}
