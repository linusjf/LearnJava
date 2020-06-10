package com.vmlens.tutorialcopyonwrite;

@SuppressWarnings("PMD.AvoidUsingVolatile")
public class MutableAddress {
  private volatile String street;
  private volatile String city;
  private volatile String phoneNumber;

  public MutableAddress(String street, String city, String phoneNumber) {
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

  public void updatePostalAddress(String st, String cty) {
    this.street = st;
    this.city = cty;
  }

  @Override
  public String toString() {
    return "street=" + street + ",city=" + city + ",phoneNumber=" + phoneNumber;
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof MutableAddress)) return false;
    MutableAddress other = (MutableAddress) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$street = this.getStreet();
    Object other$street = other.getStreet();
    if (this$street == null ? other$street != null : !this$street.equals(other$street)) return false;
    Object this$city = this.getCity();
    Object other$city = other.getCity();
    if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false;
    Object this$phoneNumber = this.phoneNumber;
    Object other$phoneNumber = other.phoneNumber;
    if (this$phoneNumber == null ? other$phoneNumber != null : !this$phoneNumber.equals(other$phoneNumber)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof MutableAddress;
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
    Object $phoneNumber = this.phoneNumber;
    result = result * PRIME + ($phoneNumber == null ? 43 : $phoneNumber.hashCode());
    return result;
  }
}
