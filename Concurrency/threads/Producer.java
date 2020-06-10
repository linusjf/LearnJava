package threads;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
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
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {
  private final Drop drop;
  private final Random random;

  public Producer(Drop drop) {
    this.drop = drop;
    this.random = new Random();
  }

  @Override
  public void run() {
    String[] importantInfo = {"Mares eat oats", "Does eat oats", "Little lambs eat ivy", "A kid will eat ivy too"};
    for (String info : importantInfo) {
      drop.put(info);
      try {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(5000));
      } catch (InterruptedException e) {
        System.err.println(e);
      }
    }
    drop.put("DONE");
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Producer)) return false;
    Producer other = (Producer) o;
    if (!other.canEqual((Object) this)) return false;
    Object this$drop = this.drop;
    Object other$drop = other.drop;
    if (this$drop == null ? other$drop != null : !this$drop.equals(other$drop)) return false;
    Object this$random = this.random;
    Object other$random = other.random;
    if (this$random == null ? other$random != null : !this$random.equals(other$random)) return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Producer;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $drop = this.drop;
    result = result * PRIME + ($drop == null ? 43 : $drop.hashCode());
    Object $random = this.random;
    result = result * PRIME + ($random == null ? 43 : $random.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "Producer(drop=" + this.drop + ", random=" + this.random + ")";
  }
}
