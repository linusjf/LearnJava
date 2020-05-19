package com.lambdaworks.jni;

import static java.lang.System.*;

import java.util.logging.Logger;

// Copyright (C) 2011 - Will Glozer.  All rights reserved.
/**
 * A native library loader that simply invokes {@link System#loadLibrary}. The shared library path
 * and filename are platform specific.
 *
 * @author Will Glozer
 */
public class SysLibraryLoader implements LibraryLoader {
  private static final Logger LOGGER = Logger.getLogger(SysLibraryLoader.class.getName());

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
      checkLink(getSecurityManager(), name);
      loadLibrary(name);
      return true;
    } catch (SecurityException e) {
      LOGGER.severe(
          () -> String.format("Error loading system library %s : *%s", name, e.getMessage()));
      return false;
    }
  }

  private void checkLink(SecurityManager sm, String name) {
    sm.checkLink(name);
  }
}
