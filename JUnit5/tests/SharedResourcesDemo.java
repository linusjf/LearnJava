package tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.parallel.ExecutionMode.*;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.*;
import static org.junit.jupiter.api.parallel.Resources.*;

import java.util.Properties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ResourceLock;

@Execution(CONCURRENT)
@SuppressWarnings("PMD.SystemPrintln")
class SharedResourcesDemo {

  private static final String PROP_NAME = "my.prop";
  private Properties backup;

  @BeforeEach
  void backupProperties() {
    backup = new Properties();
    backup.putAll(System.getProperties());
  }

  @AfterEach
  void restore() {
    System.setProperties(backup);
  }

  @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ)
  @RepeatedTest(100)
  @Order(1)
  void customPropertyIsNotSetByDefault(RepetitionInfo ri) {
    System.out.println("customProperty " + ri.getCurrentRepetition());
    assertNull(System.getProperty(PROP_NAME), "Null property");
  }

  @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
  @RepeatedTest(100)
  @Order(2)
  void canSetCustomPropertyToApple(RepetitionInfo ri) {
    System.out.println("apple " + ri.getCurrentRepetition());
    System.setProperty(PROP_NAME, "apple");
    assertEquals("apple", System.getProperty(PROP_NAME), "Equals apple");
  }

  @ResourceLock(value = SYSTEM_PROPERTIES, mode = READ_WRITE)
  @RepeatedTest(100)
  @Order(2)
  void canSetCustomPropertyToBanana(RepetitionInfo ri) {
    System.out.println("banana " + ri.getCurrentRepetition());
    System.setProperty(PROP_NAME, "banana");
    assertEquals("banana", System.getProperty(PROP_NAME), "Equals banana");
  }
}
