package predicate;

public class PlayerCreator {

  @SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
  public Player createPlayer(String playerType, int rank) {
    switch (playerType) {
      case "TENNIS":
        return handleTennisPlayer(rank);
      case "FOOTBALL":
        return handleFootballPlayer(rank);
      case "SNOOKER":
        return handleSnookerPlayer(rank);
      default:
        throw new IllegalArgumentException("Invalid player type: ");
    }
  }

  private Player handleTennisPlayer(int rank) {
    if (rank == 1) {
      return new TennisPlayer("Rafael Nadal");
    }
    if (rank > 1 && rank < 5) {
      return new TennisPlayer("Roger Federer");
    }
    if (rank >= 5 && rank <= 10) {
      return new TennisPlayer("Andy Murray");
    }
    return null;
  }

  private Player handleFootballPlayer(int rank) {
    if (rank == 1 || rank == 2) {
      return new FootballPlayer("Lionel Messi");
    }
    if (rank > 2 && rank <= 10) {
      return new FootballPlayer("Cristiano Ronaldo");
    }
    return null;
  }

  @SuppressWarnings("PMD.AvoidLiteralsInIfCondition")
  private Player handleSnookerPlayer(int rank) {
    if (rank == 1) {
      return new SnookerPlayer("Ronnie O'Sullivan");
    }
    if (rank == 2) {
      return new SnookerPlayer("Mark Selby");
    }
    if (rank > 3 && rank < 7) {
      return new SnookerPlayer("John Higgins");
    }
    if (rank >= 7 && rank <= 10) {
      return new SnookerPlayer("Neil Robertson");
    }
    return null;
  }
}
