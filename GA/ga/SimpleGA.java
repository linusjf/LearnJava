package ga;

import io.jenetics.BitChromosome;
import io.jenetics.BitGene;
import io.jenetics.Genotype;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.Factory;

@SuppressWarnings({"PMD.LawOfDemeter", "PMD.SystemPrintln"})
public final class SimpleGA {

  private SimpleGA() {
    // empty constructor
  }

  private static Integer eval(Genotype<BitGene> gt) {
    return ((BitChromosome)gt.chromosome()).bitCount();
  }

  public static void main(String[] args) {
    // 1.) Define the genotype (factory) suitable
    //     for the problem.
    Factory<Genotype<BitGene>> gtf = Genotype.of(BitChromosome.of(10, 0.5));
    System.out.println("Before the evolution:\n" + gtf);
    // 3.) Create the execution environment.
    Engine<BitGene, Integer> engine =
        Engine.builder(SimpleGA::eval, gtf).build();

    // 4.) Start the execution (evolution) and
    //     collect the result.
    Genotype<BitGene> result =
        engine.stream().limit(100).collect(EvolutionResult.toBestGenotype());

    System.out.println("After the evoluation:\n" + result);
  }
}
