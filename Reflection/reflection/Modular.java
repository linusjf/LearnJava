package reflection;

import java.lang.module.ModuleDescriptor;
import java.util.Set;

public enum Modular {
  ;
  private static Module myClassModule = String.class.getModule();

  public static void main(String... args) {
    System.out.println("isNamed: " + myClassModule.isNamed());
    ModuleDescriptor md = myClassModule.getDescriptor();
    printModuleDescription(md);
  }

  private static void printModuleDescription(ModuleDescriptor md) {
    System.out.println("Module name: " + md.name());
    System.out.println("Module exports: ");
    Set<ModuleDescriptor.Exports> exports = md.exports();
    System.out.println(exports);
    System.out.println("isOpen: " + md.isOpen());
    System.out.println("Module packages: ");
    Set<String> packages = md.packages();
    System.out.println(packages);
    System.out.println("Module uses: ");
    Set<String> uses = md.uses();
    System.out.println(uses);
  }
}
