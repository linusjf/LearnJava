package com.vmlens.tutorialcopyonwrite;

@SuppressWarnings({"PMD.IllegalToken", "PMD.AvoidUsingVolatile"})
public class AddressUsingCopyOnWrite {
  private volatile AddressValue addressValue;
  private final Object lock = new Object();

  public AddressUsingCopyOnWrite(String street, String city, String phone) {
    this.addressValue = new AddressValue(street, city, phone);
  }

  public String toStringNotThreadSafe() {
    return "street=" + addressValue.getStreet() + ",city=" + addressValue.getCity() + ",phoneNumber=" + addressValue.getPhoneNumber();
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public String toString() {
    AddressValue local = addressValue;
    return "street=" + local.getStreet() + ",city=" + local.getCity() + ",phoneNumber=" + local.getPhoneNumber();
  }

  public void updatePostalAddress(String street, String city) {
    synchronized (lock) {
      addressValue = new AddressValue(street, city, addressValue.getPhoneNumber());
    }
  }

  public void updatePhoneNumber(String phoneNumber) {
    synchronized (lock) {
      addressValue = new AddressValue(addressValue.getStreet(), addressValue.getCity(), phoneNumber);
    }
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof AddressUsingCopyOnWrite)) return false;
    AddressUsingCopyOnWrite other = (AddressUsingCopyOnWrite) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$addressValue = this.addressValue;
    Object other$addressValue = other.addressValue;
    if (this$addressValue == null ? other$addressValue != null : !this$addressValue.equals(other$addressValue)) return false;
    Object this$lock = this.lock;
    Object other$lock = other.lock;
    if (this$lock == null ? other$lock != null : !this$lock.equals(other$lock)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof AddressUsingCopyOnWrite;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $addressValue = this.addressValue;
    result = result * PRIME + ($addressValue == null ? 43 : $addressValue.hashCode());
    Object $lock = this.lock;
    result = result * PRIME + ($lock == null ? 43 : $lock.hashCode());
    return result;
  }
}
