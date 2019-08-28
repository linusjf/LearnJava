package networking;

class Producer extends Thread {
  private Resource item;

  public Producer(Resource resource) {
    super();
    item = resource;
  }

  public void run() {
    int pause;
    int newLevel;
    while(true) {
      try {
        // Add 1 to level and return new level…
        newLevel = item.addOne();
        System.out.println("<Producer> New level: " + newLevel);
        pause = (int)(Math.random() * 5000);
        //'Sleep' for 0-5 seconds…
        sleep(pause);
      } catch (InterruptedException interruptEx) {
        System.out.println(interruptEx);
        interrupt();
      }
    }
  }
}
