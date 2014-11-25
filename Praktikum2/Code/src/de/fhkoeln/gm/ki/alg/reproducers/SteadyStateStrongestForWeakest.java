package de.fhkoeln.gm.ki.alg.reproducers;

import de.fhkoeln.gm.ki.alg.fitnessFunctions.FitnessFunction;
import de.fhkoeln.gm.ki.alg.util.Population;

public class SteadyStateStrongestForWeakest extends AbstractReproducer{
	
	int i = 0;

	@Override
	public Population reproduce(Population oldGeneration,
			Population tmpGeneration) {
		
		FitnessFunction fitnessCalc = new FitnessFunction();
		for (int i = 0; i < tmpGeneration.getCurrentSize(); i++) {
		 	fitnessCalc.evaluate(tmpGeneration.getIndividualAt(i));
		}
		
		oldGeneration.replace(oldGeneration.getWeakestIndividual(), tmpGeneration.getFittestIndividual());
		
		return oldGeneration;
	}

	@Override
	public String getName() {
		return "SteadyStateStrongestForWeakest";
	}

	
}
