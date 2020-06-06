package io;

public final class RanFileConstants {
  static final int REC_SIZE = 48;
  static final int SURNAME_SIZE = 15;
  static final int NUM_INITS = 3;

  private RanFileConstants() {
    throw new IllegalStateException("Private constructor invoked for class: " + getClass());
  }
}
