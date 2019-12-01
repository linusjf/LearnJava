package pmdtests;

public final class TestStringInstantiation {

  private TestStringInstantiation() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String... args) {
    String[] vals = splitOn(args,2,"=");
    for (String val: vals)
      System.out.println(val);

  }
  private static String[] splitOn(String[] values,int index, String separator) {
    return new String(values[index]).split(separator);
  }

}
