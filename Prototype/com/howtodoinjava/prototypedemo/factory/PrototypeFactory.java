package com.howtodoinjava.prototypedemo.factory;

import com.howtodoinjava.prototypedemo.contract.PrototypeCapable;
import com.howtodoinjava.prototypedemo.model.Album;
import com.howtodoinjava.prototypedemo.model.Movie;
import com.howtodoinjava.prototypedemo.model.Show;

public final class PrototypeFactory {
  private static java.util.Map<String, PrototypeCapable> prototypes = new java.util.HashMap<>();

  private PrototypeFactory() {
    throw new IllegalStateException("Private constructor");
  }

  static {
    prototypes.put(ModelType.MOVIE, new Movie());
    prototypes.put(ModelType.ALBUM, new Album());
    prototypes.put(ModelType.SHOW, new Show());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static PrototypeCapable getInstance(final String s) throws CloneNotSupportedException {
    return prototypes.get(s).clone();
  }

  public static class ModelType {
    public static final String MOVIE = "movie";
    public static final String ALBUM = "album";
    public static final String SHOW = "show";
  }
}
