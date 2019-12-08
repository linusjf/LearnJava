package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public enum ForkJoinPoolDemo {
  ;
  private static final int EXPECTED_PRICE = 12;
  private static final int BATCH_SIZE = 10;

  public static void main(String[] args) {
    ProductListGenerator generator = new ProductListGenerator();
    List<Product> products = generator.generate(10_000);
    Task task = new Task(products, 0, products.size(), 0.20);
    ForkJoinPool pool = new ForkJoinPool();
    pool.execute(task);
    do {
      System.out.printf("Main: Thread Count: %d%n",
                        pool.getActiveThreadCount());
      System.out.printf("Main: Thread Steal: %d%n", pool.getStealCount());
      System.out.printf("Main: Parallelism: %d%n", pool.getParallelism());
      try {
        TimeUnit.MILLISECONDS.sleep(5);
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    } while (!task.isDone());
    pool.shutdown();

    // CPD-OFF
    if (task.isCompletedNormally()) {
      System.out.printf("Main: The process has completed normally.%n");
    }
    for (Product product: products) {
      if (product.getPrice() != EXPECTED_PRICE) {
        System.out.printf(
            "Product %s: %f%n", product.getName(), product.getPrice());
      }
    }
    System.out.println("Main: End of the program.%n");
  }

@SuppressWarnings("PMD.DataClass")
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

  // CPD-ON
  @SuppressWarnings("PMD.ShortClassName")
  static class Task extends RecursiveAction {
    private static final long serialVersionUID = 1L;
    private final List<Product> products;
    private final int first;
    private final int last;
    private final double increment;

    Task(List<Product> products, int first, int last, double increment) {
      super();
      this.products = products;
      this.first = first;
      this.last = last;
      this.increment = increment;
    }

    @Override
    protected void compute() {
      if (last - first < BATCH_SIZE)
        updatePrices();
      else {
        int middle = (last + first) / 2;
        System.out.printf("Task: Pending tasks: %s%n", getQueuedTaskCount());
        Task t1 = new Task(products, first, middle + 1, increment);
        Task t2 = new Task(products, middle + 1, last, increment);
        invokeAll(t1, t2);
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
