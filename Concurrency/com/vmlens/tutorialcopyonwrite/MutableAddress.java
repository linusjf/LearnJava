package com.vmlens.tutorialcopyonwrite;

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

  public void updatePostalAddress(String street, String city) {
    this.street = street;
    this.city = city;
  }

  @Override
  public String toString() {
    return "street=" + street + ",city=" + city + ",phoneNumber=" + phoneNumber;
  }
}
