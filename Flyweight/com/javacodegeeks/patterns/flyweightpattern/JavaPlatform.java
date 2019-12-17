package com.javacodegeeks.patterns.flyweightpattern;

/**
 * Describe class <code>JavaPlatform</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class JavaPlatform implements Platform {
  /** Creates a new <code>JavaPlatform</code> instance. */
  public JavaPlatform() {
    System.out.println("JavaPlatform object created");
  }

  @Override
  public void execute(Code code) {
    System.out.println(
        "Compiling and executing Java code.");
  }
}
