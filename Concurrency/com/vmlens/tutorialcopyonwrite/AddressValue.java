package com.vmlens.tutorialcopyonwrite;

public class AddressValue {
  private final String street;
  private final String city;
  private final String phoneNumber;

  public AddressValue(String street, String city, String phoneNumber) {
    this.street = street;
    this.city = city;
    this.phoneNumber = phoneNumber;
  }

  public String getStreet() {
    return street;
  }

  public String getCity() {
    return city;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof AddressValue))
      return false;
    AddressValue other = (AddressValue)o;
    if (!other.canEqual((Object)this))
      return false;
    Object this$street = this.getStreet();
    Object other$street = other.getStreet();
    if (this$street == null ? other$street != null
                            : !this$street.equals(other$street))
      return false;
    Object this$city = this.getCity();
    Object other$city = other.getCity();
    if (this$city == null ? other$city != null : !this$city.equals(other$city))
      return false;
    Object this$phoneNumber = this.getPhoneNumber();
    Object other$phoneNumber = other.getPhoneNumber();
    if (this$phoneNumber == null ? other$phoneNumber != null
                                 : !this$phoneNumber.equals(other$phoneNumber))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof AddressValue;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $street = this.getStreet();
    result = result * PRIME + ($street == null ? 43 : $street.hashCode());
    Object $city = this.getCity();
    result = result * PRIME + ($city == null ? 43 : $city.hashCode());
    Object $phoneNumber = this.getPhoneNumber();
    result =
        result * PRIME + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "AddressValue(street=" + this.getStreet() + ", city="
        + this.getCity() + ", phoneNumber=" + this.getPhoneNumber() + ")";
  }
}
