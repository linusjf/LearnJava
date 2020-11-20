package jls;

public final class SuperClassTest {

  private SuperClassTest() {
    throw new IllegalStateException("Private constructor.");
  }

  public static void main(String[] args) {
    Outer x = new Outer();
    ChildOfInner a = new ChildOfInner(x);
    ChildOfInner b = new ChildOfInner(x);
    System.out.println(b.getSecret());
    a.setSecret(6);
    System.out.println(b.getSecret());
  }
}

class Outer {
  int secret = 5;

  class Inner {
    int getSecret() {
      return secret;
    }

    void setSecret(int s) {
      secret = s;
    }
  }
}

class ChildOfInner extends Outer.Inner {
  ChildOfInner(Outer x) {
    x.super();
  }
}
