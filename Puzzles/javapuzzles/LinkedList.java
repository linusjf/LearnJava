package javapuzzles;

class LinkedList<E> {
  private Node<E> head = null;

  private static class Node<T> {
    T value;
    Node<T> next;

    Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }
  }

  public void add(E e) {
    head = new Node<E>(e, head);
  }

  public void dump() {
    for (Node<E> n = head; n != null; n = n.next)
      System.out.print(n.value + " ");
  }

  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<String>();
    list.add("world");
    list.add("Hello");
    list.dump();
  }
}
