package com.lambdaworks.jni;

// Copyright (C) 2011 - Will Glozer.  All rights reserved.
/**
 * {@code LibraryLoaders} will create the appropriate {@link LibraryLoader} for
 * the VM it is running on.
 *
 * <p>The system property {@code com.lambdaworks.jni.loader} may be used to
 * override loader auto-detection, or to disable loading native libraries
 * entirely via use of the nil loader.
 *
 * @author Will Glozer
 */
public final class LibraryLoaders {  // NOPMD

  private LibraryLoaders() {
    throw new IllegalStateException("Private constructor");
  }

  /**
   * Create a new {@link LibraryLoader} for the current VM.
   *
   * @return the loader.
   */
  public static LibraryLoader loader() {
    final String type = System.getProperty("com.lambdaworks.jni.loader");

    if (type != null) {
      if ("sys".equals(type))
        return new SysLibraryLoader();
      if ("nil".equals(type))
        return new NilLibraryLoader();
      if ("jar".equals(type))
        return new JarLibraryLoader();
      throw new IllegalStateException(
          "Illegal value for com.lambdaworks.jni.loader: " + type);
    }

    final String vmSpec = System.getProperty("java.vm.specification.name");
    return vmSpec.startsWith("Java") ? new JarLibraryLoader()
                                     : new SysLibraryLoader();
  }
}
