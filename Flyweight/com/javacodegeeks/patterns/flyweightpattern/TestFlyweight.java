package com.javacodegeeks.patterns.flyweightpattern;

/**
 * *
 *
 * <p>Main class to test the flyweight pattern.
 */
public enum TestFlyweight {
  ;
  private static final String ASTERIXES = "*************************";

  /**
   * Main class.
   *
   * @param args <code>String</code> array of arguments.
   */
  public static void main(String[] args) {
    Code code = new Code();
    code.setCode("C Code...");
    Platform platform = PlatformFactory.getPlatformInstance("C");
    platform.execute(code);
    System.out.println(ASTERIXES);
    code.setCode("C Code2...");
    platform = PlatformFactory.getPlatformInstance("C");
    platform.execute(code);
    System.out.println(ASTERIXES);
    code.setCode("JAVA Code...");
    platform = PlatformFactory.getPlatformInstance("JAVA");
    code.setCode("JAVA Code2...");
    platform.execute(code);
    System.out.println(ASTERIXES);
    code.setCode("RUBY Code...");
    platform = PlatformFactory.getPlatformInstance("RUBY");
    platform.execute(code);
    System.out.println(ASTERIXES);
    code.setCode("RUBY Code2...");
    platform = PlatformFactory.getPlatformInstance("RUBY");
    platform.execute(code);
  }
}
