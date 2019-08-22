package dailyimages;

import java.time.LocalDate;

abstract class ImageInfo {
  private String date;
  protected String imagePath;
  private byte[] imageData;

  public abstract ImageInfo findImage(String body);

  public void setDate(String date) {
    this.date = date;
  }

  public String getDate() {
    return date;
  }

  public String getImagePath() {
    return imagePath;
  }

  @SuppressWarnings("checkstyle:hiddenfield")
  public ImageInfo setImageData(byte[] imageData) {
    this.imageData = imageData;
    return this;
  }

  public byte[] getImageData() {
    return imageData;
  }

  @SuppressWarnings("checkstyle:hiddenfield")
  public abstract String getUrlForDate(LocalDate date);
}
