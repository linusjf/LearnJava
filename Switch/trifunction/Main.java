package trifunction;

import java.time.Period;
import player.TennisPlayer;

public enum Main {
  ;

  public static void main(String[] args) {
    String serveTrend = ComputeTennisPlayerStatistics.computeTrend(
        new TennisPlayer(), Period.ZERO, "TENNIS MAGAZINE", "SERVE");
    System.out.println(serveTrend);
    String forehandTrend =
        FunctionalStatistics.computeTrend(new TennisPlayer(), Period.ZERO, "SPORT TV", "FOREHAND");
    System.out.println(forehandTrend);
  }
}
