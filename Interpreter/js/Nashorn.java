package js;

import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public enum Nashorn {
  ;
  private static final Logger LOGGER =
      Logger.getLogger(Nashorn.class.getName());

  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String... args) {
    try {
      ScriptEngineManager manager = new ScriptEngineManager();
      ScriptEngine engine = manager.getEngineByName("JavaScript");
      LOGGER.info(() -> String.format("%s", getClassForObject(engine)));
      System.out.println(
          "Result:" + eval(engine, "function f() { return 1; }; f() + 1;"));
    } catch (ScriptException se) {
      LOGGER.severe(se.getMessage());
    }
  }

  private static Class<?> getClassForObject(Object obj) {
    return obj.getClass();
  }

  private static Object eval(ScriptEngine engine, String scriptlet)
      throws ScriptException {
    return engine.eval(scriptlet);
  }
}
