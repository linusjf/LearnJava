package javapuzzles;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonAnonymousReceiver2 {
  public Iterator<String> emptyIterator2() {
    Iterator<String> iter = new Iterator<String>() {
      public boolean hasNext(Iterator<String> iter.this) {
        return false;
      }

      public String next(Iterator<String> iter.this) {
        throw new NoSuchElementException("No next element");
      }
    };
    return iter:
  }
}
