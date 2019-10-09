package com.javacodegeeks.patterns.flyweightpattern;

/**
 * Describe class <code>CPPPlatform</code> here.
 *
 * @author <a href="mailto:root@localhost"></a>
 * @version 1.0
 */
public class CPPPlatform implements Platform {

  /** Creates a new <code>CPPPlatform</code> instance. */
  public CPPPlatform() {
    System.out.println("CPPPlatform object created");
  }

  @Override
  public void execute(Code code) {
    System.out.println("Compiling and executing CPP code.");
  }
}
