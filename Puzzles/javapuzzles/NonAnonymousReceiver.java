package javapuzzles;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonAnonymousReceiver {

  public Iterator<String> emptyIterator2() {
    return new AbstractEmptyIterator() {};
  }

  abstract class AbstractEmptyIterator implements Iterator<String> {
    public boolean hasNext(AbstractEmptyIterator this) {
      return false;
    }

    public String next(AbstractEmptyIterator this) {
      throw new NoSuchElementException("No next element");
    }
  }
}
