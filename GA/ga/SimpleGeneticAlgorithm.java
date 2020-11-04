package ga;

@SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
public class SimpleGeneticAlgorithm {
  private static final double UNIFORM_RATE = 0.5f;
  private static final double MUTATION_RATE = 0.025f;
  private static final int TOURNAMENT_SIZE = 5;
  private static final boolean ELITISM = true;
  private byte[] solution = new byte[Individual.DEFAULT_GENE_LENGTH];

  public boolean runAlgorithm(int populationSize, String soln) {
    if (soln.length() != solution.length) {
      throw new IllegalStateException("The solution needs to have "
                                      + solution.length + " bytes");
    }
    setSolution(soln);
    Population myPop = new Population(populationSize, true, this);
    System.out.println("Solution expected: " + soln);
    int generationCount = 1;
    Individual fittest = myPop.getFittest();
    int fitness = fittest.getFitness();
    while (fitness < getMaxFitness()) {
      System.out.println("Generation: " + generationCount
                         + " Correct genes found: " + fitness);
      myPop = evolvePopulation(myPop);
      fittest = myPop.getFittest();
      fitness = fittest.getFitness();
      ++generationCount;
    }
    System.out.println("Solution found!");
    System.out.println("Generation: " + generationCount);
    System.out.println("Genes: ");
    System.out.println(fittest);
    return true;
  }

  public Population evolvePopulation(Population pop) {
    int elitismOffset = ELITISM ? 1 : 0;
    Population newPopulation = new Population(pop.size(), false, this);
    if (ELITISM)
      newPopulation.add(0, pop.getFittest());
    for (int i = elitismOffset; i < pop.size(); i++) {
      Individual indiv1 = tournamentSelection(pop);
      Individual indiv2 = tournamentSelection(pop);
      Individual newIndiv = crossover(indiv1, indiv2);
      newPopulation.add(i, newIndiv);
    }
    for (int i = elitismOffset; i < newPopulation.size(); i++)
      mutate(newPopulation.getIndividual(i));
    return newPopulation;
  }

  private Individual crossover(Individual indiv1, Individual indiv2) {
    Individual newIndividual = new Individual(this);
    for (int i = 0; i < newIndividual.getDefaultGeneLength(); i++) {
      if (Math.random() <= UNIFORM_RATE)
        newIndividual.setSingleGene(i, indiv1.getSingleGene(i));
      else
        newIndividual.setSingleGene(i, indiv2.getSingleGene(i));
    }
    return newIndividual;
  }

  private void mutate(Individual indiv) {
    for (int i = 0; i < indiv.getDefaultGeneLength(); i++) {
      if (Math.random() <= MUTATION_RATE) {
        byte gene = (byte)Math.round(Math.random());
        indiv.setSingleGene(i, gene);
      }
    }
  }

  private Individual tournamentSelection(Population pop) {
    Population tournament = new Population(TOURNAMENT_SIZE, false, this);
    for (int i = 0; i < TOURNAMENT_SIZE; i++) {
      int randomId = (int)(Math.random() * pop.size());
      tournament.add(i, pop.getIndividual(randomId));
    }
    return tournament.getFittest();
  }

  int getFitness(Individual individual) {
    int fitness = 0;
    for (int i = 0;
         i < individual.getDefaultGeneLength() && i < solution.length;
         i++) {
      if (individual.getSingleGene(i) == solution[i])
        fitness++;
    }
    return fitness;
  }

  int getMaxFitness() {
    return solution.length;
  }

  void setSolution(String newSolution) {
    solution = new byte[newSolution.length()];
    for (int i = 0; i < newSolution.length(); i++) {
      String character = newSolution.substring(i, i + 1);
      if (character.contains("0") || character.contains("1"))
        solution[i] = Byte.parseByte(character);
      else
        solution[i] = 0;
    }
  }

  public byte[] getSolution() {
    return this.solution.clone();
  }

  public static void main(String... args) {
    SimpleGeneticAlgorithm sga = new SimpleGeneticAlgorithm();
    sga.runAlgorithm(
        50, "1011000100000100010000100000100111001000000100000100000000001111");
  }
}
