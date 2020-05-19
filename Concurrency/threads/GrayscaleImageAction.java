package threads;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

public class GrayscaleImageAction extends RecursiveAction {
  private static final long serialVersionUID = 1L;
  private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
  private final int row;
  private final transient BufferedImage bufferedImage;

  public GrayscaleImageAction(int row, BufferedImage bufferedImage) {
    super();
    this.row = row;
    this.bufferedImage = bufferedImage;
  }

  @Override
  protected void compute() {
    for (int column = 0; column < bufferedImage.getWidth(); column++) {
      int rgb = bufferedImage.getRGB(column, row);
      int r = (rgb >> 16) & 0xFF;
      int g = (rgb >> 8) & 0xFF;
      int b = rgb & 0xFF;
      int gray = (int) (0.2126 * (float) r + 0.7152 * (float) g + 0.0722 * (float) b);
      gray = (gray << 16) + (gray << 8) + gray;

      bufferedImage.setRGB(column, row, gray);
    }
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try {
      ForkJoinPool pool = new ForkJoinPool(AVAILABLE_PROCESSORS);
      BufferedImage bufferedImage = ImageIO.read(new File(args[0]));
      BufferedImage img =
          new BufferedImage(
              bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
      img.getGraphics().drawImage(bufferedImage, 0, 0, null);
      int height = bufferedImage.getHeight();
      final long startTime = System.currentTimeMillis();
      for (int row = 0; row < height; row++) {
        GrayscaleImageAction action = new GrayscaleImageAction(row, img);
        pool.execute(action);
      }
      pool.shutdown();
      pool.awaitTermination(1, TimeUnit.DAYS);
      final long endTime = System.currentTimeMillis();
      System.out.println("Image graying took " + (endTime - startTime) + " milliseconds.");
      ImageIO.write(img, "jpg", new File(args[1]));
    } catch (IOException | InterruptedException ioe) {
      System.err.println(ioe);
    }
  }
}
