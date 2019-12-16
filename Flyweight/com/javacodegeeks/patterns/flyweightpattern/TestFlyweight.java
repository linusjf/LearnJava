package com.javacodegeeks.patterns.flyweightpattern;

/** Main class to test the flyweight pattern. */
public enum TestFlyweight {
  ;
  private static final String ASTERIXES = "*************************";

  private static Platform platformC = PlatformFactory.getPlatformInstance("C");
  private static Platform platformJava = PlatformFactory.getPlatformInstance("Java");
  private static Platform platformRuby = PlatformFactory.getPlatformInstance("Ruby");

  /**
   * Main class.
   *
   * @param args <code>String</code> array of arguments.
   */
  public static void main(String[] args) {
    Code code = new Code();
    code.setCode("C Code...");
    platformC.execute(code);
    System.out.println(ASTERIXES);
    code.setCode("C Code2...");
    platformC.execute(code);
    System.out.println(ASTERIXES);
    code.setCode("JAVA Code...");
    code.setCode("JAVA Code2...");
    platformJava.execute(code);
    System.out.println(ASTERIXES);
    code.setCode("RUBY Code...");
    platformRuby.execute(code);
    System.out.println(ASTERIXES);
    code.setCode("RUBY Code2...");
    platformRuby.execute(code);
  }
}
