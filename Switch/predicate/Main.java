package predicate;

public enum Main {
  ;

  public static void main(String[] args) {
    PlayerCreator playerCreator = new PlayerCreator();
    Player tennisPlayer = playerCreator.createPlayer("TENNIS", 5);
    System.out.println(tennisPlayer);
    Player footballPlayer = PlayerSupplier.supplyPlayer("FOOTBALL", 6);
    System.out.println(footballPlayer);
    Player snookerPlayer = PlayerTypes.supplyPlayer("SNOOKER", 10);
		System.out.println(snookerPlayer);
  }
}
