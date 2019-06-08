package gen;

// List files that end with ".java"
import java.io.File;
import java.io.FilenameFilter;

public class ListDirectoryWithFilter {
  public static void main(String[] args) {
    File dir = new File(".");
    listRecursiveFiles(dir);
  }

  public static void listRecursiveFiles(File dir) {
    if (dir.isDirectory()) {
      listFiles(dir);
      File[] items = dir.listFiles();
      for (File item : items) {
        if (item.isDirectory())
          listRecursiveFiles(item); // Recursive call
      }
    }
  }

  public static void listFiles(File dir) {
    if (dir.isDirectory()) {
      // List only files that meet the filtering criteria
      //  programmed in accept() method of FilenameFilter.
      String[] files = dir.list(new FilenameFilter() {
        public boolean accept(File dir, String file) {
          return file.endsWith(".java");
        }
      }); // an anonymous inner class as FilenameFilter
      for (String file : files) System.out.println(file);
    }
  }
}
