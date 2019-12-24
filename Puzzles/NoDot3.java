@SuppressWarnings({"checkstyle:packagedeclaration", "checkstyle:hideutilityclassconstructor",
    "checkstyle:magicnumber", "checkstyle:illegalthrows", "PMD.NoPackage",
    "PMD.DoubleBraceInitialization", "PMD.SignatureDeclareThrowsException", "PMD.UseUtilityClass"})
class NoDot3 {
  // using semi-colon, though
  public static void main(String[] args) throws Throwable {
    throw new Throwable(
        (char) 13 + "Hello, world!                           ", null, true, false) {};
  }
}
