package scan;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public final class ScannerDemo1 {
  private static Charset charset = StandardCharsets.UTF_8;

  private ScannerDemo1() {
    throw new IllegalStateException("Private constructor");
  }

  @SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
  public static void main(String[] args) {
    // Declare the object and initialize with
    // predefined standard input object
    System.out.println("Enter name,gender,age,mobile,CGPA");

    Scanner sc = new Scanner(System.in, charset.name());

    // String input
    String name = sc.nextLine();

    // Character input
    char gender = sc.next().charAt(0);

    // Numerical data input
    // byte, short and float can be read
    // using similar-named functions.
    int age = sc.nextInt();

    long mobileNo = sc.nextLong();

    double cgpa = sc.nextDouble();

    // Print the values to check if the input was correctly obtained.
    System.out.println("Name: " + name);

    System.out.println("Gender: " + gender);

    System.out.println("Age: " + age);

    System.out.println("Mobile Number: " + mobileNo);

    System.out.println("CGPA: " + cgpa);
  }
}
