package com.howtodoinjava.prototypedemo.contract;

public interface PrototypeCapable extends Cloneable {
  PrototypeCapable clone() throws CloneNotSupportedException;
}
