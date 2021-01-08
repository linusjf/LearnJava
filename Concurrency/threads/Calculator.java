package threads;

@SuppressWarnings("PMD.SystemPrintln")
public class Calculator implements Runnable {
  private final int number;

  public Calculator(int number) {
    this.number = number;
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public void run() {
    for (int i = 1; i <= 10; i++) {
      System.out.printf("%s: %d * %d = %d%n",
                        Thread.currentThread().getName(),
                        number,
                        i,
                        i * number);
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Calculator))
      return false;
    Calculator other = (Calculator)o;
    if (!other.canEqual((Object)this))
      return false;
    if (this.number != other.number)
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Calculator;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.number;
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "Calculator(number=" + this.number + ")";
  }
}
