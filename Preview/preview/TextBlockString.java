package preview;

@SuppressWarnings("preview")
public enum TextBlockString {
  ;

  /*** 
   * JEP 355: Preview Feature.
   * New methods are to be used with Text Block Strings
   * @param args - command line arguments
   */
  public static void main(String[] args) {
    String textBlock =
        """
        Hi Hello Yes
        """;

        String str = "Hi\nHello\nYes";

    System.out.println("Text Block String:\n" + textBlock);
    System.out.println("Normal String Literal:\n" + str);

    System.out.println("Text Block and String Literal equals() Comparison: "
                       + (textBlock.equals(str)));
    System.out.println("Text Block and String Literal == Comparison: "
                       + (textBlock == str));
    String textBlockHTML = """
  <html><head><link href='/css/style.css' rel='stylesheet' /></head><body><h1>Hello World</h1></body></html>
  """;

String textBlockJSON = """
{
      "name" : "Pankaj", "website" : "JournalDev"
    }
    """;
  }
}
