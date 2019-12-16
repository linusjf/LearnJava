package dailyimages;

import java.time.LocalDate;

@SuppressWarnings("PMD.DataClass")
abstract class ImageInfo {
  protected String imagePath;
  private String date;
  private byte[] imageData;

  public abstract ImageInfo findImage(String body);

  @SuppressWarnings({"checkstyle:hiddenfield", "PMD.LinguisticNaming", "PMD.ArrayIsStoredDirectly"})
  public ImageInfo setImageData(byte[] imageData) {
    this.imageData = imageData;
    return this;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDate() {
    return date;
  }

  public String getImagePath() {
    return imagePath;
  }

  @SuppressWarnings({"checkstyle:hiddenfield", "PMD.MethodReturnsInternalArray"})
  public byte[] getImageData() {
    return imageData;
  }

  @SuppressWarnings("checkstyle:hiddenfield") public abstract String getUrlForDate(LocalDate date);
}
