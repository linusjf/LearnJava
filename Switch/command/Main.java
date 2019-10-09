package command;

import player.Player;

public enum Main {
  ;
  public static void main(String[] args) {
    PlayerCreator playerCreator = new PlayerCreator();
    Player tennisPlayer = playerCreator.createPlayer("TENNIS");

    System.out.println(tennisPlayer);

    CreatePlayerCommand createCommand = new CreatePlayerCommand();
    Player snookerPlayer = createCommand.createPlayer("SNOOKER");
    System.out.println(snookerPlayer);
  }
}
