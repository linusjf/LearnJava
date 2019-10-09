package trifunction;

import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import player.TennisPlayer;

public final class FunctionalStatistics {
  private static final Map<String, TriFunction<TennisPlayer, Period, String, String>> STATISTICS = new HashMap<>();

  private FunctionalStatistics() {
    throw new AssertionError();
  }

  static {
    STATISTICS.put("SERVE", Statistics::computeServeTrend);
    STATISTICS.put("FOREHAND", Statistics::computeForehandTrend);
    STATISTICS.put("BACKHAND", Statistics::computeBackhandTrend);
  }

  public static String computeTrend(
    TennisPlayer tennisPlayer,
    Period period,
    String owner,
    String trend
  ) {
    // clang-format off
    TriFunction<TennisPlayer, Period, String, String> function = Objects.requireNonNull(
      STATISTICS.get(trend),
      "Invalid trend type: " + trend
    );

    // clang-format on
    return function.apply(tennisPlayer, period, owner);
  }
}
