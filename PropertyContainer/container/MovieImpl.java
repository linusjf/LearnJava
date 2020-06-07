package container;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("PMD.DataClass")
public class MovieImpl extends PropertyContainerImpl implements Movie, Serializable {
  private static final long serialVersionUID = 1L;
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

  @SuppressWarnings("all")
  @Override
  public int hashCode() {
    List<Object> fields = new ArrayList<>(values());
    String[] keys = getPropertyKeys();
    for (String key : keys) fields.add(key);
    fields.add(id);
    fields.add(title);
    fields.add(available);
    fields.add(description);
    fields.add(price);
    fields.add(rating);
    return Objects.hash(fields.toArray());
  }

  @SuppressWarnings("all")
  @Override
  public boolean equals(Object o) {
    if (this == o) 
      return true;
    if (!(o instanceof MovieImpl)) 
      return false;
    MovieImpl obj = (MovieImpl) o;
    if (!super.equals(obj)) 
    return false;
    else { 
      if (!Objects.equals(available, obj.available))
          return false;
      if (!Objects.equals(price, obj.price))
      return false;
      if (!Objects.equals(description, obj.description))
        return false;
      if (!Objects.equals(rating, obj.rating))
        return false;
      if (!Objects.equals(title, obj.title))
        return false;
      if (!Objects.equals(id, obj.id))
        return false;
    }
    return true;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "MovieImpl(available=" + this.getAvailable() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ", rating=" + this.getRating() + ", title=" + this.getTitle() + ", id=" + this.getId() + ")";
  }
}
