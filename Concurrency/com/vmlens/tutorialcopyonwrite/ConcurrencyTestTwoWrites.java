package com.vmlens.tutorialcopyonwrite;

import static org.junit.jupiter.api.Assertions.*;

import com.vmlens.annotation.Interleave;
import org.junit.jupiter.api.Test;

public class ConcurrencyTestTwoWrites {
  // Not thread safe address since the write is not guarded by a synchronized
  // block
  private final AddressUsingCopyOnWriteWithoutSynchronized address =
      new AddressUsingCopyOnWriteWithoutSynchronized("E. Bonanza St.",
                                                     "South Park",
                                                     "456 77 99");

  // Thread safe, the write is guarded by a synchronized block
  // private final AddressUsingCopyOnWrite address = new
  // AddressUsingCopyOnWrite("E. Bonanza St." , "South Park" , "456 77 99");
  @Interleave(ConcurrencyTestTwoWrites.class)
  private void updatePostalAddress() {
    address.updatePostalAddress("Evergreen Terrace", "Springfield");
  }

  @Interleave(ConcurrencyTestTwoWrites.class)
  private void updatePhoneNumber() {
    address.updatePhoneNumber("99 55 2222");
  }

  @Test
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
              updatePhoneNumber();
            });

    // clang-format on
    first.start();
    second.start();
    first.join();
    second.join();
    assertEquals(
        address.toString(),
        "street=Evergreen Terrace,city=Springfield,phoneNumber=99 55 2222",
        "The two address strings match");
  }
}
