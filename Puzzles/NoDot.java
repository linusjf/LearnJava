@SuppressWarnings({"try",
                   "checkstyle:magicnumber",
                   "checkstyle:packagedeclaration",
                   "checkstyle:hideutilityclassconstructor",
                   "checkstyle:nowhitespacebefore",
                   "checkstyle:emptyblock",
                   "checkstyle:regexp",
                   "checkstyle:javadocpackage",
                   "PMD.NoPackage",
                   "PMD.SignatureDeclareThrowsException",
                   "PMD.UseUtilityClass"})
class NoDot {
  public static void main(String[] args) throws Exception {

    try (
        AutoCloseable o = new Throwable(
            ((char)13) + "Hello world", null, true, false) {
        } ::printStackTrace) {
    }
  }
}
