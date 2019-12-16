package strategy;

import player.Player;

public enum Main {
  ;

  public static void main(String[] args) {
    PlayerCreator playerCreator = new PlayerCreator();
    Player tennisPlayer = playerCreator.createPlayer(PlayerTypes.TENNIS);

    System.out.println(tennisPlayer);
    Player footballPlayer = playerCreator.createPlayer("FOOTBALL");

    System.out.println(footballPlayer);
  }
}
