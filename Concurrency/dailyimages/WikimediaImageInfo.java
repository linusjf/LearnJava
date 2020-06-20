package dailyimages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WikimediaImageInfo extends ImageInfo {
  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public WikimediaImageInfo findImage(String body) {
    Pattern pattern = Pattern.compile("<img\\s+alt=\"[^\"]+\"\\s+src=\"(?<src>[^\\\"]+)\"");
    Matcher matcher = pattern.matcher(body);
    if (matcher.find()) {
      this.imagePath = matcher.group("src");
    }
    return this;
  }

  @Override
  @SuppressWarnings("PMD.LawOfDemeter")
  public String getUrlForDate(LocalDate date) {
    return "https://commons.m.wikimedia.org/wiki/Special:FeedItem/potd/"
        + DateTimeFormatter.BASIC_ISO_DATE.format(date)
        + "000000/en";
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof WikimediaImageInfo)) return false;
    WikimediaImageInfo other = (WikimediaImageInfo) o;
    if (!other.canEqual((Object) this)) return false;
    if (!super.equals(o)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof WikimediaImageInfo;
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
    return "WikimediaImageInfo()";
  }
}
