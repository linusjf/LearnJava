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
    return iter;
  }

  static interface<T extends Iterator<String>> MyIterator
      extends Iterator<String> {

    public <T> boolean hasNext(T this);

    public <T> String next(T this);
  }
}
