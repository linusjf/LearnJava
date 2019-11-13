package refactoringguru.iterator.example.socialnetworks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import refactoringguru.iterator.example.iterators.LinkedInIterator;
import refactoringguru.iterator.example.iterators.ProfileIterator;
import refactoringguru.iterator.example.profile.Profile;

public class LinkedIn implements SocialNetwork {
  private final List<Profile> contacts;

  public LinkedIn(List<Profile> cache) {
    Optional<List<Profile>> list = Optional.ofNullable(cache);
    this.contacts = list.orElse(new ArrayList<Profile>());
  }

  public Profile requestContactInfoFromLinkedInAPI(String profileEmail) {
    // Here would be a POST request to one of the LinkedIn API endpoints.
    // Instead, we emulates long network connection, which you would expect
    // in the real life...
    simulateNetworkLatency();
    System.out.println("LinkedIn: Loading profile '" + profileEmail
                       + "' over the network...");

    // ...and return test data.
    return findContact(profileEmail);
  }

  public List<String> requestRelatedContactsFromLinkedInAPI(
      String profileEmail,
      String contactType) {
    // Here would be a POST request to one of the LinkedIn API endpoints.
    // Instead, we emulates long network connection, which you would expect
    // in the real life.
    simulateNetworkLatency();
    System.out.println("LinkedIn: Loading '" + contactType + "' list of '"
                       + profileEmail + "' over the network...");

    // ...and return test data.
    Optional<Profile> profile = Optional.ofNullable(findContact(profileEmail));

    return profile.isPresent() ? profile.get().getContacts(contactType) : null;
  }

  private Profile findContact(String profileEmail) {
    for (Profile profile: contacts) {
      if (profile.getEmail().equals(profileEmail)) {
        return profile;
      }
    }
    return null;
  }

  private void simulateNetworkLatency() {
    try {
      Thread.sleep(2500);
    } catch (InterruptedException ex) {
      System.err.println(ex.getMessage());
    }
  }

  @Override
  public ProfileIterator createFriendsIterator(String profileEmail) {
    return new LinkedInIterator(this, "friends", profileEmail);
  }

  @Override
  public ProfileIterator createCoworkersIterator(String profileEmail) {
    return new LinkedInIterator(this, "coworkers", profileEmail);
  }
}
