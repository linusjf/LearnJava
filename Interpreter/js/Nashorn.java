package js;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public enum Nashorn {
  ;

  public static void main(String... args) {
    try {
      ScriptEngineManager manager = new ScriptEngineManager();
      ScriptEngine engine = manager.getEngineByName("JavaScript");
      System.out.println(getClassForObject(engine));
      System.out.println("Result:" + eval(engine, "function f() { return 1; }; f() + 1;"));
    } catch (ScriptException se) {
      System.err.println(se);
    }
  }

  private static Class<?> getClassForObject(Object obj) {
    return obj.getClass();
  }

  private static Object eval(ScriptEngine engine, String scriptlet) throws ScriptException {
    return engine.eval(scriptlet);
  }
}
