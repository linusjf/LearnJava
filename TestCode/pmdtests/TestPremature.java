package pmdtests;

public enum TestPremature {
  ;

  public static void main(String... args) {

    String one = "ONE";
    String two = "TWO";

    System.out.println(compareString(one, two));
    System.out.println(compareStringAgain(one, two));
  }

  public static int compareString(String value1, String value2) {
    int result;

    if (value1 == null || value2 == null) return compareNulls(value1, value2);

    result = value1.compareToIgnoreCase(value2);

    if (result != 0) return result;

    return value1.compareTo(value2);
  }

  public static int compareNulls(Object a, Object b) {
    if (a == null && b == null) return 0;
    if (a == null && b != null) return 1;
    return -1;
  }

  public static int compareStringAgain(String value1, String value2) {
    int result;

    if (value1 == null || value2 == null) result = compareNulls(value1, value2);
    else {
      result = value1.compareToIgnoreCase(value2);

      if (result == 0) result = value1.compareTo(value2);
    }
    return result;
  }
}
