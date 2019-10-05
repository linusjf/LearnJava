package javapuzzles;

public enum UnwelcomeGuest {
  ;
  public static final long GUEST_USER_ID = -1;
  //  private static final long USER_ID;
  private static final long USER_ID = getUserIdOrGuest();

  /**
   * Commented out. static { try { USER_ID = getUserIdFromEnvironment(); } catch
   * (IdUnavailableException e) { USER_ID = GUEST_USER_ID;
   * System.out.println("Logging in as guest"); } }*
   */
  private static long getUserIdOrGuest() {
    try {
      return getUserIdFromEnvironment();
    } catch (IdUnavailableException e) {
      System.out.println("Logging in as guest");
      return GUEST_USER_ID;
    }
  }

  private static long getUserIdFromEnvironment() throws IdUnavailableException {
    throw new IdUnavailableException("ID Unavailable");
    // Simulate an error
  }

  public static void main(String[] args) {
    System.out.println("User ID: " + USER_ID);
  }
}

@SuppressWarnings("checkstyle:onetoplevelclass")
class IdUnavailableException extends Exception {
  private static final long serialVersionUID = 1L;

  IdUnavailableException(String msg) {
    super(msg);
  }
}
