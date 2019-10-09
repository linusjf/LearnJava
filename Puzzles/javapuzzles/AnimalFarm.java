package javapuzzles;

@SuppressWarnings("PMD")
public enum AnimalFarm {
  ;
  public static void main(String[] args) {
    final String pig = "length: 10";
    final String cat = "length: 10";
    final String dog = "length: " + pig.length();
    System.out.println("Animals are equal: " + pig == dog);
    System.out.println(("Animals are equal: " + pig) == dog);
    System.out.println("Animals are equal: " + (pig == dog));
    System.out.println("Animals are equal: " + pig.equals(dog));
    System.out.println("Animals are equal: " + (cat == pig));
  }
}
