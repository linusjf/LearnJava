package annotations;

import java.util.List;
import java.util.ArrayList;

public class Typing {
  @SuppressWarnings("unused")
  public static void main(String... args) {
    // type def
    @TypeAnnotated
    String cannotBeEmpty = null;
    // type
    List<@TypeAnnotated String> myList = new ArrayList<>();
    // values
    String myString = new @TypeAnnotated String("this is annotated in java 8");
  }

  // in method params
  public void methodAnnotated(@TypeAnnotated int parameter) {
    System.out.println("do nothing");
  }
}
