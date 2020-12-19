package jls;

@SuppressWarnings("all")
public final class SwitchDemo {
  private SwitchDemo() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  static int check(int sw) {
    int ret = 0;
    switch (sw) {
      case 1:
        ret = 1;
        break;
      default:
        ret = 2;
        break;
    }
    return ret;
  }

  static int checkIfElse(int sw) {
    if (sw == 1)
      return 1;
    else
      return 2;
  }

  static int checkIfElseString(String sw) {
    if ("HELLO".equals(sw))
      return 1;
    else
      return 2;
  }
}
