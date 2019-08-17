package container;

public interface Movie {
  int getAvailable();

  void setAvailable(int available);

  String getDescription();

  void setDescription(String description);

  float getPrice();

  void setPrice(float price);

  String getRating();

  void setRating(String rating);

  String getTitle();

  void setTitle(String title);

  String getId();

  void setId(String id);
}
