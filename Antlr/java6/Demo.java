package java6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * * Excerpted from "The Definitive ANTLR 4 Reference", published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, courses, books,
 * articles, and the like. Contact us if you are in doubt. We make no guarantees that this code is
 * fit for any purpose. Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book
 * information. *
 */
@SuppressWarnings("PMD.ShortClassName")
public class Demo {
  @SuppressWarnings("PMD.ShortMethodName")
  void f(int x, String y) {
    // empty method body
  }

  @SuppressWarnings("PMD.ShortMethodName")
  int[] g(/*no args*/) {
    return new int[0];
  }

  @SuppressWarnings("PMD.ShortMethodName")
  List<List<Map<String, Integer>>> h() {
    return new ArrayList<List<Map<String, Integer>>>(0);
  }
}
