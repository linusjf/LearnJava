package reflection;

import java.lang.reflect.Method;

public class CarBean {
  private String name;
  private Object price;
  private Object year;

  public CarBean(String name, String year, String price) {
    this.name = name;
    this.price = price;
    this.year = year;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Object getPrice() {
    return price;
  }

  public void setPrice(Object price) {
    this.price = price;
  }

  public Object getYear() {
    return year;
  }

  public void setYear(Object year) {
    this.year = year;
  }

  public static void main(String... args) {
    try {
      CarBean car = new CarBean("vw touran", "2010", "12000");
      Method[] methods = car.getClass().getDeclaredMethods();

      // all getters, original values
      for (Method method : methods) {
        if (method.getName().startsWith("get")) {
          System.out.println(method.invoke(car));
        }
      }

      // setting values
      for (Method method : methods) {
        if (method.getName().startsWith("set")) {
          method.invoke(car, "destroyed");
        }
      }

      // get new values
      for (Method method : methods) {
        if (method.getName().startsWith("get")) {
          System.out.println(method.invoke(car));
        }
      }
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe);
    }
  }
}
