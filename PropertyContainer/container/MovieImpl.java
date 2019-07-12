package container;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class MovieImpl
    extends PropertyContainerImpl implements Movie, Serializable {

  public static final long serialVersionUID = 1L;

  private int available;
  private String description;
  private float price;
  private String rating;
  private String title;
  private String id;

  @Override
  public int getAvailable() {
    return this.available;
  }

  @Override
  public void setAvailable(int available) {
    this.available = available;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public float getPrice() {
    return this.price;
  }

  @Override
  public void setPrice(float price) {
    this.price = price;
  }

  @Override
  public String getRating() {
    return this.rating;
  }

  @Override
  public void setRating(String rating) {
    this.rating = rating;
  }

  @Override
  public String getTitle() {
    return this.title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    List<Object> fields 
      = new ArrayList<>(values());
    String[] keys = getPropertyKeys();
    for (String key: keys)
      fields.add(key);
    fields.add(id);
    fields.add(title);
    fields.add(available);
    fields.add(description);
    fields.add(price);
    fields.add(rating);
    return Objects.hash(fields.toArray());
  }

  @Override
  public boolean equals(Object o) {
  if (this == o)  
            return true; 
  if (!(o instanceof MovieImpl))  
            return false;
  MovieImpl obj = (MovieImpl)o;
  if (super.equals(obj))
    return Objects.equals(available,obj.available) &&
      Objects.equals(price,obj.price) &&
      Objects.equals(description,obj.description) &&
 Objects.equals(rating,obj.rating) &&
 Objects.equals(title,obj.title) &&
 Objects.equals(id,obj.id);
  return false;
  }
}
