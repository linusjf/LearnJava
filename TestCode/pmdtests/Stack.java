package pmdtests;

@SuppressWarnings("PMD.ShortClassName")
public class Stack {
  private Node first;
  // can't be made final
  private int n;

  public void insert(int x) {
    first = new Node(first, x);
  }

  public int size() {
    return n;
  }

  private final class Node {
    Node next;
    int x;

    private Node(Node next, int x) {
      this.next = next;
      this.x = x;
      // inner class updates instance variable in outer class
      n++;
    }
  }
}
