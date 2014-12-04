package de.fhkoeln.gm.ki.alg.mutators;

import java.util.ArrayList;

import de.fhkoeln.gm.ki.alg.genes.AbstractGene;
import de.fhkoeln.gm.ki.alg.util.Individual;
import de.fhkoeln.gm.ki.alg.util.Population;

public class RandomResetter extends AbstractMutator {
	public static final float MUTATECHANCE = 0.10f;
	@Override
	public Population mutate(Population tmpPopulation,
			AbstractGene[] viableGenes) {
		
		Population population = new Population();
		
		for (Individual i:tmpPopulation.getPop()){
			ArrayList<AbstractGene> genes = new ArrayList <AbstractGene>();
			for(int j=0; j < i.getGenes().size(); j++){
				if(Math.random() <= MUTATECHANCE){
					AbstractGene randGen = viableGenes[(int)(Math.random()*viableGenes.length)];
					genes.add(randGen);
				}else{
					genes.add(i.getGenes().get(j));
				}
			}
			population.add(new Individual(genes));
		}
		return population;
	}

	@Override
	public String getName() {
		return "Random Resetting";
	}

}
