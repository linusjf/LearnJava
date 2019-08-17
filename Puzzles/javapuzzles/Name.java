package javapuzzles;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("PMD.ShortClassName")
public class Name {
  private final String first;
  private final String last;

  public Name(String first, String last) {
    this.first = first;
    this.last = last;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Name))
      return false;
    Name n = (Name) o;
    return n.first.equals(first) && n.last.equals(last);
  }

  @Override
  public int hashCode() {
    return 37 * first.hashCode() + last.hashCode();
  }

  public static void main(String[] args) {
    Set<Name> s = new HashSet<>();
    s.add(new Name("Mickey", "Mouse"));
    System.out.println(s.contains(new Name("Mickey", "Mouse")));
  }
}
