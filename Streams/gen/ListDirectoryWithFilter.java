package gen;

// List files that end with ".java"
import java.io.File;
import java.util.Optional;

@SuppressWarnings("PMD.SystemPrintln")
public final class ListDirectoryWithFilter {
  private ListDirectoryWithFilter() {
    throw new AssertionError("Private constructor invoked for class: "
                             + getClass());
  }

  public static void main(String[] args) {
    File dir = new File(".");
    listRecursiveFiles(dir);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void listRecursiveFiles(File dir) {
    if (dir.isDirectory()) {
      listFiles(dir);
      File[] items = dir.listFiles();
      Optional<?> files = Optional.ofNullable(items);
      files.ifPresent(obj -> {
        File[] objs = (File[])obj;
        for (File item: objs) {
          if (item.isDirectory())
            listRecursiveFiles(item);
          // Recursive call
        }
      });
    }
  }

  public static void listFiles(File dir) {
    if (dir.isDirectory()) {
      // List only files that meet the filtering criteria
      //  programmed in accept() method of FilenameFilter.
      File[] files = dir.listFiles((d, file) -> file.endsWith(".java"));
      if (files != null) {
        for (File file: files)
          System.out.println(file);
      }
    }
  }
}
