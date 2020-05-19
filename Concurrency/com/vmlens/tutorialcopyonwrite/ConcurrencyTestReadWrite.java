package com.vmlens.tutorialcopyonwrite;

import static org.junit.Assert.assertTrue;

import com.vmlens.annotation.Interleave;
import org.junit.Test;

public class ConcurrencyTestReadWrite {
  // An not thread safe address
  private final MutableAddress address =
      new MutableAddress("E. Bonanza St.", "South Park", "456 77 99");

  // Change to a thread safe address using copy on write
  // private final AddressUsingCopyOnWrite address = new
  // AddressUsingCopyOnWrite("E. Bonanza St." , "South Park" , "456 77 99");
  // using a thread safe address using copy on write does not break the test
  // private final AddressUsingCopyOnWriteWithoutSynchronized address = new
  // AddressUsingCopyOnWriteWithoutSynchronized("E. Bonanza St." , "South Park"
  // , "456 77 99");
  private String readAddress;

  @Interleave(ConcurrencyTestReadWrite.class)
  private void updatePostalAddress() {
    address.updatePostalAddress("Evergreen Terrace", "Springfield");
  }

  @Interleave(ConcurrencyTestReadWrite.class)
  private void read() {
    readAddress = address.toString();
    // readAddress = address.toStringNotThreadSafe();
  }

  @Test
  @SuppressWarnings("PMD.LawOfDemeter")
  public void test() throws InterruptedException {
    // clang-format off
    Thread first =
        new Thread(
            () -> {
              updatePostalAddress();
            });
    Thread second =
        new Thread(
            () -> {
              read();
            });

    // clang-format on
    first.start();
    second.start();
    first.join();
    second.join();
    assertTrue(
        "readAddress:" + readAddress,
        "street=E. Bonanza St.,city=South Park,phoneNumber=456 77 99".equals(readAddress)
            || "street=Evergreen Terrace,city=Springfield,phoneNumber=456 77 99"
                .equals(readAddress));
  }
}
