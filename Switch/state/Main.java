package state;

public enum Main {
  ;
  public static void main(String[] args) {
    Player player = new Player();
    player.register();
    player.unregister();

    // Causes an "Already Unregistered ..." message
    player.unregister();

    player.register();
    player.register();
  }
}
