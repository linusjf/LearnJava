package snl;

public class SimpleGame {
  public static void main(String... args) {
    Board board = new EfiBoard();
    board.play(new GameObserverPrinter());
  }
}
