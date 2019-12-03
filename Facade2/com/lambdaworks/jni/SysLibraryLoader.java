package com.lambdaworks.jni;

// Copyright (C) 2011 - Will Glozer.  All rights reserved.
/**
 * A native library loader that simply invokes {@link System#loadLibrary}. The
 * shared library path and filename are platform specific.
 *
 * @author Will Glozer
 */
public class SysLibraryLoader implements LibraryLoader {

  /**
   * Load a shared library.
   *
   * @param name Name of the library to load.
   * @param verify Ignored, no verification is done.
   * @return true if the library was successfully loaded.
   */
  @Override
  @SuppressWarnings("PMD.AvoidUsingNativeCode")
  public boolean load(String name, boolean verify) {
    try {
      System.getSecurityManager().checkLink(name);
      System.loadLibrary(name);
      return true;
    } catch (SecurityException e) {
      System.err.println("Error loading system library " + name + " : "
                         + e.getMessage());
      return false;
    }
  }
}
