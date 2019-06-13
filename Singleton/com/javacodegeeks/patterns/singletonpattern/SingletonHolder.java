package com.javacodegeeks.patterns.singletonpattern;

/**
 * Describe class <code>SingletonHolder</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public final class SingletonHolder { // NOPMD

  // SingletonHolder prevents any other class from instantiating
  private SingletonHolder() {
    throw new IllegalStateException("Private constructor.");
  }

  // Providing Global point of access
  /**
   * Describe <code>getSingletonInstance</code> method here.
   *
   * @return a <code>SingletonHolder</code> value
   */
  public static SingletonHolder getSingletonInstance() {
    return Holder.INSTANCE;
  }
  
  // Static member class member that holds only one instance of the
  // SingletonHolder class
  private static class Holder { // NOPMD
    /** Describe variable <code>singletonInstance</code> here. */
    public static final SingletonHolder INSTANCE 
      = new SingletonHolder();
  }

}
