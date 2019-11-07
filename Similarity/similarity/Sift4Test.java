package similarity;

import info.debatty.java.stringsimilarity.experimental.Sift4;

public enum Sift4Test {
  ;

  public static void main(String[] args) {
    String s1 = "This is the first string";
    String s2 = "And this is another string";
    Sift4 sift4 = new Sift4();
    sift4.setMaxOffset(5);
    double expResult = 11.0;
    double result = sift4.distance(s1, s2);
    System.out.println("\nSift4: \n");
    assert expResult == result;
    System.out.println(expResult == result);
  }
}
