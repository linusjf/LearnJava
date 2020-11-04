package ga;

import java.util.ArrayList;
import java.util.List;

public class Population {
  private List<Individual> individuals;
  private final SimpleGeneticAlgorithm sga;

  public Population(int size, boolean createNew, SimpleGeneticAlgorithm sga) {
    this.sga = sga;
    individuals = new ArrayList<>();
    if (createNew) {
      createNewPopulation(size);
    }
  }

  protected Individual getIndividual(int index) {
    return individuals.get(index);
  }

  @SuppressWarnings("PMD.LawOfDemeter")
  protected Individual getFittest() {
    Individual fittest = individuals.get(0);
    for (int i = 0; i < individuals.size(); i++) {
      if (fittest.getFitness() <= getIndividual(i).getFitness()) {
        fittest = getIndividual(i);
      }
    }
    return fittest;
  }

  private void createNewPopulation(int size) {
    for (int i = 0; i < size; i++) {
      Individual newIndividual = new Individual(sga);
      individuals.add(i, newIndividual);
    }
  }

  @SuppressWarnings("all")
  public List<Individual> getIndividuals() {
    return this.individuals;
  }

  @SuppressWarnings("all")
  public void setIndividuals(List<Individual> individuals) {
    this.individuals = individuals;
  }

  @Override
  @SuppressWarnings("all")
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Population))
      return false;
    Population other = (Population)o;
    if (!other.canEqual((Object)this))
      return false;
    Object this$individuals = this.getIndividuals();
    Object other$individuals = other.getIndividuals();
    if (this$individuals == null ? other$individuals != null
                                 : !this$individuals.equals(other$individuals))
      return false;
    return true;
  }

  @SuppressWarnings("all")
  protected boolean canEqual(Object other) {
    return other instanceof Population;
  }

  @Override
  @SuppressWarnings("all")
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $individuals = this.getIndividuals();
    result =
        result * PRIME + ($individuals == null ? 43 : $individuals.hashCode());
    return result;
  }

  @Override
  @SuppressWarnings("all")
  public String toString() {
    return "Population(individuals=" + this.getIndividuals() + ")";
  }
}
