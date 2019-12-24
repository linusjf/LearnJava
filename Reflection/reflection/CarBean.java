package reflection;

import java.lang.reflect.Method;

@SuppressWarnings("PMD.DataClass")
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

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String... args) {
    try {
      CarBean car = new CarBean("vw touran", "2010", "12000");

      Method[] methods = CarBean.class.getDeclaredMethods();
      invokeGetters(methods, car);
      invokeSetters(methods, car, "destroyed");
      invokeGetters(methods, car);
    } catch (ReflectiveOperationException roe) {
      System.err.println(roe);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void invokeGetters(Method[] methods, CarBean car)
      throws ReflectiveOperationException {
    // all getters, original values
    for (Method method : methods) {
      if (method.getName().startsWith("get")) System.out.println(method.invoke(car));
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  private static void invokeSetters(Method[] methods, CarBean car, String value)
      throws ReflectiveOperationException {
    // setting values
    for (Method method : methods) {
      if (method.getName().startsWith("set")) method.invoke(car, value);
    }
  }
}
