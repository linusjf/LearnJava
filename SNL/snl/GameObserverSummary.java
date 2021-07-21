package snl;

public class GameObserverSummary implements GameObserver {
  private int turns;

  public void turn() {
    turns++;
  }

  public int turns() {
    return turns;
  }
}
