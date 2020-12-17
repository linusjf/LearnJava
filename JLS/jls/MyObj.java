package jls;

@SuppressWarnings("all")
class TestExc extends Exception {}

@SuppressWarnings("all")
class TestExc1 extends Exception {}

@SuppressWarnings("all")
class TestExc2 extends Exception {}

@SuppressWarnings("all")
class MyObj {
  // An instance variable
  int i;
  private long index = 0;

  Object create() {
    return new Object();
  }

  MyObj example() {
    MyObj o = new MyObj();
    return silly(o);
  }

  MyObj silly(MyObj o) {
    if (o != null)
      return o;
    else
      return o;
  }

  void setIt(int value) {
    i = value;
  }

  int getIt() {
    return i;
  }

  void createBuffer() {
    int[] buffer;
    int bufsz = 100;
    int value = 12;
    buffer = new int[bufsz];
    buffer[10] = value;
    value = buffer[11];
  }

  void createThreadArray() {
    Thread[] threads;
    int count = 10;
    threads = new Thread[count];
    threads[0] = new Thread();
  }

  int[][][] create3DArray() {
    int[][][] grid = new int[10][5][];
    return grid;
  }

  void tryItOut() throws TestExc, TestExc1, TestExc2 {
  }

  void wrapItUp() {
  }

  public long nextIndex() {
    return index++;
  }

  void cantBeZero(int i) throws TestExc {
    if (i == 0) {
      throw new TestExc();
    }
  }

  void catchOne() throws TestExc1, TestExc2 {
    try {
      tryItOut();
    } catch (TestExc e) {
      handleExc(e);
    }
  }

  void catchTwo() throws TestExc {
    try {
      tryItOut();
    } catch (TestExc1 e) {
      handleExc(e);
    } catch (TestExc2 e) {
      handleExc(e);
    }
  }

  void nestedCatch() throws TestExc {
    try {
      try {
        tryItOut();
      } catch (TestExc1 e) {
        handleExc1(e);
      }
    } catch (TestExc2 e) {
      handleExc2(e);
    }
  }

  void handleExc(Exception e) {
    System.err.println(e);
  }

  void handleExc1(Exception e) {
    System.err.println(e);
  }

  void handleExc2(Exception e) {
    System.err.println(e);
  }

  void tryFinally() throws TestExc, TestExc1, TestExc2 {
    try {
      tryItOut();
    } finally {
      wrapItUp();
    }
  }

  void tryCatchFinally() throws TestExc, TestExc1, TestExc2 {
    try {
      tryItOut();
    } catch (TestExc e) {
      handleExc(e);
    } finally {
      wrapItUp();
    }
  }
}
