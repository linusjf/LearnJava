package javapuzzles;

public enum Addition {
  ;

  public static void main(String... args) {
    int a = 45;
    double b = 3.141_598_7;
    long c = 70_000_000;
    a += b;
    System.out.println(a);
    a += c;
    System.out.println(a);

    // Errors printed out
    // d  = d + b;
    // d = d + c;
    int d = 0;
    d = (int)(d + b);
    System.out.println(d);
    d = (int)(d + c);
    System.out.println(d);

    var avar = 45;
    avar += b;
    System.out.println(avar);
    avar += c;
    System.out.println(avar);
    var dvar = 0;
    dvar = (int)(dvar + b);
    System.out.println(dvar);
    dvar = (int)(dvar + c);
    System.out.println(dvar);
  }
}
