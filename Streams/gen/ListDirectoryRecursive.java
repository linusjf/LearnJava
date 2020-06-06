package gen;

// Recursively list the contents of a directory (Unix's "ls -r" command).
import java.io.File;
import java.util.Optional;

public final class ListDirectoryRecursive {
  private ListDirectoryRecursive() {
    throw new AssertionError("Private constructor invoked for class: " + getClass());
  }

  public static void main(String[] args) {
    File dir = new File(".");
    listRecursive(dir);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void listRecursive(File dir) {
    if (dir.isDirectory()) {
      File[] items = dir.listFiles();
      Optional<?> files = Optional.ofNullable(items);
      files.ifPresent(obj -> {
        File[] objs = (File[])obj;
        for (File item: objs) {
          System.out.println(item.getAbsoluteFile());
          if (item.isDirectory())
            listRecursive(item);
          // Recursive call
        }
      });
    }
  }
}
