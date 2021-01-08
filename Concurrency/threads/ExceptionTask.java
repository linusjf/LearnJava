package threads;

@SuppressWarnings("PMD.SystemPrintln")
public enum ExceptionTask {
  ;

  public static void main(String[] args) {
    Task task = new Task();
    Thread thread = new Thread(task);
    thread.setUncaughtExceptionHandler(new ExceptionHandler());
    thread.start();
  }

  @SuppressWarnings("PMD.ShortClassName")
  static class Task implements Runnable {
    @Override
    public void run() {
      int numero = Integer.parseInt("TTT");
      System.out.println(numero);
    }
  }
}
