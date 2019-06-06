package strategy;

public enum Main {
  ;

  public static void main(String[] args) {

    PlayerCreator playerCreator = new PlayerCreator();
    Player tennisPlayer =
      playerCreator.createPlayer(PlayerTypes.TENNIS);

    System.out.println(tennisPlayer);
    Player footballPlayer =
      PlayerTypes.valueOf("FOOTBALL").createPlayer();

    System.out.println(footballPlayer);
  }
}
