package test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("PMD.LawOfDemeter")
public final class SoftWorker {
  public void work(List<Integer> numbers) {
    for (int i: numbers) {
      int number = numbers.get(i);
      if (isPair(number)) {
        doNothing();
      }
    }
  }

  private boolean isPair(int number) {
    return number % 2 == 0;
  }

  private void doNothing() {
    // empty function
  }

  public static void main(String[] args) {
    List<Integer> numbers =
        IntStream.rangeClosed(0, 400_000).boxed().collect(Collectors.toList());
    SoftWorker softWorker = new SoftWorker();
    softWorker.work(numbers);
  }
}
