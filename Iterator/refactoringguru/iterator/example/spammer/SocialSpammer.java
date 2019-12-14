package refactoringguru.iterator.example.spammer;

import refactoringguru.iterator.example.iterators.ProfileIterator;
import refactoringguru.iterator.example.profile.Profile;
import refactoringguru.iterator.example.socialnetworks.SocialNetwork;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class SocialSpammer {
  SocialNetwork network;
  ProfileIterator iterator;

  public SocialSpammer(SocialNetwork network) {
    this.network = network;
  }

  public void sendSpamToFriends(String profileEmail, String message) {
    System.out.println("\nIterating over friends...\n");
    iterator = network.createFriendsIterator(profileEmail);
    sendMessages(iterator,message);
  }


  public void sendSpamToCoworkers(String profileEmail, String message) {
    System.out.println("\nIterating over coworkers...\n");
    iterator = network.createCoworkersIterator(profileEmail);
    sendMessages(iterator,message);
  }

  public void sendMessages(ProfileIterator iterator,
      String message) {
    while (iterator.hasNext()) {
      Profile profile = iterator.getNext();
      sendMessage(profile, message);
    }
  }
  
  public void sendMessage(Profile profile, String message) {
      sendMessage(profile.getEmail(), message);
  }

  public void sendMessage(String email, String message) {
    System.out.println("Sent message to: '" + email + "'. Message body: '"
                       + message + "'");
  }
}
