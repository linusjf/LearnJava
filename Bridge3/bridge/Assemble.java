package bridge;

class Assemble implements Workshop {
  @Override
  @SuppressWarnings("PMD.SystemPrintln")
  public void work() {
    System.out.print(" And");

    System.out.println(" Assembled.");
  }
}
