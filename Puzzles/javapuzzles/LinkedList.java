package javapuzzles;

class LinkedList<E> {
  private Node<E> head;

  @SuppressWarnings("PMD.ShortClassName")
  private static class Node<T> {
    T value;
    Node<T> next;

    Node(T value, Node<T> next) {
      this.value = value;
      this.next = next;
    }
  }

  public void add(E e) {
    head = new Node<>(e, head);
  }

  public void dump() {
    for (Node<E> n = head; n != null; n = n.next)
      System.out.print(n.value + " ");
  }

  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<>();
    list.add("world");
    list.add("Hello");
    list.dump();
  }
}
