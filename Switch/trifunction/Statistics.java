package trifunction;

import java.time.Period;
import player.TennisPlayer;

public final class Statistics {
  private Statistics() {
    throw new IllegalStateException("Private constructor");
  }

  public static String computeBackhandTrend(TennisPlayer player, Period period,
                                            String owner) {
    return "Backhand trend...";
  }

  public static String computeForehandTrend(TennisPlayer player, Period period,
                                            String owner) {
    return "Forehand trend...";
  }

  public static String computeServeTrend(TennisPlayer player, Period period,
                                         String owner) {
    return "Serve trend...";
  }
}
