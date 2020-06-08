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
  private static final long serialVersionUID = 1L;
  @SuppressWarnings("checkstyle:illegaltoken")
  private static volatile transient Singleton instance; // NOPMD
  private transient long nextValue;
  private Object syncObj = new Object();

  private Singleton() {
    if (instance != null) {
      throw new IllegalStateException("Illegal access to constructor: Already instantiated for class " + getClass());
    }
  }

  /**
   * Describe <code>getInstance</code> method here.
   *
   * @return a <code>Singleton</code> value
   */
  public static Singleton getInstance() {
    if (instance == null) {
      // NOPMD
      // the pmd warning emitted ignores the volatile modifier.
      // works for Java 1.5 onwards
      synchronized (Singleton.class) {
        if (instance == null) instance = new Singleton();
      }
    }
    return instance;
  }

  private Object readResolve() {
    return instance;
  }

  private Object writeReplace() {
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
    throw new CloneNotSupportedException("Singleton cannot be cloned : " + getClass());
  }

  @SuppressWarnings({"unused", "PMD.UseProperClassLoader", "PMD.LawOfDemeter"})
  private static Class<?> getClass(String classname) throws ClassNotFoundException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    if (classLoader == null) classLoader = Singleton.class.getClassLoader();
    return classLoader.loadClass(classname);
  }

  /**
   * Describe <code>getNextValue</code> method here.
   *
   * @return a <code>long</code> value
   */
  public long getNextValue() {
    synchronized (syncObj) {
      return nextValue++;
    }
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "Singleton(nextValue=" + this.getNextValue() + ", syncObj=" + this.syncObj + ")";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Singleton)) return false;
    Singleton other = (Singleton) o;
    Object this$syncObj = this.syncObj;
    Object other$syncObj = other.syncObj;
    if (this$syncObj == null ? other$syncObj != null : !this$syncObj.equals(other$syncObj)) return false;
    return true;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $syncObj = this.syncObj;
    result = result * PRIME + ($syncObj == null ? 43 : $syncObj.hashCode());
    return result;
  }
}
