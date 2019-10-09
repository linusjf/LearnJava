package javapuzzles;

@SuppressWarnings("PMD")
public enum LongDivision {
  ;
  public static void main(String[] args) {
    final long microsPerDay = 24 * 60 * 60 * 1000 * 1000;
    final long millisPerDay = 24 * 60 * 60 * 1000;
    System.out.println(microsPerDay / millisPerDay);

    final long microsPerDayLong = 24L * 60 * 60 * 1000 * 1000;
    final long millisPerDayLong = 24L * 60 * 60 * 1000;
    System.out.println(microsPerDayLong / millisPerDayLong);
  }
}
