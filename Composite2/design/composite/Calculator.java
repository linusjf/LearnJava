package design.composite;

import java.math.BigInteger;

public enum Calculator implements ICalc {
  FACTORIAL {
    @Override
    public String calculate(String value) {
      String answer = "NA";
      try {
        final long longValue = Long.parseLong(value);
        BigInteger factorialValue = BigInteger.valueOf(1);
        for (long i = 1; i <= longValue; i++)
          factorialValue = factorialValue.multiply(BigInteger.valueOf(i));
        answer = factorialValue.toString();
      } catch (NumberFormatException exp) {
        System.out.println("Can't calculate factorial of " + value);
      }
      return answer;
    }
  },

  PALINDROME {
    @Override
    @SuppressWarnings("PMD.InefficientEmptyStringCheck")
    public String calculate(String value) {
      String answer = "false";
      if (value != null && !value.trim().isEmpty()) {
        final String reverse = new StringBuilder(value).reverse().toString();
        answer = Boolean.toString(reverse.equals(value));
      }
      return answer;
    }
  },

  ARMSTRONG {
    @Override
    @SuppressWarnings("checkstyle:magicnumber")
    public String calculate(String value) {
      String answer = "false";
      try {
        final long longValue = Long.parseLong(value);
        long number = longValue;
        long armstrongValue = 0;
        long temp;
        while (number != 0) {
          temp = number % 10;
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
