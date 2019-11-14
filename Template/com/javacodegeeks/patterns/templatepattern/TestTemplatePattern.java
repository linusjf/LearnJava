package com.javacodegeeks.patterns.templatepattern;

public enum TestTemplatePattern {
  ;

  public static void main(String[] args) {
    System.out.println("For MYSQL....");
    ConnectionTemplate template = new MySqlCSVCon();
    template.disableLogging();
    template.run();
    System.out.println("For Oracle...");
    template = new OracleTxtCon();
    template.run();
  }
}
