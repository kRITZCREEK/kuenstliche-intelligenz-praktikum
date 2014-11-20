package de.fhkoeln.gm.ki.alg.selectors;

import de.fhkoeln.gm.ki.alg.util.Individual;
import de.fhkoeln.gm.ki.alg.util.Population;

public class WeightedProbabilitySelector extends AbstractSelector {

	public static final double SURVIVALRATE = 0.9;
	@Override
	public Population selectFromPopulation(Population currentGen) {
		Population population = new Population();
		currentGen.sort();
		double sumFitness = currentGen.getAverageFitness()*currentGen.getCurrentSize();
		for(int i = 0; i < (int) (currentGen.getCurrentSize() * SURVIVALRATE); i++){
			double stopVal = sumFitness * Math.random();
			double accumulator = 0;
			for(int j = 0; j < currentGen.getCurrentSize(); j++){
				Individual individual = currentGen.getIndividualAt(j);
				accumulator += individual.fitness;
				if (accumulator >= stopVal){
					population.add(individual);
					break;
				}
			}						
		}
		return population;
	}

	@Override
	public String getName() {
		return "WeightedProbabilitySelector";
	}

}
