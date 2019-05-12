package com.lambdaworks.jni;

// Copyright (C) 2011 - Will Glozer.  All rights reserved.

/**
 * Exception thrown when the current platform cannot be detected.
 *
 * @author Will Glozer
 */
public class UnsupportedPlatformException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Creates a new <code>UnsupportedPlatformException</code> instance.
   *
   * @param s a <code>String</code> value
   */
  public UnsupportedPlatformException(String s) {
    super(s);
  }
}
