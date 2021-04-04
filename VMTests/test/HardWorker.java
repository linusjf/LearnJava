package test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings({"PMD.LawOfDemeter", "PMD.AvoidLiteralsInIfCondition"})
public final class HardWorker {

  public static void main(String[] args) {
    List<Integer> numbers =
        IntStream.rangeClosed(0, 400_000).boxed().collect(Collectors.toList());
    HardWorker hardWorker = new HardWorker();
    hardWorker.work(numbers);
  }

  public void work(List<Integer> numbers) {
    for (int i: numbers) {
      int number = numbers.get(i);
      if (isPair(number)) {
        doNothing();
      }
    }
  }

  private void doNothing() {
    // empty function
  }

  private boolean isPair(int number) {
    // Unlike SoftWorker's isPair(int) method,
    // this one contains a little bit more code not necessarily
    // related to pair number detection. It's here to detect if
    // a long method can be inlined
    String stringNumber = String.valueOf(number);
    int divRest;
    if (stringNumber.length() == 3)
      divRest = number % 10;
    else if (stringNumber.length() == 4)
      divRest = number % 100;
    else if (stringNumber.length() == 5)
      divRest = number % 1000;
    else if (stringNumber.length() == 6)
      divRest = number % 1000;
    else
      divRest = number % 10_000;
    if (divRest == 0)
      return true;
    return number % 2 == 0;
  }
}
