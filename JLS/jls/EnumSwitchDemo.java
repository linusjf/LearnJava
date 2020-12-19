package jls;

@SuppressWarnings("all")
enum Month {
  JANUARY(1),
  FEBRUARY(2),
  MARCH(3),
  APRIL(4),
  MAY(5),
  JUNE(6),
  JULY(7),
  AUGUST(8),
  SEPTEMBER(9),
  OCTOBER(10),
  NOVEMBER(11),
  DECEMBER(12);

  private final int monthNumber;

  private Month(int month) {
    this.monthNumber = month;
  }

  public int getMonthNumber() {
    return monthNumber;
  }
}

@SuppressWarnings("all")
public final class EnumSwitchDemo {
  private EnumSwitchDemo() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static int getMonthNumber(Month month) {
    return month.getMonthNumber();
  }

  public static int getMonth(Month month) {
    int monthNum = 0;
    switch (month) {
      case JANUARY:
        monthNum = month.ordinal() + 1;
        break;
      case FEBRUARY:
        monthNum = month.ordinal() + 1;
        break;
      case MARCH:
        monthNum = month.ordinal() + 1;
        break;
      case APRIL:
        monthNum = month.ordinal() + 1;
        break;
      case MAY:
        monthNum = month.ordinal() + 1;
        break;
      case JUNE:
        monthNum = month.ordinal() + 1;
        break;
      case JULY:
        monthNum = month.ordinal() + 1;
        break;
      case AUGUST:
        monthNum = month.ordinal() + 1;
        break;
      case SEPTEMBER:
        monthNum = month.ordinal() + 1;
        break;
      case OCTOBER:
        monthNum = month.ordinal() + 1;
        break;
      case NOVEMBER:
        monthNum = month.ordinal() + 1;
        break;
      case DECEMBER:
        monthNum = month.ordinal() + 1;
        break;
      default:
        monthNum = 0;
        break;
    }
    return monthNum;
  }

  public static void main(String[] args) {
    String month = "August".toUpperCase();
    int returnedMonthNumber =
        EnumSwitchDemo.getMonthNumber(Month.valueOf(Month.class, month));
    System.out.println(returnedMonthNumber);
    returnedMonthNumber =
        EnumSwitchDemo.getMonth(Month.valueOf(Month.class, month));
    System.out.println(returnedMonthNumber);
  }
}
