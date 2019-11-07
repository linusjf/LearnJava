package similarity;

import info.debatty.java.stringsimilarity.NGram;

public enum NGramTest {
  ;

    public static void main(String[] args) {
        
        System.out.println("\nNGram :\n");
        // produces 0.583333
        NGram twogram = new NGram(2);
        System.out.println(twogram.distance("ABCD", "ABTUIO"));
        
        twogram = new NGram(2);
        System.out.println(twogram.distance("ABCD", "UVWXYZ"));
        
        // does not produce 0.97222
        String s1 = "Adobe CreativeSuite 5 Master Collection from cheap 4zp";
        String s2 = "Adobe CreativeSuite 5 Master Collection from cheap d1x";
        NGram ngram = new NGram(4);
        System.out.println(ngram.distance(s1, s2));
    }
}
