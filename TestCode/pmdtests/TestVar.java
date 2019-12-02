package pmdtests;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("checkstyle:magicnumber")
public final class TestVar {

  private TestVar() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String... args) {
    List<String> prods = new ArrayList<>();
    prods.add("X");
    prods.add("Y");
    prods.add("Z");
    addProducts(prods);
  }

  private static int addProducts(List<String> prods) {
    var rowAdder = new RowAdder();
    rowAdder.add(prods);
    processDocument(rowAdder);
    return rowAdder.intValue();
  }

  private static void processDocument(RowAdder rowAdder) {
    System.out.println("Processing " + rowAdder);
  }

  private static class RowAdder {
    public void add(List<String> prods) {
      System.out.println("Adding " + prods);
    }

    public int intValue() {
      return 5;
    }
  }
}
