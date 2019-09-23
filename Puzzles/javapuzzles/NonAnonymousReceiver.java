package javapuzzles;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonAnonymousReceiver {

  abstract class EmptyIterator implements Iterator<String> {
    public boolean hasNext(EmptyIterator this) {
      return false;
    }

    public String next(EmptyIterator this) {
      throw new NoSuchElementException("No next element");
    }
  }

  public Iterator<String> emptyIterator2() {
    return new EmptyIterator() {};
  }
}
