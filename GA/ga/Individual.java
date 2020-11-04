package ga;

@SuppressWarnings("PMD.DataClass")
public class Individual {
  static final int DEFAULT_GENE_LENGTH = 64;
  private byte[] genes = new byte[DEFAULT_GENE_LENGTH];
  private int fitness;
  private final SimpleGeneticAlgorithm sga;

  public Individual(SimpleGeneticAlgorithm sga) {
    this.sga = sga;
    for (int i = 0; i < genes.length; i++) {
      byte gene = (byte)Math.round(Math.random());
      genes[i] = gene;
    }
  }

  byte getSingleGene(int index) {
    return genes[index];
  }

  void setSingleGene(int index, byte value) {
    genes[index] = value;
    fitness = 0;
  }

  int getFitness() {
    if (fitness == 0)
      fitness = sga.getFitness(this);
    return fitness;
  }

  @Override
  public String toString() {
    StringBuilder geneString = new StringBuilder(genes.length);
    for (int i = 0; i < genes.length; i++)
      geneString.append(getSingleGene(i));
    return geneString.toString();
  }

  public int getDefaultGeneLength() {
    return DEFAULT_GENE_LENGTH;
  }

  public byte[] getGenes() {
    return this.genes.clone();
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Individual))
      return false;
    Individual other = (Individual)o;
    if (!other.canEqual((Object)this))
      return false;
    if (this.getDefaultGeneLength() != other.getDefaultGeneLength())
      return false;
    if (this.getFitness() != other.getFitness())
      return false;
    if (!java.util.Arrays.equals(this.getGenes(), other.getGenes()))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Individual;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    result = result * PRIME + this.getDefaultGeneLength();
    result = result * PRIME + this.getFitness();
    result = result * PRIME + java.util.Arrays.hashCode(this.getGenes());
    return result;
  }
}
