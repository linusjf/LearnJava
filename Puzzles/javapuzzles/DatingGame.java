package javapuzzles;

import java.util.Calendar;
import java.util.Date;

public enum DatingGame {
  ;
  @SuppressWarnings("deprecation")
  public static void main(String[] args) {
    Calendar cal = Calendar.getInstance();
    cal.set(1999, 12, 31);

    // Year, Month, Day
    System.out.print(cal.get(Calendar.YEAR) + " ");
    Date d = cal.getTime();
    System.out.println(d.getDay());
    altMain(args);
  }

  public static void altMain(String... args) {
    Calendar cal = Calendar.getInstance();
    cal.set(1999, Calendar.DECEMBER, 31);
    System.out.print(cal.get(Calendar.YEAR) + " ");
    System.out.println(cal.get(Calendar.DAY_OF_MONTH));
  }
}
