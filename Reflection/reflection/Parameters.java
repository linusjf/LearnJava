package reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public enum Parameters {
  ;

  public static void main(String... args) {
    Class<String> stringClass = String.class;
    System.out.println("Printing out String methods:");
    for (Method methodStringClass: stringClass.getDeclaredMethods()) {
      System.out.println("method " + methodStringClass.getName());
      for (Parameter paramMethodStringClass:
           methodStringClass.getParameters()) {
        // arg0, arg1, etc because the eclipse compiling tool (different ←-
        // not support -parameters option yet
        System.out.println(" parameter name "
                           + paramMethodStringClass.getName());
        System.out.println(" parameter type "
                           + paramMethodStringClass.getType());
      }
    }
    Class<DummyTask> taskClass = DummyTask.class;
    System.out.println("Printing out DummyTask methods:");
    for (Method methodTaskClass: taskClass.getDeclaredMethods()) {
      System.out.println("method " + methodTaskClass.getName());
      for (Parameter paramMethodTaskClass: methodTaskClass.getParameters()) {
        // arg0, arg1, etc because the eclipse compiling tool (different ←-
        // not support -parameters option yet
        System.out.println(" parameter name " + paramMethodTaskClass.getName());
        System.out.println(" parameter type " + paramMethodTaskClass.getType());
      }
    }
  }

  static class DummyTask {

    public void execute(int num, String message) {
      // empty method body
    }
  }
}
