package javapuzzles;

public enum Arrayed {
  ;

  public static void main(String... args) {
  
int[] ia = new int[2]; 
ia[1] = 1;
for (int v : ia) System.out.print(v + " ");
String[] sa = new String[2]; 
sa[0] = "Hi"; 
for (String v : sa) System.out.print(v + " "); 
  }
}
