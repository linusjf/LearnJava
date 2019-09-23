package com.javacodegeeks.patterns.prototypepattern;

@SuppressWarnings("checkstyle:noclone")
public interface Prototype extends Cloneable {
  Prototype clone() throws CloneNotSupportedException;
}
