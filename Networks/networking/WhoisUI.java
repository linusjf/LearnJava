package networking;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

public class WhoisUI {
  private String searchFor;
  private String searchAt;
  private boolean exactMatch;
  private String whois;
  private String searchIn;
  private static final String UTF_8 = StandardCharsets.UTF_8.name();

  public void inputWhoisFields(String... args) {
    Scanner scanner = new Scanner(System.in, UTF_8);
    inputWhois(scanner);
    inputExactMatch(scanner);
    inputSearchFor(scanner);
    inputSearchIn(scanner);
    inputSearchAt(scanner);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private void inputWhois(Scanner scanner) {
    System.out.println("Enter entity name for whois record: ");
    if (scanner.hasNext())
      whois = scanner.next().trim();
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private void inputSearchFor(Scanner scanner) {
    System.out.println("Enter entity type to search for: ");
    String pattern = Whois.SearchFor.getRegexPatternAsString();
    System.out.println(pattern);
    do {
      if (scanner.hasNext())
        searchFor = scanner.next();
      else
        scanner.next();
    } while (!searchFor.matches(pattern));
    System.out.println(searchFor);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private void inputSearchAt(Scanner scanner) {
    System.out.println("Enter name of registry server to search at: ");
    if (scanner.hasNext())
      searchAt = scanner.next().trim();
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private void inputSearchIn(Scanner scanner) {
    System.out.println("Enter records to search in: ");
    String pattern = Whois.SearchIn.getRegexPatternAsString();
    System.out.println(pattern);
    do {
      if (scanner.hasNext())
        searchIn = scanner.next();
      else
        scanner.next();
    } while (!searchIn.matches(pattern));
    System.out.println(searchIn);
  }

  private void inputExactMatch(Scanner scanner) {
    System.out.println("Is search to be an exact match?");
    if (scanner.hasNextBoolean())
      exactMatch = scanner.nextBoolean();
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try {
      WhoisUI ui = new WhoisUI();
      ui.inputWhoisFields(args);
      Whois server = new Whois(ui.searchAt);
      if (ui.exactMatch)
        server.lookUpNamesExactMatch(ui.whois,
            Whois.SearchFor.valueOf(ui.searchFor.toUpperCase(Locale.getDefault())),
            Whois.SearchIn.valueOf(ui.searchIn.toUpperCase(Locale.getDefault())));
      else
        server.lookUpNames(ui.whois,
            Whois.SearchFor.valueOf(ui.searchFor.toUpperCase(Locale.getDefault())),
            Whois.SearchIn.valueOf(ui.searchIn.toUpperCase(Locale.getDefault())));
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
