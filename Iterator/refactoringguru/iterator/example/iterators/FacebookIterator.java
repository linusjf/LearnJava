package refactoringguru.iterator.example.iterators;

import java.util.ArrayList;
import java.util.List;
import refactoringguru.iterator.example.profile.Profile;
import refactoringguru.iterator.example.socialnetworks.Facebook;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class FacebookIterator implements ProfileIterator {
  private final Facebook facebook;
  private final String type;
  private final String email;
  private int currentPosition;
  private final List<String> emails = new ArrayList<>();
  private final List<Profile> profiles = new ArrayList<>();

  public FacebookIterator(Facebook facebook, String type, String email) {
    this.facebook = facebook;
    this.type = type;
    this.email = email;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private void lazyLoad() {
    if (emails.isEmpty()) {
      List<String> friends = facebook.requestProfileFriendsFromFacebook(this.email, this.type);
      for (String profile : friends) {
        this.emails.add(profile);
        this.profiles.add(null);
      }
    }
  }

  @Override
  public boolean hasNext() {
    lazyLoad();
    return currentPosition < emails.size();
  }

  @Override
  public Profile getNext() {
    if (!hasNext())
      return null;

    Profile friendProfile = profiles.get(currentPosition);
    if (friendProfile == null) {
      String friendEmail = emails.get(currentPosition);
      friendProfile = facebook.requestProfileFromFacebook(friendEmail);
      profiles.set(currentPosition, friendProfile);
    }
    currentPosition++;
    return friendProfile;
  }

  @Override
  public void reset() {
    currentPosition = 0;
  }
}
