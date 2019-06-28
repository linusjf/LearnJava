package com.javacodegeeks.patterns.prototypepattern;

public interface Prototype extends Cloneable {
  Prototype clone() throws CloneNotSupportedException;
}
