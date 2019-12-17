package refactoringguru.iterator.example.socialnetworks;

import refactoringguru.iterator.example.iterators.ProfileIterator;

public interface SocialNetwork {
  ProfileIterator createFriendsIterator(
      String profileEmail);

  ProfileIterator createCoworkersIterator(
      String profileEmail);
}
