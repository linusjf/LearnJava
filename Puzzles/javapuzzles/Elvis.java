package javapuzzles;

import java.util.Calendar;

public final class Elvis {
  public static final Elvis INSTANCE = new Elvis();
  private final int beltSize;
  private static final int CURRENT_YEAR =
      Calendar.getInstance().get(Calendar.YEAR);

  private Elvis() {
    beltSize = CURRENT_YEAR - 1930;
  }
  
  public int getBeltSize() {
    return beltSize;
  }
  
  public static void main(String[] args) {
    System.out.println("Elvis wears a size " + INSTANCE.getBeltSize() + " belt.");
  }
}
