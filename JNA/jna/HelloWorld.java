package jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/** Simple example of JNA interface mapping and usage. */
public final class HelloWorld {
  private HelloWorld() {
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String... args) {
    CLibrary.INSTANCE.printf("Hello, World\n");
    for (int i = 0; i < args.length;i++) {
      CLibrary.INSTANCE.printf("Argument %d: %s\n", i + 1, args[i]);
    }
  }

  // This is the standard, stable way of mapping, which supports extensive
  // customization and mapping of Java to native types.
  public interface CLibrary extends Library {
    CLibrary INSTANCE =
        Native.load(Platform.isWindows() ? "msvcrt" : "c", CLibrary.class);

    void printf(String format, Object... args);
  }
}
