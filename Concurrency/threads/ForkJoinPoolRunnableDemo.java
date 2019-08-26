package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

public enum ForkJoinPoolRunnableDemo {
  ;
  private static final int EXPECTED_PRICE = 12;
  private static final int BATCH_SIZE = 10;

  public static void main(String[] args) {
    ProductListGenerator generator = new ProductListGenerator();
    List<Product> products = generator.generate(10_000);
    Task task = new Task(products, 0, products.size(), 0.20);
    ForkJoinTask<?> t = ForkJoinTask.adapt(task);
    task.setForkedTask(t);
    ForkJoinPool pool = new ForkJoinPool();
    pool.execute(t);
    do {
      System.out.printf("Main: Thread Count: %d\n",
                        pool.getActiveThreadCount());
      System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
      System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
      try {
        TimeUnit.MILLISECONDS.sleep(5);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    } while (!t.isDone());
    pool.shutdown();
    if (t.isCompletedNormally()) {
      System.out.printf("Main: The process has completed normally.\n");
    }
    for (Product product: products) {
      if (product.getPrice() != EXPECTED_PRICE) {
        System.out.printf(
            "Product %s: %f\n", product.getName(), product.getPrice());
      }
    }
    System.out.println("Main: End of the program.\n");
  }

  static class Product {
    private String name;
    private double price;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public double getPrice() {
      return price;
    }

    public void setPrice(double price) {
      this.price = price;
    }
  }

  static class ProductListGenerator {
    public List<Product> generate(int size) {
      List<Product> ret = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        Product product = new Product();
        product.setName("Product " + i);
        product.setPrice(10);
        ret.add(product);
      }
      return ret;
    }
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Task implements Runnable {
    private List<Product> products;
    private int first;
    private int last;
    private double increment;
    private ForkJoinTask<?> t;

    Task(List<Product> products, int first, int last, double increment) {
      super();
      this.products = products;
      this.first = first;
      this.last = last;
      this.increment = increment;
    }

    @SuppressWarnings("checkstyle:hiddenfield")
    void setForkedTask(ForkJoinTask<?> t) {
      this.t = t;
    }

    @Override
    public void run() {
      if (last - first < BATCH_SIZE)
        updatePrices();
      else {
        int middle = (last + first) / 2;
        System.out.printf("Task: Pending tasks: %s\n", ForkJoinTask.getQueuedTaskCount());
        Task t1 = new Task(products, first, middle + 1, increment);
        Task t2 = new Task(products, middle + 1, last, increment);
        ForkJoinTask<?> task1 = ForkJoinTask.adapt(t1);
        ForkJoinTask<?> task2 = ForkJoinTask.adapt(t2);
        t1.setForkedTask(task1);
        t2.setForkedTask(task2);
        ForkJoinTask.invokeAll(task1, task2);
      }
    }

    private void updatePrices() {
      for (int i = first; i < last; i++) {
        Product product = products.get(i);
        product.setPrice(product.getPrice() * (1 + increment));
      }
    }
  }
}
