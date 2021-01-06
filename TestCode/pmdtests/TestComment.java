package pmdtests;

public final class TestComment {
  @SuppressWarnings("PMD.CommentSize")
  private TestComment() {
    /*
     * Writing and maintaining complicated software is a difficult task,
     * and every programmer inadvertently makes mistakes.
     * Usually these are minor typographical errors that will be caught at
     * compile time, but others may remain undetected until the system is in production.
     * In the most extreme cases, the bug will cause system failure, as was the case in
     * the massive blackout in the Northeast last summer.
     * In addition to the consequences of system malfunction,
     * software defects have a substantial financial cost.
     * A 2003 report from the National Institute of Standards and Technology (NIST)
     * calculated the total annual cost of bugs at nearly $60 billion.
     * The report also confirmed what most developers already know:
     * the difficulty and cost of fixing a problem grows dramatically throughout the development cycle.
     * Although the report concluded that it is not practical to locate and remove all defects
     * from an application, it stated that nearly 40% could be eliminated by an improved inspection and testing
     * process.
     *
     * Two common methods for improving software quality are code reviews and automated
     * testing. The goal of both is to detect mistakes, but the distinction between them
     * is how this is achieved. Automated testing attempts to expose problems by executing
     * the code, while reviews rely on "another set of eyes" to verify that it's correct.
     * Problems can arise when we get too involved with the code to be objective or to consider
     * unusual paths of execution. Certainly every programmer has at one time
     * spent hours trying to track down a problem only to have someone else spot it
     * immediately.
     */
    throw new UnsupportedOperationException(
        "This is a utility class and cannot be instantiated");
  }

  public static void main(String... args) {
    // empty main
  }
}
