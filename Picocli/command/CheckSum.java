package command;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@SuppressWarnings("PMD.ImmutableField")
@Command(
  description = "Prints the checksum (MD5 by default) of a file to STDOUT.",
  name = "checksum",
  mixinStandardHelpOptions = true,
  version = "checksum 3.0"
)
public class CheckSum implements Callable<Integer> {
  @Parameters(
    index = "0",
    description = "The file whose checksum to calculate."
  )
  private File file;

  @Option(
    names = { "-a", "--algorithm" },
    description = "MD5, SHA-1, SHA-256, ..."
  )
  private String algorithm = "SHA-1";

  private Base64.Encoder encoder = Base64.getEncoder();

  @SuppressWarnings("PMD.DoNotCallSystemExit")
  public static void main(String[] args) {
    int exitCode = new CommandLine(new CheckSum()).execute(args);
    System.exit(exitCode);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  @Override
  public Integer call() throws IOException, NoSuchAlgorithmException {
    byte[] fileContents = Files.readAllBytes(file.toPath());
    byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
    System.out.println(algorithm);
    System.out.println(encoder.encodeToString(digest));
    System.out.printf(
      "%0" + (digest.length * 2) + "x%n",
      new BigInteger(1, digest)
    );
    return 0;
  }
}
