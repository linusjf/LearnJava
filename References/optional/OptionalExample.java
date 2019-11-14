package optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Simple example of how to use Optional from Java 8 to avoid NullPointerException. Optional is a
 * new addition in Java API and also allows you to set default values for any object.
 *
 * @author Javin Paul
 */
public enum OptionalExample {
  ;

  public static void main(String... args) {
    Address johnaddress = new Address("52/A, 22nd Street", "Mumbai", "India", 400_001);

    Person john = new Person("John", johnaddress, 874_731_232);

    Person mac = new Person("Mac", null, 333_299_911);
    Person gautam = new Person("Gautam", null, 533_299_911);

    List<Person> people = new ArrayList<>();
    people.add(john);
    people.add(mac);
    people.add(gautam);

    people.stream()
        .forEach(
            p -> {
              System.out.printf(
                  "%s from %s %n", p.getName(), p.getAddress().orElse(Address.EMPTY_ADDRESS));
            });
  }

  static class Person {
    private final String name;
    private final Optional<Address> address;
    private final int phone;

    Person(String name, Address address, int phone) {
      if (name == null) {
        throw new IllegalArgumentException("Null value for name is not permitted");
      }
      this.name = name;
      this.address = Optional.ofNullable(address);
      this.phone = phone;
    }

    public String getName() {
      return name;
    }

    public Optional<Address> getAddress() {
      return address;
    }

    public int getPhone() {
      return phone;
    }

    @Override
    public String toString() {
      return "Person{" + "name=" + name + ", address=" + address.get() + ", phone=" + phone + '}';
    }
  }

  static class Address {
    public static final Address EMPTY_ADDRESS = new Address("", "", "", 0);
    private final String line1;
    private final String city;
    private final String country;
    private final int zipcode;

    Address(String line1, String city, String country, int zipcode) {
      this.line1 = line1;
      this.city = city;
      this.country = country;
      this.zipcode = zipcode;
    }

    public String getLine1() {
      return line1;
    }

    public String getCity() {
      return city;
    }

    public String getCountry() {
      return country;
    }

    public int getZipcode() {
      return zipcode;
    }

    @Override
    public String toString() {
      return "Address{"
          + "line1="
          + line1
          + ", city="
          + city
          + ", country="
          + country
          + ", zipcode="
          + zipcode
          + '}';
    }
  }
}
