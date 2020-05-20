package command;

import java.util.Locale;
import javax.crypto.Cipher;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@SuppressWarnings("PMD.ShortClassName")
public class App {
  @Parameters
  Locale locale;

  @Option(names = "-a")
  Cipher cipher;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String... argv) {
    App app = new App();
    CommandLine commandLine =
        new CommandLine(app)
            .registerConverter(
                Locale.class,
                s -> new Locale.Builder().setLanguageTag(s).build())
            .registerConverter(Cipher.class, s -> Cipher.getInstance(s));

    commandLine.parseArgs("-a", "AES/CBC/NoPadding", "en-GB");

    // flagged as static property access
    assert app.locale.toLanguageTag().equals("en-GB");

    // flagged as static property access
    assert app.cipher.getAlgorithm().equals("AES/CBC/NoPadding");
    System.out.println("No assertion errors");
  }
}
