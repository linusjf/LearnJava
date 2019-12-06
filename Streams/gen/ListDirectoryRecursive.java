package gen;

// Recursively list the contents of a directory (Unix's "ls -r" command).
import java.io.File;

public final class ListDirectoryRecursive {

  private ListDirectoryRecursive() {
    throw new AssertionError("Private constructor");
  }

  public static void main(String[] args) {
    File dir = new File(".");
    listRecursive(dir);
  }

  public static void listRecursive(File dir) {
    if (dir.isDirectory()) {
      File[] items = dir.listFiles();
      if (items != null) {
      for (File item: items) {
        System.out.println(item.getAbsoluteFile());
        if (item.isDirectory())
          listRecursive(item);
        // Recursive call
      }
    }
    }
  }
}
