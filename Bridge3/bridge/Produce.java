package bridge;

class Produce implements Workshop {
  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public void work() {
    System.out.print("Produced");
  }
}
