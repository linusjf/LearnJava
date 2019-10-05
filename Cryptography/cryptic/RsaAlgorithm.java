package cryptic;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RsaAlgorithm {
  private static final int MAX_LENGTH = 1024;
  private BigInteger p;
  private BigInteger q;
  private BigInteger n;
  private BigInteger phi;
  private BigInteger e;
  private BigInteger d;
  private Random random;

  public RsaAlgorithm() {
    random = new Random();
    p = BigInteger.probablePrime(MAX_LENGTH, random);
    q = BigInteger.probablePrime(MAX_LENGTH, random);
    n = p.multiply(q);
    phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    e = BigInteger.probablePrime(MAX_LENGTH / 2, random);
    while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
      e.add(BigInteger.ONE);
    }
    d = e.modInverse(phi);
  }

  public RsaAlgorithm(BigInteger e, BigInteger d, BigInteger n) {
    this.e = e;
    this.d = d;
    this.n = n;
  }

  public static void main(String[] arguments) throws IOException {
    RsaAlgorithm rsa = new RsaAlgorithm();
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    String inputString;
    System.out.println("Enter message you wish to send.");
    inputString = input.readLine();
    System.out.println("Encrypting the message: " + inputString);
    System.out.println("The message in bytes is:: "
                       + bToS(inputString.getBytes()));
    // encryption
    byte[] cipher = rsa.encryptMessage(inputString.getBytes());
    // decryption
    byte[] plain = rsa.decryptMessage(cipher);
    System.out.println("Decrypting Bytes: " + bToS(plain));
    System.out.println("Plain message is: " + new String(plain));
  }

  private static String bToS(byte[] cipher) {
    String temp = "";
    for (byte b: cipher) {
      temp += Byte.toString(b);
    }
    return temp;
  }

  // Encrypting the message
  public byte[] encryptMessage(byte[] message) {
    return (new BigInteger(message)).modPow(e, n).toByteArray();
  }

  // Decrypting the message
  public byte[] decryptMessage(byte[] message) {
    return (new BigInteger(message)).modPow(d, n).toByteArray();
  }
}
