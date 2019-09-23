package com.howtodoinjava.prototypedemo.contract;

@SuppressWarnings("checkstyle:noclone")
public interface PrototypeCapable extends Cloneable {
  PrototypeCapable clone() throws CloneNotSupportedException;
}
