package pmdtest;

import java.util.concurrent.ThreadLocalRandom;

public final class ThrowSwitch {
  private ThrowSwitch() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings({"fallthrough", "PMD.MissingBreakInSwitch", "PMD.DoNotCallSystemExit"})
  public static void main(String... args) {
    int errCode = getSimulatedErrorCode(ThreadLocalRandom.current());

    switch (errCode) {
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
        throw new AssertionError("Error code: " + errCode);
      default:
        System.exit(0);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static int getSimulatedErrorCode(ThreadLocalRandom tlr) {
    return tlr.nextInt(0, 6);
  }
}
