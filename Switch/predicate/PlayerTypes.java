package predicate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public enum PlayerTypes {
  TENNIS(
    Collections.unmodifiableList(
      Arrays.asList(
        () -> new TennisPlayer("Rafael Nadal"),
        () -> new TennisPlayer("Roger Federer"),
        () -> new TennisPlayer("Andy Murray")
      )
    ),
    Collections.unmodifiableList(
      Arrays.asList(
        rank -> rank == 1,
        rank -> rank > 1 && rank < 5,
        rank -> rank >= 5 && rank <= 10
      )
    )
  ),
  FOOTBALL(
    Collections.unmodifiableList(
      Arrays.asList(
        () -> new FootballPlayer("Lionel Messi"),
        () -> new FootballPlayer("Cristiano Ronaldo")
      )
    ),
    Collections.unmodifiableList(
      Arrays.asList(
        rank -> rank == 1 || rank == 2,
        rank -> rank > 2 && rank <= 10
      )
    )
  ),
  SNOOKER(
    Collections.unmodifiableList(
      Arrays.asList(
        () -> new SnookerPlayer("Ronnie O'Sullivan"),
        () -> new SnookerPlayer("Mark Selby"),
        () -> new SnookerPlayer("John Higgins"),
        () -> new SnookerPlayer("Neil Robertson")
      )
    ),
    Collections.unmodifiableList(
      Arrays.asList(
        rank -> rank == 1,
        rank -> rank == 2,
        rank -> rank > 3 && rank < 7,
        rank -> rank >= 7 && rank <= 10
      )
    )
  );
  private final List<Supplier<Player>> names;
  private final List<Predicate<Integer>> conditions;

  PlayerTypes(
    List<Supplier<Player>> names,
    List<Predicate<Integer>> conditions
  ) {
    this.names = names;
    this.conditions = conditions;
  }

  public static Player supplyPlayer(String playerType, int rank) {
    if (rank < 1 || rank > 10) {
      throw new IllegalArgumentException("Invalid rank: " + rank);
    }
    PlayerTypes type = getPlayerType(playerType);
    List<Predicate<Integer>> selectors = 
      type.conditions;
List<Supplier<Player>> players = type.names;
  return getPlayerForConditions(selectors,players,rank);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static Player getPlayerForConditions(List<Predicate<Integer>> selectors,
 List<Supplier<Player>> players,
 int rank) {
OptionalInt indexOpt = IntStream.range(0, selectors.size())
     .filter(i -> selectors.get(i).test(rank))
     .findFirst();
if (indexOpt.isPresent())
  return players.get(indexOpt.getAsInt()).get();
    throw new IllegalStateException("The enum is corrupted");
  }

  public static PlayerTypes getPlayerType(String type) {
    return PlayerTypes.valueOf(type);
  }
}
