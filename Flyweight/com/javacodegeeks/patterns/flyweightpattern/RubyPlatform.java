package com.javacodegeeks.patterns.flyweightpattern;

/**
 * Describe class <code>RubyPlatform</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class RubyPlatform implements Platform {
  /** Creates a new <code>RubyPlatform</code> instance. */
  @SuppressWarnings("PMD.SystemPrintln")
  public RubyPlatform() {
    System.out.println("RubyPlatform object created");
  }

  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public void execute(Code code) {
    System.out.println("Compiling and executing Ruby code.");
  }
}
