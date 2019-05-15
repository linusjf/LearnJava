package com.javacodegeeks.patterns.singletonpattern;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Describe class <code>Singleton</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class Singleton implements Serializable {

  private static final long serialVersionUID = -1093810940935189395L;

  private static volatile Singleton instance = null;

  private long nextValue = 0;

  private Singleton() {
    if (instance != null) {
      throw new IllegalStateException("Illegal access to constructor: Already instantiated.");
    }
  }

  /**
   * Describe <code>getInstance</code> method here.
   *
   * @return a <code>Singleton</code> value
   */
  public static Singleton getInstance() {
    if (instance == null) {
      synchronized (Singleton.class) {
        if (instance == null) {
          instance = new Singleton();
        }
      }
    }
    return instance;
  }

  private Object readResolve() throws ObjectStreamException {
    return instance;
  }

  private Object writeReplace() throws ObjectStreamException {
    return instance;
  }

  /**
   * Describe <code>clone</code> method here.
   *
   * @return an <code>Object</code> value
   * @exception CloneNotSupportedException if an error occurs
   */
  public Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Singleton, cannot be cloned");
  }

  @SuppressWarnings("unused")
  private static Class<?> getClass(String classname) throws ClassNotFoundException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    if (classLoader == null) classLoader = Singleton.class.getClassLoader();
    return (classLoader.loadClass(classname));
  }

  /**
   * Describe <code>getNextValue</code> method here.
   *
   * @return a <code>long</code> value
   */
  public synchronized long getNextValue() {
    return nextValue++;
  }
}
