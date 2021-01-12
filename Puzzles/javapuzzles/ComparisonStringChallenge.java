package javapuzzles;

@SuppressWarnings("all")
public final class ComparisonStringChallenge {
  private ComparisonStringChallenge() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String... doYourBest) {
    String result = "";
    result += " powerfulCode ".trim() == "powerfulCode" ? "0" : "1";
    result += "flexibleCode" == "flexibleCode" ? "2" : "3";
    result += new String("doYourBest") == new String("doYourBest") ? "4" : "5";
    result += new String("noBugsProject").equals("noBugsProject") ? "6" : "7";
    result += new String("breakYourLimits").intern()
                      == new String("breakYourLimits").intern()
                  ? "8"
                  : "9";
    System.out.println(result);
  }
}
