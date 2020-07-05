package tests;

import static org.junit.jupiter.api.Assertions.*;
import static tests.GlobalUsers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;

@SuppressWarnings("PMD.SystemPrintln")
@Execution(ExecutionMode.CONCURRENT)
public class SharedResourceTest {

  // static final String GLOBAL_USERS = "tests.GlobalUsers.users";
  static final String GLOBAL_USERS = "Hello";

  @BeforeEach
  void initiate() {
    clear();
  }

  @Test
  @SuppressWarnings("PMD.LawOfDemeter")
  @ResourceLock(value = GLOBAL_USERS, mode = ResourceAccessMode.READ)
  void isEmptyTest() {
    System.out.println("isEmptyTest() : " + getUsers());
    assertTrue(getUsers().isEmpty(), "Empty");
  }

  @Test
  @ResourceLock(value = GLOBAL_USERS, mode = ResourceAccessMode.READ_WRITE)
  void addTest() {
    add(1, "peter");
    System.out.println("addTest() : " + getUsers());
    assertEquals("peter", get(1), "peter expected");
  }

  @Test
  @ResourceLock(value = GLOBAL_USERS, mode = ResourceAccessMode.READ_WRITE)
  void updateTest() {
    update(1, "john");
    System.out.println("updateTest() : " + getUsers());
    assertEquals("john", get(1), "john expected");
  }

  @Test
  @ResourceLock(value = GLOBAL_USERS, mode = ResourceAccessMode.READ_WRITE)
  void removeTest() {
    add(2, "Anand");
    remove(2);
    System.out.println("removeTest() : " + getUsers());
    assertNull(get(2), "Value is null");
  }
}
