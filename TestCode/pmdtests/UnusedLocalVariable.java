package pmdtests;

public class UnusedLocalVariable {
  int i;

  public void doSomething() {
    int i = 5;
    int j = 0;
    i++;
    j++;
    System.out.print(i);
  }
}
