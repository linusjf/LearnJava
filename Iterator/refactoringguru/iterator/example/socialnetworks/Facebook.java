package refactoringguru.iterator.example.socialnetworks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import refactoringguru.iterator.example.iterators.FacebookIterator;
import refactoringguru.iterator.example.iterators.ProfileIterator;
import refactoringguru.iterator.example.profile.Profile;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class Facebook implements SocialNetwork {
  private static final Logger LOGGER =
      Logger.getLogger(Facebook.class.getName());
  private final List<Profile> profiles;

  public Facebook(List<Profile> cache) {
    Optional<List<Profile>> list = Optional.ofNullable(cache);
    this.profiles = list.orElse(new ArrayList<Profile>());
  }

  @SuppressWarnings("PMD.SystemPrintln")
  public Profile requestProfileFromFacebook(String profileEmail) {
    // Here would be a POST request to one of the Facebook API endpoints.
    // Instead, we emulates long network connection, which you would expect
    // in the real life...
    simulateNetworkLatency();
    System.out.println("Facebook: Loading profile '" + profileEmail
                       + "' over the network...");

    // ...and return test data.
    return findProfile(profileEmail);
  }

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  public List<String> requestProfileFriendsFromFacebook(String profileEmail,
                                                        String contactType) {
    // Here would be a POST request to one of the Facebook API endpoints.
    // Instead, we emulates long network connection, which you would expect
    // in the real life...
    simulateNetworkLatency();
    System.out.println("Facebook: Loading '" + contactType + "' list of '"
                       + profileEmail + "' over the network...");

    // ...and return test data.
    Optional<Profile> profile = Optional.ofNullable(findProfile(profileEmail));

    return profile.isPresent() ? profile.get().getContacts(contactType) : null;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private Profile findProfile(String profileEmail) {
    for (Profile profile: profiles) {
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
    return new FacebookIterator(this, "friends", profileEmail);
  }

  @Override
  public ProfileIterator createCoworkersIterator(String profileEmail) {
    return new FacebookIterator(this, "coworkers", profileEmail);
  }
}
