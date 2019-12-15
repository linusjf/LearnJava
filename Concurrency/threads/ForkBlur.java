package threads;

/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;

/**
 * ForkBlur implements a simple horizontal image blur. It averages pixels in the source array and
 * writes them to a destination array. The S_THRESHOLD value determines whether the blurring will be
 * performed directly or split into two tasks.
 *
 * <p>This is not the recommended way to blur images; it is only intended to illustrate the use of
 * the Fork/Join framework.
 */
public class ForkBlur extends RecursiveAction {
  public static final long serialVersionUID = 1L;
  protected static final int S_THRESHOLD = 10_000;
  private static AtomicInteger taskCount = new AtomicInteger(0);
  private static final int BLUR_WIDTH = 15;

  private static final int SIDE_PIXELS = (BLUR_WIDTH - 1) / 2;

  private final int[] mSource;
  private final int mStart;
  private final int mLength;
  private int[] mDestination;

  // Processing window size, should be odd.
  @SuppressWarnings("PMD.ArrayIsStoredDirectly")
  public ForkBlur(final int[] src, int start, int length, finalint... dst) {
    super();
    mSource = src;
    mStart = start;
    mLength = length;
    mDestination = dst;
  }

  // Average pixels from source, write results into destination.
  protected void computeDirectly() {
    for (int index = mStart; index < mStart + mLength; index++) {
      // Calculate average.
      // clang-format off
      float rt = 0, gt = 0, bt = 0; // NOPMD

      // clang-format on
      for (int mi = -SIDE_PIXELS; mi <= SIDE_PIXELS; mi++) {
        int mindex = Math.min(Math.max(mi + index, 0), mSource.length - 1);
        int pixel = mSource[mindex];
        rt += (float) ((pixel & 0x00ff0000) >> 16) / BLUR_WIDTH;
        gt += (float) ((pixel & 0x0000ff00) >> 8) / BLUR_WIDTH;
        bt += (float) ((pixel & 0x000000ff) >> 0) / BLUR_WIDTH;
      }

      // Re-assemble destination pixel.
      int dpixel = 0xff000000 |
      (((int) rt) << 16) |
      (((int) gt) << 8) |
      (((int) bt) << 0);
      mDestination[index] = dpixel;
    }
  }

  @Override
  protected void compute() {
    taskCount.getAndIncrement();
    if (mLength < S_THRESHOLD) {
      computeDirectly();
      return;
    }

    int split = mLength / 2;

    invokeAll(
      new ForkBlur(mSource, mStart, split, mDestination),
      new ForkBlur(mSource, mStart + split, mLength - split, mDestination)
    );
  }

  // Plumbing follows.
  public static void main(String... args) {
    try {
      String srcName = "Red_Tulips.jpg";
      File srcFile = new File(srcName);
      BufferedImage image = ImageIO.read(srcFile);

      BufferedImage img = new BufferedImage(
        image.getWidth(),
        image.getHeight(),
        BufferedImage.TYPE_3BYTE_BGR
      );
      img.getGraphics().drawImage(image, 0, 0, null);
      System.out.println("Source image: " + srcName);

      BufferedImage blurredImage = blur(img);

      String dstName = "blurred-tulips.jpg";
      File dstFile = new File(dstName);
      ImageIO.write(blurredImage, "jpeg", dstFile);

      System.out.println("Output image: " + dstName);
    } catch (IOException ioe) {
      System.err.println(ioe);
    }
  }

  public static BufferedImage blur(BufferedImage srcImage) {
    int w = srcImage.getWidth();
    int h = srcImage.getHeight();

    int[] src = srcImage.getRGB(0, 0, w, h, null, 0, w);
    int[] dst = new int[src.length];

    System.out.println("Array size is " + src.length);
    System.out.println("Threshold is " + S_THRESHOLD);

    int processors = Runtime.getRuntime().availableProcessors();
    System.out.println(
      Integer.toString(processors) +
        " processor" +
        (processors > 1 ? "s are " : " is ") +
        "available"
    );

    ForkBlur fb = new ForkBlur(src, 0, src.length, dst);

    ForkJoinPool pool = new ForkJoinPool();

    long startTime = System.currentTimeMillis();
    pool.invoke(fb);
    pool.shutdown();
    long endTime = System.currentTimeMillis();

    System.out.println(
      "Image blur took " + (endTime - startTime) + " milliseconds."
    );
    System.out.println("Task count: " + taskCount.get());
    BufferedImage dstImage = new BufferedImage(w, h, srcImage.getType());
    dstImage.getGraphics().drawImage(srcImage, 0, 0, null);
    dstImage.setRGB(0, 0, w, h, dst, 0, w);

    return dstImage;
  }
}
