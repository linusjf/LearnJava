package refactoringguru.iterator.example.iterators;

import java.util.ArrayList;
import java.util.List;
import refactoringguru.iterator.example.profile.Profile;
import refactoringguru.iterator.example.socialnetworks.LinkedIn;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class LinkedInIterator implements ProfileIterator {
  private final LinkedIn linkedIn;
  private final String type;
  private final String email;
  private int currentPosition;
  private final List<String> emails = new ArrayList<>();
  private final List<Profile> contacts = new ArrayList<>();

  public LinkedInIterator(LinkedIn linkedIn,
                          String type,
                          String email) {
    this.linkedIn = linkedIn;
    this.type = type;
    this.email = email;
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private void lazyLoad() {
    if (emails.isEmpty()) {
      List<String> profiles =
          linkedIn.requestRelatedContactsFromLinkedInAPI(
              email, type);
      for (String profile: profiles) {
        this.emails.add(profile);
        this.contacts.add(null);
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
    if (!hasNext()) {
      return null;
    }

    Profile friendContact = contacts.get(currentPosition);
    if (friendContact == null) {
      String friendEmail = emails.get(currentPosition);
      friendContact =
          linkedIn.requestContactInfoFromLinkedInAPI(
              friendEmail);
      contacts.set(currentPosition, friendContact);
    }
    currentPosition++;
    return friendContact;
  }

  @Override
  public void reset() {
    currentPosition = 0;
  }
}
