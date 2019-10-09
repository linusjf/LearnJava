package supplier;

import player.Player;

public enum Main {
  ;
  public static void main(String[] args) {
    PlayerSupplier playerSupplier = new PlayerSupplier();
    Player snookerPlayer = playerSupplier.supplyPlayer("SNOOKER");

    System.out.println(snookerPlayer);
  }
}
