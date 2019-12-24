package command;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Option;

public class Login implements Callable<Integer> {
  @Option(
      names = {"-u", "--user"},
      description = "User name")
  String user;

  @Option(
      names = {"-p", "--password"},
      description = "Passphrase",
      interactive = true,
      arity = "0..1")
  char[] password;

  private final Base64.Encoder encoder = Base64.getEncoder();

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public Integer call() throws NoSuchAlgorithmException {
    byte[] bytes = new byte[password.length];
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = (byte) password[i];
    }

    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(bytes);

    System.out.printf("Hi %s, your password is hashed to %s.%n", user, encoder.encode(md.digest()));

    // null out the arrays when done
    Arrays.fill(bytes, (byte) 0);
    Arrays.fill(password, ' ');

    return 0;
  }

  public static void main(String... args) {
    new CommandLine(new Login()).execute(args);
  }
}
