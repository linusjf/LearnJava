package factorymethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public enum BillGenerator {
  ;
  /**
   * Main method.
   *
   * @param args command line arguments
   */
  @SuppressWarnings("PMD.DoNotCallSystemExit")
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Specify path to file as argument.");
      System.exit(1);
    }

    PlanFactory planFactory = new PlanFactory();

    try (BufferedReader br = new BufferedReader(new InputStreamReader(
             Files.newInputStream(Paths.get(args[0])), StandardCharsets.UTF_8.name()))) {
      Optional<String> planName = Optional.ofNullable(br.readLine());
      planName.ifPresent(plan -> {
        try {
          int units = Integer.parseInt(br.readLine());

          Plan p = planFactory.getPlan(plan);

          System.out.print("Bill amount for " + plan + " of  " + units + " units is: ");
          p.allotRate();
          p.calculateBill(units);
        } catch (IOException | NumberFormatException e) {
          System.out.println("Input error: " + e.getMessage());
        }
      });
    } catch (IOException e) {
      System.out.println("Input error: " + e.getMessage());
    }
  }
}
