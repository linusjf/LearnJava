package refactoringguru.iterator.example.iterators;

import refactoringguru.iterator.example.profile.Profile;

public interface ProfileIterator {
  boolean hasNext();

  Profile getNext();

  void reset();
}
