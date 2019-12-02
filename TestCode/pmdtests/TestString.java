package pmdtests;

@SuppressWarnings("PMD.UnusedPrivateMethod")
public class TestString {

  String a;
  String c;

  private void startProcess() {
    this.createEvent(a, new Object().toString(), c);
  }

  // method invoked
  private void createEvent(String a, String b, String c) {
    // do some logic
    System.out.printf("creating event... with %s %s %s%n", a, b, c);
  }
}
