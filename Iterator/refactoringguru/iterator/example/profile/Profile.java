package refactoringguru.iterator.example.profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Profile {
  private final String email;
  private final String name;
  private final Map<String, List<String>> contacts = new HashMap<>();

  @SuppressWarnings("checkstyle:hiddenfield")
  public Profile(String email, String name, String... contacts) {
    this.email = email;
    this.name = name;

    // Parse contact list from a set of "friend:email@gmail.com" pairs.
    for (String contact : contacts) {
      String[] parts = contact.split(":");
      String contactType = "friend";
      String contactEmail;
      if (parts.length == 1) contactEmail = parts[0];
      else {
        contactType = parts[0];
        contactEmail = parts[1];
      }
      if (!this.contacts.containsKey(contactType))
        this.contacts.put(contactType, new ArrayList<String>());
      this.contacts.get(contactType).add(contactEmail);
    }
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public List<String> getContacts(String contactType) {
    if (!this.contacts.containsKey(contactType))
      this.contacts.put(contactType, new ArrayList<String>());
    return contacts.get(contactType);
  }

  @SuppressWarnings({"PMD.LawOfDemeter", "checkstyle:hiddenfield"})
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Profile)) return false;
    Profile profile = (Profile) o;
    return areEmailsEqual(profile);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private boolean areEmailsEqual(Profile profile) {
    return profile.email.equals(email);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(email);
  }
}
