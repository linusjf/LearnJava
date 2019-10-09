package container;

@SuppressWarnings("PMD.ShortClassName")
public enum Main {
  ;
  public static void main(String... args) {
    MovieImpl movie = new MovieImpl();
    movie.setId("23");
    movie.setRating("UA");
    movie.setAvailable(25);
    movie.setDescription("Action");
    movie.setPrice(8.00f);
    movie.setTitle("Tarzan of the Apes");
    System.out.println(movie.hashCode());
    MovieImpl movie1 = new MovieImpl();
    movie1.setId("23");
    movie1.setRating("UA");
    movie1.setAvailable(25);
    movie1.setDescription("Action");
    movie1.setPrice(8.00f);
    movie1.setTitle("Tarzan of the Apes");
    System.out.println(movie1.hashCode());
    System.out.println(movie.equals(movie1));
    movie.addPropertyBy("3 Stars", "Review");
    System.out.println(movie.hashCode());
    movie1.addPropertyBy("3 Stars", "Review");
    System.out.println(movie1.hashCode());
    System.out.println(movie.equals(movie1));
    movie.addPropertyBy("4 Stars", "Review2");
    System.out.println(movie.hashCode());
    System.out.println(movie.equals(movie1));
  }
}
