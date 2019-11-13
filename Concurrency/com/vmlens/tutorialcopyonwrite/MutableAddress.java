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
}
