package player;
:q

public enum Main {
  ;
  public static void main(String[] args) {
    PlayerFactory playerFactory = new PlayerFactory();
    Player snookerPlayer = playerFactory.createPlayer(Type.SNOOKER, 8);
    int snookerPlayerEndurance = snookerPlayer.playerEndurance();
    System.out.println("Snooker player endurance = " + snookerPlayerEndurance);
  }
}
