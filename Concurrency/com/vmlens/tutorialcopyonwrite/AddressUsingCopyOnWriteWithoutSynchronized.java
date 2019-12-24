package com.vmlens.tutorialcopyonwrite;

@SuppressWarnings({"PMD.IllegalToken", "PMD.AvoidUsingVolatile"})
public class AddressUsingCopyOnWriteWithoutSynchronized {
  private volatile AddressValue addressValue;

  public AddressUsingCopyOnWriteWithoutSynchronized(String street, String city, String phone) {
    this.addressValue = new AddressValue(street, city, phone);
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
    addressValue = new AddressValue(street, city, addressValue.getPhoneNumber());
  }

  public void updatePhoneNumber(String phone) {
    addressValue = new AddressValue(addressValue.getStreet(), addressValue.getCity(), phone);
  }
}
