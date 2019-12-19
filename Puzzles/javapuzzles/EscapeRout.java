package javapuzzles;

public enum EscapeRout {
  ;
  @SuppressWarnings({"checkstyle:avoidescapedunicodecharacters", "checkstyle:illegaltokentext",
  "PMD.LawOfDemeter"})
  public static void main(String[] args) {
    // \u0022 is the Unicode escape for double quote "
    System.out.println("a\u0022.length() + \u0022b".length());
    System.out.println("a".length() + "b".length());
    System.out.println("a\".length() + \"b".length());
  }
}
