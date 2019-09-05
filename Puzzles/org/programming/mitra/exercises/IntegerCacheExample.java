package org.programming.mitra.exercises;

/**
 * <b>IntegerCacheExample.</b>
 *
 * @see Annotation
 * @author Naresh Joshi
 * <a href="https://www.programmingmitra.com/2018/11/java-integer-cache.html">Article</a>
 */
public enum IntegerCacheExample {
  ;

  @SuppressWarnings("PMD.CompareObjectsWithEquals")
  public static void main(String[] args) {
    int a = 127;
    int b = 127;

    System.out.println(a == b);  // Output -- true

    Integer objC = 127;
    // Auto boxing example, compiler converts it to Integer
    // c = Integer.valueOf(127);
    Integer objD = 127;
    // Compiler converts it to Integer d = Integer.valueOf(127);

    int e = objC;
    // Auto unboxing example, Compiler converts this line to int
    // e = c.intValue();

    System.out.println("e = " + e);

    // Output of below line is true because Integer class cache integer objects
    // which falls in range -128 to 127, and returns same object for every
    // autoboxing invocation
    System.out.println(objC == objD);  // Output -- true

    System.out.println(objC.equals(objD));  // Output -- true

    a = 128;
    b = 128;

    System.out.println(a == b);  // Output -- true

    objC = 128;
    objD = 128;

    System.out.println(objC == objD);
    // Output -- false

    System.out.println(objC.equals(objD));  // Output -- true
  }
}
