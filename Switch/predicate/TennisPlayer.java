package predicate;

public class TennisPlayer implements Player {
  private final String name;

  public TennisPlayer(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}
