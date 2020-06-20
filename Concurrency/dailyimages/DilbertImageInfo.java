package dailyimages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DilbertImageInfo extends ImageInfo {
  @Override
  public DilbertImageInfo findImage(String body) {
    this.imagePath = findProperty(body, "image");
    return this;
  }

  @SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
  private static String findProperty(String body, String filter) {
    String search = "meta name=\"twitter:" + filter + "\" content=\"";
    return body.lines()
        .filter(line -> line.contains(search))
        .findFirst()
        .map(line -> line.replaceAll(".*" + search, ""))
        .map(line -> line.replaceAll("\".*", ""))
        .orElseThrow(() -> new IllegalStateException("Could not find \"" + filter + "\""));
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public String getUrlForDate(LocalDate date) {
    return "https://dilbert.com/strip/" + DateTimeFormatter.ISO_DATE.format(date);
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof DilbertImageInfo)) return false;
    DilbertImageInfo other = (DilbertImageInfo) o;
    if (!other.canEqual((Object) this)) return false;
    if (!super.equals(o)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof DilbertImageInfo;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "DilbertImageInfo()";
  }
}
