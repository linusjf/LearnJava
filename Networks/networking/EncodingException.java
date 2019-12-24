package networking;

public final class EncodingException extends RuntimeException {
  public static final long serialVersionUID = 1;

  public EncodingException(String message, Exception e) {
    super(message, e);
  }

  public EncodingException(String message) {
    super(message);
  }
}
