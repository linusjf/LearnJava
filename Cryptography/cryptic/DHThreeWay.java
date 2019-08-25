package cryptic;

import static cryptic.DHHelper.*;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

/*
 * This program executes the Diffie-Hellman key agreement protocol between
 * 3 parties: Alice, Bob, and Carol using a shared 2048-bit DH parameter.
 */
public final class DHThreeWay {
  private DHThreeWay() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String... argv) {
    try {
      // Alice creates her own DH key pair with 2048-bit key size
      System.out.println("ALICE: Generate DH keypair ...");
      KeyPairGenerator aliceKpairGen = KeyPairGenerator.getInstance("DH");
      aliceKpairGen.initialize(2048);
      KeyPair aliceKpair = aliceKpairGen.generateKeyPair();
      // This DH parameters can also be constructed by creating a
      // DHParameterSpec object using agreed-upon values
      DHParameterSpec dhParamShared =
          ((DHPublicKey)aliceKpair.getPublic()).getParams();
      // Bob creates his own DH key pair using the same params
      System.out.println("BOB: Generate DH keypair ...");
      KeyPairGenerator bobKpairGen = KeyPairGenerator.getInstance("DH");
      bobKpairGen.initialize(dhParamShared);
      final KeyPair bobKpair = bobKpairGen.generateKeyPair();
      // Carol creates her own DH key pair using the same params
      System.out.println("CAROL: Generate DH keypair ...");
      KeyPairGenerator carolKpairGen = KeyPairGenerator.getInstance("DH");
      carolKpairGen.initialize(dhParamShared);
      final KeyPair carolKpair = carolKpairGen.generateKeyPair();
      // Alice initialize
      System.out.println("ALICE: Initialize ...");
      KeyAgreement aliceKeyAgree = KeyAgreement.getInstance("DH");
      aliceKeyAgree.init(aliceKpair.getPrivate());
      // Bob initialize
      System.out.println("BOB: Initialize ...");
      KeyAgreement bobKeyAgree = KeyAgreement.getInstance("DH");
      bobKeyAgree.init(bobKpair.getPrivate());
      // Carol initialize
      System.out.println("CAROL: Initialize ...");
      KeyAgreement carolKeyAgree = KeyAgreement.getInstance("DH");
      carolKeyAgree.init(carolKpair.getPrivate());
      // Alice uses Carol's public key
      Key ac = aliceKeyAgree.doPhase(carolKpair.getPublic(), false);
      // Bob uses Alice's public key
      Key ba = bobKeyAgree.doPhase(aliceKpair.getPublic(), false);
      // Carol uses Bob's public key
      Key cb = carolKeyAgree.doPhase(bobKpair.getPublic(), false);
      // Alice uses Carol's result from above
      aliceKeyAgree.doPhase(cb, true);
      // Bob uses Alice's result from above
      bobKeyAgree.doPhase(ac, true);
      // Carol uses Bob's result from above
      carolKeyAgree.doPhase(ba, true);
      // Alice, Bob and Carol compute their secrets
      byte[] aliceSharedSecret = aliceKeyAgree.generateSecret();
      System.out.println("Alice secret: " + toHexString(aliceSharedSecret));
      System.out.println("Alice secret: " + toBase64String(aliceSharedSecret));
      byte[] bobSharedSecret = bobKeyAgree.generateSecret();
      System.out.println("Bob secret: " + toHexString(bobSharedSecret));
      System.out.println("Bob secret: " + toBase64String(bobSharedSecret));
      byte[] carolSharedSecret = carolKeyAgree.generateSecret();
      System.out.println("Carol secret: " + toHexString(carolSharedSecret));
      System.out.println("Carol secret: " + toBase64String(carolSharedSecret));
      // Compare Alice and Bob
      if (!java.util.Arrays.equals(aliceSharedSecret, bobSharedSecret))
        throw new GeneralSecurityException("Alice and Bob differ");
      System.out.println("Alice and Bob are the same");
      // Compare Bob and Carol
      if (!java.util.Arrays.equals(bobSharedSecret, carolSharedSecret))
        throw new GeneralSecurityException("Bob and Carol differ");
      System.out.println("Bob and Carol are the same");
    } catch (GeneralSecurityException e) {
      System.err.println(e);
    }
  }

  /*
   * Converts a byte array to Base64 string
   */
  private static String toBase64String(byte[] block) {
    return Base64.getEncoder().encodeToString(block);
  }
}
