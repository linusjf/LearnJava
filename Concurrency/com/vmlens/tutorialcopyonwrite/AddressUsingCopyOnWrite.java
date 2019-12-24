package com.vmlens.tutorialcopyonwrite;

@SuppressWarnings({"PMD.IllegalToken", "PMD.AvoidUsingVolatile"})
public class AddressUsingCopyOnWrite {
  private volatile AddressValue addressValue;
  private final Object lock = new Object();

  public AddressUsingCopyOnWrite(String street, String city, String phone) {
    this.addressValue = new AddressValue(street, city, phone);
  }

  public String toStringNotThreadSafe() {
    return "street="
        + addressValue.getStreet()
        + ",city="
        + addressValue.getCity()
        + ",phoneNumber="
        + addressValue.getPhoneNumber();
  }

  @Override
  public String toString() {
    AddressValue local = addressValue;
    return "street="
        + local.getStreet()
        + ",city="
        + local.getCity()
        + ",phoneNumber="
        + local.getPhoneNumber();
  }

  public void updatePostalAddress(String street, String city) {
    synchronized (lock) {
      addressValue = new AddressValue(street, city, addressValue.getPhoneNumber());
    }
  }

  public void updatePhoneNumber(String phoneNumber) {
    synchronized (lock) {
      addressValue =
          new AddressValue(addressValue.getStreet(), addressValue.getCity(), phoneNumber);
    }
  }
}
