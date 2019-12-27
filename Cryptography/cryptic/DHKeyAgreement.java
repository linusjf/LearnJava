package cryptic;

import static cryptic.DHHelper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class DHKeyAgreement {
  private static KeyAgreement aliceKeyAgree;
  private static KeyPair aliceKpair;

  private DHKeyAgreement() {
    throw new IllegalStateException("Private coonstructor");
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void initAliceKey() throws GeneralSecurityException {
    /*
     * Alice creates her own DH key pair with 2048-bit key size
     */
    System.out.println("ALICE: Generate DH keypair ...");
    KeyPairGenerator aliceKpairGen = KeyPairGenerator.getInstance("DH");
    aliceKpairGen.initialize(2048);
    aliceKpair = aliceKpairGen.generateKeyPair();

    // Alice creates and initializes her DH KeyAgreement object
    System.out.println("ALICE: Initialization ...");
    aliceKeyAgree = KeyAgreement.getInstance("DH");
    aliceKeyAgree.init(aliceKpair.getPrivate());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static byte[] getAlicePublicEncodedKey() throws GeneralSecurityException {
    // Alice encodes her public key, and sends it over to Bob.
    return aliceKpair.getPublic().getEncoded();
  }

  @SuppressWarnings({"checkstyle:illegaltoken", "PMD.ExcessiveMethodLength","PMD.LawOfDemeter"})
  public static void main(String... argv) {
    try {
      initAliceKey();
      byte[] alicePubKeyEnc = getAlicePublicEncodedKey();

      /*
       * Let's turn over to Bob. Bob has received Alice's public key
       * in encoded format.
       * He instantiates a DH public key from the encoded key material.
       */
      KeyFactory bobKeyFac = KeyFactory.getInstance("DH");
      X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(alicePubKeyEnc);

      PublicKey alicePubKey = bobKeyFac.generatePublic(x509KeySpec);

      /*
       * Bob gets the DH parameters associated with Alice's public key.
       * He must use the same parameters when he generates his own key
       * pair.
       */
      DHParameterSpec dhParamFromAlicePubKey = ((DHPublicKey) alicePubKey).getParams();

      // Bob creates his own DH key pair
      System.out.println("BOB: Generate DH keypair ...");
      KeyPairGenerator bobKpairGen = KeyPairGenerator.getInstance("DH");
      bobKpairGen.initialize(dhParamFromAlicePubKey);
      KeyPair bobKpair = bobKpairGen.generateKeyPair();

      // Bob creates and initializes his DH KeyAgreement object
      System.out.println("BOB: Initialization ...");
      KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
      bobKeyAgree.init(bobKpair.getPrivate());

      // Bob encodes his public key, and sends it over to Alice.
      byte[] bobPubKeyEnc = bobKpair.getPublic().getEncoded();

      /*
       * Alice uses Bob's public key for the first (and only) phase
       * of her version of the DH
       * protocol.
       * Before she can do so, she has to instantiate a DH public key
       * from Bob's encoded key material.
       */
      KeyFactory aliceKeyFac = KeyFactory.getInstance("DH");
      x509KeySpec = new X509EncodedKeySpec(bobPubKeyEnc);
      PublicKey bobPubKey = aliceKeyFac.generatePublic(x509KeySpec);
      System.out.println("ALICE: Execute PHASE1 ...");
      aliceKeyAgree.doPhase(bobPubKey, true);

      /*
       * Bob uses Alice's public key for the first (and only) phase
       * of his version of the DH
       * protocol.
       */
      System.out.println("BOB: Execute PHASE1 ...");
      bobKeyAgree.doPhase(alicePubKey, true);

      /*
       * At this stage, both Alice and Bob have completed the DH key
       * agreement protocol.
       * Both generate the (same) shared secret.
       */
      byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();
      int aliceLen = aliceSharedSecret.length;
      byte[] bobSharedSecret = new byte[aliceLen];

      // provide output buffer of required size
      int bobLen = bobKeyAgree.generateSecret(bobSharedSecret, 0);
      assert aliceLen == bobLen;
      System.out.println("Alice secret: " + toHexString(aliceSharedSecret));
      System.out.println("Bob secret: " + toHexString(bobSharedSecret));
      if (!java.util.Arrays.equals(aliceSharedSecret, bobSharedSecret))
        throw new GeneralSecurityException("Shared secrets differ");
      System.out.println("Shared secrets are the same");

      /*
       * Now let's create a SecretKey object using the shared secret
       * and use it for encryption. First, we generate SecretKeys for the
       * "AES" algorithm (based on the raw shared secret data) and
       * Then we use AES in CBC mode, which requires an initialization
       * vector (IV) parameter. Note that you have to use the same IV
       * for encryption and decryption: If you use a different IV for
       * decryption than you used for encryption, decryption will fail.
       *
       * If you do not specify an IV when you initialize the Cipher
       * object for encryption, the underlying implementation will generate
       * a random one, which you have to retrieve using the
       * javax.crypto.Cipher.getParameters() method, which returns an
       * instance of java.security.AlgorithmParameters. You need to transfer
       * the contents of that object (e.g., in encoded format, obtained via
       * the AlgorithmParameters.getEncoded() method) to the party who will
       * do the decryption. When initializing the Cipher for decryption,
       * the (reinstantiated) AlgorithmParameters object must be explicitly
       * passed to the Cipher.init() method.
       */
      System.out.println("Use shared secret as SecretKey object ...");
      SecretKeySpec bobAesKey = new SecretKeySpec(bobSharedSecret, 0, 16, "AES");
      SecretKeySpec aliceAesKey = new SecretKeySpec(aliceSharedSecret, 0, 16, "AES");

      /*
       * Bob encrypts, using AES in CBC mode
       */
      Cipher bobCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      bobCipher.init(Cipher.ENCRYPT_MODE, bobAesKey);
      byte[] cleartext = "This is just an example".getBytes(StandardCharsets.UTF_8);
      byte[] ciphertext = bobCipher.doFinal(cleartext);

      // Retrieve the parameter that was used, and transfer it to Alice in
      // encoded format
      byte[] encodedParams = bobCipher.getParameters().getEncoded();

      /*
       * Alice decrypts, using AES in CBC mode
       */
      // Instantiate AlgorithmParameters object from parameter encoding
      // obtained from Bob
      AlgorithmParameters aesParams = AlgorithmParameters.getInstance("AES");
      aesParams.init(encodedParams);
      Cipher aliceCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      aliceCipher.init(Cipher.DECRYPT_MODE, aliceAesKey, aesParams);
      byte[] recovered = aliceCipher.doFinal(ciphertext);
      if (!java.util.Arrays.equals(cleartext, recovered))
        throw new GeneralSecurityException("AES in CBC mode recovered text is "
            + "different from cleartext");
      System.out.println("AES in CBC mode recovered text is same as cleartext");
    } catch (GeneralSecurityException | IOException exc) {
      System.err.println(exc);
    }
  }
}
