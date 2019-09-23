package javapuzzles;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AnonymousReceiver {
  public Iterator<String> emptyIterator2() {
    return new Iterator<String>() {
      public boolean hasNext(Iterator<String> this) {
        return false;
      }

      public String next(Iterator<String> this) {
        throw new NoSuchElementException("No next element");
      }
    };
  }
}
