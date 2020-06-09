package refactoringguru.iterator.example.socialnetworks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;
import refactoringguru.iterator.example.iterators.LinkedInIterator;
import refactoringguru.iterator.example.iterators.ProfileIterator;
import refactoringguru.iterator.example.profile.Profile;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class LinkedIn implements SocialNetwork {
  private static final Logger LOGGER =
      Logger.getLogger(LinkedIn.class.getName());

  private final List<Profile> contacts;

  public LinkedIn(List<Profile> cache) {
    Optional<List<Profile>> list = Optional.ofNullable(cache);
    this.contacts = list.orElse(new ArrayList<Profile>());
  }

  @SuppressWarnings("PMD.SystemPrintln")
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

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
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

  @SuppressWarnings("PMD.LawOfDemeter")
  private Profile findContact(String profileEmail) {
    for (Profile profile: contacts) {
      if (profile.getEmail().equals(profileEmail))
        return profile;
    }
    return null;
  }

  private void simulateNetworkLatency() {
    try {
      TimeUnit.MILLISECONDS.sleep(2500);
    } catch (InterruptedException ex) {
      LOGGER.severe(ex.getMessage());
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
