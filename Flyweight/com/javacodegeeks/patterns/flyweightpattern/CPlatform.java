package com.javacodegeeks.patterns.flyweightpattern;

/**
 * Describe class <code>CPlatform</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class CPlatform implements Platform {
  /** Creates a new <code>CPlatform</code> instance. */
  public CPlatform() {
    System.out.println("CPlatform object created");
  }

  @Override
  public void execute(Code code) {
    System.out.println("Compiling and executing C code.");
  }
}
