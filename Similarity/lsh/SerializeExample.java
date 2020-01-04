package lsh;

import info.debatty.java.lsh.LSHMinHash;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Random;

public enum SerializeExample {
  ;

  @SuppressWarnings("PMD.LawOfDemeter")
  public static void main(String[] args) {
    try {
      // Create a single random boolean vector
      int n = 100;
      double sparsity = 0.75;
      boolean[] vector = new boolean[n];
      Random rand = new Random();
      for (int j = 0; j < n; j++)
        vector[j] = rand.nextDouble() > sparsity;

      // Create and configure LSH
      int stages = 2;
      int buckets = 10;
      LSHMinHash lsh = new LSHMinHash(stages, buckets, n);
      println(lsh.hash(vector));

      // Create another LSH object
      // as the parameters of the hashing function are randomly initialized
      // these two LSH objects will produce different hashes for the same
      // input vector!
      LSHMinHash otherLSH = new LSHMinHash(stages, buckets, n);
      println(otherLSH.hash(vector));

      // Moreover, signatures produced by different LSH objects cannot
      // be used to compute estimated similarity!
      // The solution is to serialize and save the object, so it can be
      // reused later...
      // this should not be flagged by LOD
      File tempfile = File.createTempFile("lshobject", ".ser");
      // this should not be flagged by LOD
      OutputStream fout = Files.newOutputStream(tempfile.toPath());
      ObjectOutputStream oos = new ObjectOutputStream(fout);
      oos.writeObject(lsh);
      oos.close();
      System.out.println("LSH object serialized to "
                         + tempfile.getAbsolutePath());

      // this should not be flagged by LOD
      InputStream fin = Files.newInputStream(tempfile.toPath());
      ObjectInputStream ois = new ObjectInputStream(fin);
      LSHMinHash savedLSH = (LSHMinHash)ois.readObject();
      println(savedLSH.hash(vector));
    } catch (IOException | ClassNotFoundException exc) {
      System.err.println(exc.getMessage());
    }
  }

  static void println(int... array) {
    System.out.print("[");
    for (int v: array) {
      System.out.print(v + " ");
    }
    System.out.println("]");
  }
}
