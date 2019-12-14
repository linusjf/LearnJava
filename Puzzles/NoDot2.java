@SuppressWarnings({"deprecation", "try", "checkstyle:singlespaceseparator",
    "checkstyle:packagedeclaration", "checkstyle:hideutilityclassconstructor",
    "checkstyle:magicnumber", "checkstyle:regexpheader", "checkstyle:emptyblock", "PMD.NoPackage",
    "PMD.CompareObjectsWithEquals", "PMD.DoubleBraceInitialization", "PMD.EmptyIfStmt",
    "PMD.SignatureDeclareThrowsException", "PMD.UseUtilityClass"})
class NoDot2 {
  public static void main(String[] args) throws Exception {
    if (new ClassLoader() {
          {
            try (AutoCloseable o = defineClass("A",
                     new byte[] {-54, -2, -70, -66, 0, 3, 0, 45, 0, 25, 12, 0, 20, 0, 24, 1, 0, 16,
                         106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 1,
                         0, 6, 60, 105, 110, 105, 116, 62, 7, 0, 2, 12, 0, 3, 0, 8, 7, 0, 13, 1, 0,
                         5, 112, 114, 105, 110, 116, 1, 0, 3, 40, 41, 86, 7, 0, 18, 1, 0, 4, 67,
                         111, 100, 101, 9, 0, 9, 0, 1, 1, 0, 10, 83, 111, 117, 114, 99, 101, 70,
                         105, 108, 101, 1, 0, 19, 106, 97, 118, 97, 47, 105, 111, 47, 80, 114, 105,
                         110, 116, 83, 116, 114, 101, 97, 109, 10, 0, 4, 0, 5, 10, 0, 6, 0, 16, 12,
                         0, 7, 0, 22, 1, 0, 1, 65, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110,
                         103, 47, 83, 121, 115, 116, 101, 109, 1, 0, 12, 72, 101, 108, 108, 111, 32,
                         119, 111, 114, 108, 100, 10, 1, 0, 3, 111, 117, 116, 8, 0, 19, 1, 0, 21,
                         40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105,
                         110, 103, 59, 41, 86, 7, 0, 17, 1, 0, 21, 76, 106, 97, 118, 97, 47, 105,
                         111, 47, 80, 114, 105, 110, 116, 83, 116, 114, 101, 97, 109, 59, 0, 33, 0,
                         23, 0, 4, 0, 0, 0, 0, 0, 1, 0, 1, 0, 3, 0, 8, 0, 1, 0, 10, 0, 0, 0, 25, 0,
                         2, 0, 1, 0, 0, 0, 13, 42, -73, 0, 14, -78, 0, 11, 18, 21, -74, 0, 15, -79,
                         0, 0, 0, 0, 0, 1, 0, 12, 0, 0, 0, 2, 0, 17},
                     0, 292)::newInstance) {
            }
          }
        }
        == null) {
    }
  }
}
