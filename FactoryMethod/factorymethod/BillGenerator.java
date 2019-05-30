package factorymethod;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public enum BillGenerator {
  ;

  public static void main(String args[]) {
    if (args.length < 1) {
      System.out.println("Specify path to file as argument.");
      System.exit(1);
    }

    PlanFactory planFactory = new PlanFactory();

    try (BufferedReader br =
             new BufferedReader(new InputStreamReader(new FileInputStream(args[0])))) {
      String planName = br.readLine();
      int units = Integer.parseInt(br.readLine());

      Plan p = planFactory.getPlan(planName);
      // call getRate() method and calculateBill()method of DomesticPaln.

      System.out.print("Bill amount for " + planName + " of  " + units + " units is: ");
      p.getRate();
      p.calculateBill(units);
    } catch (IOException | NumberFormatException e) {
      System.out.println("Input error: " + e.getMessage());
    }
  }
}
