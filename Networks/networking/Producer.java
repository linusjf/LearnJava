package networking;

import java.util.Random;

class Producer extends Thread {
  private final Resource item;
  private final Random random = new Random();

  Producer(Resource resource) {
    super();
    item = resource;
  }

  @Override
  public void run() {
    int pause;
    int newLevel;
    while (true) {
      try {
        // Add 1 to level and return new level…
        newLevel = item.addOne();
        System.out.println("<Producer> New level: " + newLevel);
        pause = random.nextInt(5000);

        // 'Sleep' for 0-5 seconds…
        sleep(pause);
      } catch (InterruptedException interruptEx) {
        System.out.println(interruptEx);
        interrupt();
      }
    }
  }
}
