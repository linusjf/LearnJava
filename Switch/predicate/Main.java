package predicate;

public enum Main {
  ;

  public static void main(String[] args) {
    PlayerCreator playerCreator = new PlayerCreator();
    Player tennisPlayer = playerCreator.createPlayer("TENNIS", 5);
  }
}
