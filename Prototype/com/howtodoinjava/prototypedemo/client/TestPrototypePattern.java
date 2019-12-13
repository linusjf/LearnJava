package com.howtodoinjava.prototypedemo.client;

import com.howtodoinjava.prototypedemo.contract.PrototypeCapable;
import com.howtodoinjava.prototypedemo.factory.PrototypeFactory;
import com.howtodoinjava.prototypedemo.factory.PrototypeFactory.ModelType;

public enum TestPrototypePattern {
  ;

  public static void main(String[] args) {
    try {
      PrototypeCapable moviePrototype =
          PrototypeFactory.getInstance(ModelType.MOVIE);
      System.out.println(moviePrototype);

      PrototypeCapable albumPrototype =
          PrototypeFactory.getInstance(ModelType.ALBUM);
      System.out.println(albumPrototype);

      PrototypeCapable showPrototype =
          PrototypeFactory.getInstance(ModelType.SHOW);
      System.out.println(showPrototype);
    } catch (CloneNotSupportedException e) {
      System.err.println(e.getMessage());
    }
  }
}
