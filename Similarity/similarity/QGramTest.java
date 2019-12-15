package similarity;

import info.debatty.java.stringsimilarity.QGram;

public enum QGramTest {
  ;
  public static void main(String[] args) {
    QGram dig = new QGram(2);

    // AB BC CD CE
    // 1  1  1  0
    // 1  1  0  1
    // Total: 2
    System.out.println("\nQGram: \n");
    System.out.println(dig.distance("ABCD", "ABCE"));
  }
}
