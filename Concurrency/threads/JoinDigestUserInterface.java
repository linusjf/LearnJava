package threads;

import java.util.Base64;

@SuppressWarnings("PMD.SystemPrintln")
public final class JoinDigestUserInterface {
  private JoinDigestUserInterface() {
    throw new IllegalStateException("Private constructor invoked for class: "
                                    + getClass());
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void startThread(int index,
                                  ReturnDigest[] digestThreads,
                                  String arg) {
    digestThreads[index] = new ReturnDigest(arg);
    digestThreads[index].start();
  }

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  public static void main(String[] args) {
    ReturnDigest[] digestThreads = new ReturnDigest[args.length];
    for (int i = 0; i < args.length; i++)
      startThread(i, digestThreads, args[i]);
    for (int i = 0; i < args.length; i++) {
      try {
        digestThreads[i].join();

        // Now print the result
        printResult(args[i], digestThreads[i]);
      } catch (InterruptedException ex) {
        System.err.println("Thread Interrupted before completion");
      }
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void printResult(String arg, ReturnDigest digestThread) {
    StringBuilder result = new StringBuilder(arg);
    result.append(": ");
    byte[] digest = digestThread.getDigest();
    result.append(Base64.getEncoder().encodeToString(digest));
    System.out.println(result);
  }
}
