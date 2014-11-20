package de.fhkoeln.gm.ki.alg.fitnessFunctions;

import java.util.ArrayList;

import de.fhkoeln.gm.ki.alg.util.Individual;
import de.fhkoeln.gm.ki.alg.genes.*;

public class FitnessFunction extends AbstractFitness {
	
	private float maxFitness;
	
	public FitnessFunction() {
		maxFitness = Float.NEGATIVE_INFINITY;
	}
	
	@Override
	// Errechnet die Fitness eines Individuums anhand seiner Gene
	public float evaluate(Individual genome) {
		ArrayList<AbstractGene> genes;
		float fitness = 0;
		genes = genome.getGenes();
		for (int i = 0; i < genes.size(); i++) {
			fitness += genes.get(i).execute();
		}
		if (fitness > maxFitness) {
			maxFitness = fitness;
		}
		return fitness;
	}

	@Override
	public boolean thresholdReached() {
		if (maxFitness > 3510)
			return true;
		else
			return false;
	}

	@Override
	public String getName() {
		return "FitnessFunction";
	}

}
