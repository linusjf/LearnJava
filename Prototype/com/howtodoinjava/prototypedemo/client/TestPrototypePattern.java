package com.howtodoinjava.prototypedemo.client;

import com.howtodoinjava.prototypedemo.contract.PrototypeCapable;
import com.howtodoinjava.prototypedemo.factory.PrototypeFactory;
import com.howtodoinjava.prototypedemo.factory.PrototypeFactory.ModelType;
import java.util.logging.Logger;

public enum TestPrototypePattern {
  ;

  private static final Logger LOGGER =
      Logger.getLogger(TestPrototypePattern.class.getName());

  public static void main(String[] args) {
    try {
      PrototypeCapable moviePrototype =
          PrototypeFactory.getInstance(ModelType.MOVIE);
      LOGGER.info(() -> String.format("%s", moviePrototype));

      PrototypeCapable albumPrototype =
          PrototypeFactory.getInstance(ModelType.ALBUM);
      LOGGER.info(() -> String.format("%s", albumPrototype));

      PrototypeCapable showPrototype =
          PrototypeFactory.getInstance(ModelType.SHOW);
      LOGGER.info(() -> String.format("%s", showPrototype));
    } catch (CloneNotSupportedException e) {
      LOGGER.severe(e.getMessage());
    }
  }
}
