package javapuzzles;

public enum ChocolateCake {
  ;

  @SuppressWarnings("PMD.SimplifyBooleanExpressions")
  public static void main(String[] args) {
    System.out.println(true ? false : true == true ? false : true);
  }
}
