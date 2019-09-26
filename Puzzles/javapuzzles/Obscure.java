package javapuzzles;

@SuppressWarnings("PMD")
public class Obscure {
  static String System;  
  // Obscures type java.lang.System

  public static void main(String[] args) {
    // Next line won’t compile: System refers to static field
    java.lang.System.out.println("hello, obscure world!");
  }
}
