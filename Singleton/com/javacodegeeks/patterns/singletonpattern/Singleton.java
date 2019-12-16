package com.javacodegeeks.patterns.singletonpattern;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Describe class <code>Singleton</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class Singleton implements Serializable, Cloneable {
  private static final long serialVersionUID = -1093810940935189395L;

  @SuppressWarnings("checkstyle:illegaltoken")
  private static transient volatile Singleton instance; // NOPMD

  private transient long nextValue;

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
    if (instance == null) { // NOPMD
      // the pmd warning emitted ignores the volatile modufier.
      // works for Java 1.5 onwards
      synchronized (Singleton.class) {
        if (instance == null)
          instance = new Singleton();
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
   * This method <code>clone</code> should be implemented only if the Singleton class immplements
   * the Cloneable interface or extends a class that does. if not,the compiler will do the exact
   * same thing this method does without having to implement it.
   *
   * @return an <code>Object</code> value
   * @exception CloneNotSupportedException if an error occurs
   */
  @SuppressWarnings("checkstyle:noclone")
  @Override
  public Singleton clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException("Singleton, cannot be cloned");
  }

  @SuppressWarnings({"unused", "PMD.UseProperClassLoader", "PMD.LawOfDemeter"})
  private static Class<?> getClass(String classname) throws ClassNotFoundException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    if (classLoader == null)
      classLoader = Singleton.class.getClassLoader();
    return classLoader.loadClass(classname);
  }

  /**
   * Describe <code>getNextValue</code> method here.
   *
   * @return a <code>long</code> value
   */
  public long getNextValue() {
    synchronized (this) {
      return nextValue++;
    }
  }
}
