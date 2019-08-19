package cryptic;

import java.util.Random;

public enum Exponential {
  ;

  static int fastExp1(int base, int exponent) {
    String binString = Integer.toBinaryString(exponent);
    char[] binary = binString.toCharArray();
    return exp(base, binary, binary.length -1);
  }

  static int exp(int x, char[] binary, int k) {
    // Y = Y[k] Y[k-1] ... Y[1] Y[0] (in binary)
    int y = 0, z = 1;
    for (int i = 0; i <= k; i++) {
      y = 2 * y;
      z = z * z;
      if (binary[i] == '1') {
        y++;
        z = z * x;
      }
    }
    return z;
  }

  static int expSecond(int X, int Y) {
    int x = X, y = Y, z = 1;
    while (y > 0) {
      while (y % 2 == 0) {
        x = x * x;
        y = y / 2;
      }
      z = z * x;
      y = y - 1;
    }
    return z;
  }

  public static void main(String... args) {
  Random random = new Random();
  int base = random.nextInt(5);
  int exponent = random.nextInt(20);
  
  System.out.printf("Base: %d Exponent %d%n",base,exponent);

  long start = System.nanoTime();
  int value = (int)Math.pow((double)base,(double)exponent);
  long end = System.nanoTime();

  System.out.printf("Time taken: %d%n",end-start);
  System.out.printf("Solution: %d%n",value);
  
  start = System.nanoTime();
  value = fastExp1(base,exponent);
  end = System.nanoTime();

  System.out.printf("Time taken: %d%n",end-start);
  System.out.printf("Solution: %d%n",value);

  start = System.nanoTime();
  value = expSecond(base,exponent);
  end = System.nanoTime();

  System.out.printf("Time taken: %d%n",end-start);
  System.out.printf("Solution: %d%n",value);
  }
}
