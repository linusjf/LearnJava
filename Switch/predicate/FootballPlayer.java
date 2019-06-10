package predicate;

public class FootballPlayer implements Player {
  private String name;

  public FootballPlayer(String name) {
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
