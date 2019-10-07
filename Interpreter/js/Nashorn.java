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
      System.out.println(engine.getClass().getName());
      System.out.println("Result:"
                         + engine.eval("function f() { return 1; }; f() + 1;"));
    } catch (ScriptException se) {
      System.err.println(se);
    }
  }
}
