package pmdtests;

import java.util.List;

public final class TestVar {

private TestVar() {
  throw new IllegalStateException("Private constructor.");
}

private static int addProducts(List<String> prods) {
    var rowAdder = new RowAdder();
    rowAdder.add(prods);
    processDocument(
                    rowAdder);
    return rowAdder.intValue();
}

private void processDocument(RowAdder rowAdder) {
  // do nothing
  //
System.out.println("Processing " + rowAdder);
}

private static class RowAdder {
public void addProducts(List<String> prods) {
  System.out.println("Adding " + prods);
}

public int intValue() {
return 5;
} 
}
}
