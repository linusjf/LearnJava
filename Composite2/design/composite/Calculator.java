package design.composite;

import java.math.BigInteger;

public enum Calculator implements ICalc {
  FACTORIAL {
    @Override
    public String calculate(String value) {
      String answer = "NA";
      try {
        long longValue = Long.parseLong(value);
        BigInteger factorialValue = BigInteger.valueOf(1);
        for (long i = 1; i <= longValue; i++) {
          factorialValue = factorialValue.multiply(BigInteger.valueOf(i));
        }
        answer = factorialValue.toString();
      } catch (NumberFormatException exp) {
        System.out.println("Can't calculate factorial of " + value);
      }
      return answer;
    }
  },

  PALINDROME {
    @Override
    public String calculate(String value) {
      String answer = "false";
      if (value != null && !value.trim().isEmpty()) {
        String reverse = new StringBuilder(value).reverse().toString();
        answer = Boolean.toString(reverse.equals(value));
      }
      return answer;
    }
  },

  ARMSTRONG {
    @Override
    public String calculate(String value) {
      String answer = "false";
      try {
        long longValue = Long.parseLong(value);
        long number = longValue;
        long armstrongValue = 0;
        while (number != 0) {
          long temp = number % 10;
          armstrongValue = armstrongValue + temp * temp * temp;
          number /= 10;
        }
        answer = Boolean.toString(String.valueOf(armstrongValue).equals(value));
      } catch (NumberFormatException exp) {
        System.out.println("Can't calculate armstrong of " + value);
      }
      return answer;
    }
  };

}
