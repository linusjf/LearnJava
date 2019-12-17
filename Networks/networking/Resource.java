package networking;

class Resource {
  private static final int MAX = 5;
  private int numResources;

  Resource(int startLevel) {
    numResources = startLevel;
  }

  public int getLevel() {
    return numResources;
  }

  public int addOne() {
    try {
      synchronized (this) {
        while (numResources >= MAX)
          wait();
        numResources++;

        // 'Wake up' any waiting consumer…
        notifyAll();
      }
    } catch (InterruptedException interruptEx) {
      System.out.println(interruptEx);
      Thread.currentThread().interrupt();
    }
    return numResources;
  }

  public int takeOne() {
    try {
      synchronized (this) {
        while (numResources == 0)
          wait();
        numResources--;

        // 'Wake up' waiting producer…
        notifyAll();
      }
    } catch (InterruptedException interruptEx) {
      System.out.println(interruptEx);
      Thread.currentThread().interrupt();
    }
    return numResources;
  }
}
