package cryptic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@SuppressWarnings({"PMD.BeanMembersShouldSerialize", "PMD.ImmutableField"})
public class RsaAlgorithm {
  private static final int MAX_LENGTH = 1024;
  private final BigInteger n;
  private BigInteger e;
  private final BigInteger d;

  public RsaAlgorithm() {
    Random random = new Random();
    BigInteger p = BigInteger.probablePrime(MAX_LENGTH, random);
    BigInteger q = BigInteger.probablePrime(MAX_LENGTH, random);
    n = p.multiply(q);
    BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    e = BigInteger.probablePrime(MAX_LENGTH / 2, random);
    while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
      e = e.add(BigInteger.ONE);
    }
    d = e.modInverse(phi);
  }

  public RsaAlgorithm(BigInteger e, BigInteger d, BigInteger n) {
    this.e = e;
    this.d = d;
    this.n = n;
  }

  public static void main(String[] arguments) throws IOException {
    BufferedReader input =
        new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8.name()));
    String inputString;
    System.out.println("Enter message you wish to send.");
    inputString = input.readLine();
    if (inputString == null) return;
    else {
      System.out.println("Encrypting the message: " + inputString);
      System.out.println(
          "The message in bytes is:: "
              + bytesToString(inputString.getBytes(StandardCharsets.UTF_8)));
      RsaAlgorithm rsa = new RsaAlgorithm();

      // encryption
      byte[] cipher = rsa.encryptMessage(inputString.getBytes(StandardCharsets.UTF_8));

      // decryption
      byte[] plain = rsa.decryptMessage(cipher);
      System.out.println("Decrypting Bytes: " + bytesToString(plain));

      System.out.println("Plain message is: " + new String(plain, StandardCharsets.UTF_8));
    }
  }

  private static String bytesToString(byte[] cipher) {
    StringBuilder temp = new StringBuilder();
    for (byte b : cipher) temp.append(Byte.toString(b));
    return temp.toString();
  }

  // Encrypting the message
  public byte[] encryptMessage(byte[] message) {
    return new BigInteger(message).modPow(e, n).toByteArray();
  }

  // Decrypting the message
  public byte[] decryptMessage(byte[] message) {
    return new BigInteger(message).modPow(d, n).toByteArray();
  }
}
