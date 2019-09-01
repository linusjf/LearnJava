package io;

import java.io.File;
import java.util.Scanner;

public enum FileMethods {
  ;

  public static void main(String[] args) {
    String filename;
    try (Scanner input = new Scanner(System.in)) {
      System.out.print("Enter name of file/directory ");
      System.out.print("or press <Enter> to quit: ");
      filename = input.nextLine();
      while (!"".equals(filename)) {  // Not <Enter> key.
        File fileDir = new File(filename);
        if (!fileDir.exists()) {
          System.out.println(filename + " does not exist!");
          break;  // Get out of loop.
        }
        System.out.print(filename + " is a ");
        if (fileDir.isFile())
          System.out.println("file.");
        else
          System.out.println("directory.");
        System.out.print("It is ");
        if (!fileDir.canRead())
          System.out.print("not ");
        System.out.println("readable.");
        System.out.print("It is ");
        if (!fileDir.canWrite())
          System.out.print("not ");
        System.out.println("writeable.");
        if (fileDir.isDirectory()) {
          System.out.println("Contents:");
          String[] fileList = fileDir.list();
          // Now display list of files in
          // directory…
          for (String fileName: fileList)
            System.out.println(" " + fileName);
        } else {
          System.out.print("Size of file: ");
          System.out.println(fileDir.length() + " bytes.");
        }
        System.out.print("\n\nEnter name of next file/directory ");
        System.out.print("or press <Enter> to quit: ");
        filename = input.nextLine();
      }
    }
  }
}
