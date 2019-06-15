package threads;

import javax.xml.bind.DatatypeConverter;

public final class JoinDigestUserInterface {
  private JoinDigestUserInterface() {
    throw new IllegalStateException("Private constructor");
  }

  public static void main(String[] args) {
    ReturnDigest[] digestThreads = new ReturnDigest[args.length];
    for (int i = 0; i < args.length; i++) {
      // Calculate the digest
      digestThreads[i] = new ReturnDigest(args[i]);
      digestThreads[i].start();
    }
    for (int i = 0; i < args.length; i++) {
      try {
        digestThreads[i].join();
        // Now print the result
        StringBuilder result = new StringBuilder(args[i]);
        result.append(": ");
        byte[] digest = digestThreads[i].getDigest();
        result.append(DatatypeConverter.printBase64Binary(digest));
        System.out.println(result);
      } catch (InterruptedException ex) {
        System.err.println("Thread Interrupted before completion");
      }
    }
  }
}
